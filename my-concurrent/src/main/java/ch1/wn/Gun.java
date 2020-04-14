package ch1.wn;

import tools.SleepTools;

/**
 * @Author: Rocky
 * @Date: 2020/2/13
 * @Description:
 */
public class Gun {
    private int zd = 0;

    /**
     * 等待加入子弹
     */
    private synchronized void put() {
        while (zd >= 20) {
            System.out.println(Thread.currentThread().getName() + "当前弹夹已有20枚子弹");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        zd++;
        System.out.println(Thread.currentThread().getName() + "加入一枚子弹，子弹数为：" + zd);
        notifyAll();
    }

    /**
     * 等待发射字段
     */
    private synchronized void get() {
        while (zd <= 0) {
            System.out.println(Thread.currentThread().getName() + "当前弹夹子弹为0");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        zd--;
        System.out.println(Thread.currentThread().getName() + "射出一枚子弹，字段数为：" + zd);
        notifyAll();
    }

    private static class Consumer implements Runnable {
        private Gun gun;

        public Consumer(Gun gun) {
            this.gun = gun;
        }

        @Override
        public void run() {
            while (true) {
                gun.get();
                SleepTools.ms(8);
            }
        }
    }

    private static class Produce implements Runnable {
        private Gun gun;

        public Produce(Gun gun) {
            this.gun = gun;
        }

        @Override
        public void run() {
            while (true) {
                gun.put();
                SleepTools.ms(6);
            }
        }
    }

    public static void main(String[] args) {
        Gun gun = new Gun();
        for (int i = 0; i < 6; i++) {
            new Thread(new Produce(gun), "Produce-" + i).start();
        }
        for (int i = 0; i < 6; i++) {
            new Thread(new Consumer(gun), "Consumer" + i).start();
        }
    }

}
