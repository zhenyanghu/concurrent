package ch2.future.tools;

import tools.SleepTools;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: Rocky
 * @Date: 2020/4/13
 * @Description:
 */
public class UseCountDownLatch {

    private static CountDownLatch latch = new CountDownLatch(6);

    /**
     * 初始化线程，
     */
    private static class InitThread implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread_" + Thread.currentThread().getId() + " init ..." );
            latch.countDown();
            for (int i = 0; i < 2; i++) {
                System.out.println("Thread_" + Thread.currentThread().getId() + " continue its work ..." );
            }
        }
    }

    /**
     * 业务线程
     */
    private static class BusinessThread implements Runnable {
        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println("BusinessThread_" + Thread.currentThread().getId() + " do business ------" );
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println("Thread_" + Thread.currentThread().getId() + " ready start ..." );

            SleepTools.ms(1);
            System.out.println("Thread_" + Thread.currentThread().getId() + " do 1st step !!!" );
            latch.countDown();

            SleepTools.ms(1);
            System.out.println("Thread_" + Thread.currentThread().getId() + " do 2nd step !!!" );
            latch.countDown();
        }).start();

        new Thread(new BusinessThread()).start();

        for (int i = 0; i <= 3; i++) {
            new Thread(new InitThread()).start();
        }

        latch.await();
        System.out.println("MainThread_" + Thread.currentThread().getId() + " do work !!!");
    }


}
