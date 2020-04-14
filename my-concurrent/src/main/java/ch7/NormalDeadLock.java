package ch7;

/**
 * @Author: Rocky
 * @Date: 2020/4/9
 * @Description:
 */
public class NormalDeadLock {

    private final static Object first = new Object();
    private final static Object second = new Object();

    public static void firstToSecond() {
        String name = Thread.currentThread().getName();
        synchronized (first) {
            System.out.println(name + " get first");
            try {
                // 模拟业务时间
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (second) {
                System.out.println(name + " get second");
            }
        }
    }

    public static void secondToFirst() {
        String name = Thread.currentThread().getName();
        synchronized (second) {
            System.out.println(name + " get second");
            try {
                // 模拟业务时间
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (first) {
                System.out.println(name + " get first");
            }
        }
    }

    private static class TestThread extends Thread {
        private String name;

        public TestThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            secondToFirst();
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("TestDeadLock");
        TestThread t = new TestThread("SubThread");
        t.start();
        firstToSecond();

    }

}
