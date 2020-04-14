package ch1;

import tools.SleepTools;

import java.time.LocalDateTime;

/**
 * @Author: Rocky
 * @Date: 2020/2/12
 * @Description:
 */
public class SleepLock {

    private Object lock = new Object();

    private class SleepThread extends Thread {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " will take lock " + LocalDateTime.now());
            synchronized (lock) {
                SleepTools.second(10);
                System.out.println(threadName + " finish work " + LocalDateTime.now());
            }
        }
    }

    private class SleepNotThread extends Thread {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " will take lock " + LocalDateTime.now());
            synchronized (lock) {
                System.out.println(threadName + " finish work " + LocalDateTime.now());
            }
        }
    }

    public static void main(String[] args) {
        SleepLock sleep = new SleepLock();
        SleepThread a = sleep.new SleepThread();
        a.setName("SleepThread");
        SleepNotThread b = sleep.new SleepNotThread();
        b.setName("SleepNotThread");
        a.start();

        SleepTools.second(2);
        System.out.println("Main slept");

        // a释放锁之后，b才能执行
        b.start();
    }

}
