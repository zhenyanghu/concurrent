package ch1.safeend;

/**
 * @Author: Rocky
 * @Date: 2020/2/12
 * @Description:
 */
public class HasInterruptedEx {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException 后 interrupt flag: "
                            + Thread.currentThread().isInterrupted());
                    // 释放资源
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("线程名： " + Thread.currentThread().getName());
                System.out.println("中断标识：" + Thread.currentThread().isInterrupted());

            }
        });
        t.start();
        Thread.sleep(500);
        t.interrupt();
    }
}
