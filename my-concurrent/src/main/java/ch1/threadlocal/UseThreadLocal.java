package ch1.threadlocal;

/**
 * @Author: Rocky
 * @Date: 2020/4/1
 * @Description:
 */
public class UseThreadLocal {

    private static ThreadLocal<Integer> intLocal = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return 1;
        }
    };

    private static class TestThread extends Thread {

        private int id;

        private TestThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start");
            Integer s = intLocal.get();
            s = id + s;
            intLocal.set(s);
            System.out.println(Thread.currentThread().getName() + " end, 值为：" + intLocal.get());
            intLocal.remove();
        }
    }

    private static void start() {
        for (int i = 0; i < 5; i++) {
            new TestThread(i).start();
        }
    }

    public static void main(String[] args) {
        start();
    }


}
