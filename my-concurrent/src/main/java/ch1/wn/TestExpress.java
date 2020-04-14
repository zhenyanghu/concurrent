package ch1.wn;

import tools.SleepTools;

/**
 * @Author: Rocky
 * @Date: 2020/2/13
 * @Description:
 */
public class TestExpress {

    private static Express e = new Express(0, "guangzhou");

    /**
     * 该线程等待距离的改变
     */
    private static class changeKmThread extends Thread {
        @Override
        public void run() {
            e.waitKm();
        }
    }

    /**
     * 该线程等待地址的改变
     */
    private static class changeCityThread extends Thread {
        @Override
        public void run() {
            e.waitCity();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new changeKmThread().start();
        }
        for (int i = 0; i < 3; i++) {
            new changeCityThread().start();
        }
        SleepTools.second(2);
        e.changeCity();
    }



}
