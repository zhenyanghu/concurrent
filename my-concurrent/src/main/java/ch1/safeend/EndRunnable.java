package ch1.safeend;

/**
 * @Author: Rocky
 * @Date: 2020/2/12
 * @Description:
 */
public class EndRunnable {

    public static void main(String[] args) throws InterruptedException {
        Thread t =  new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
//            while (!Thread.interrupted()) {
                System.out.println("I am is implements Runnable: " + Thread.currentThread().getName());
            }
            System.out.println("interrupt flag is: " + Thread.currentThread().isInterrupted());
        });
        t.setName("EndRunnable Thread");
        t.start();
        Thread.sleep(10);
        t.interrupt();
    }
}
