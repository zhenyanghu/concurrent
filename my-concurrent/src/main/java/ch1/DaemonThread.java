package ch1;

/**
 * @Author: Rocky
 * @Date: 2020/2/12
 * @Description:
 */
public class DaemonThread {

    private static class UseThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("并不一定会执行...");
            }
        }
    }

    static {
        UseThread t = new UseThread();
        t.setDaemon(true);
        t.start();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(2000);
    }

}
