package ch1;

/**
 * @Author: Rocky
 * @Date: 2020/2/12
 * @Description:
 */
public class NewThead {

    private static class UserThread extends Thread {
        @Override
        public void run() {
            System.out.println("extends Thread: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                System.out.println("线程名" + Thread.currentThread().getName());
            }
        }).start();
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
        new UserThread().start();
    }

}
