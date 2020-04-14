package ch1;

import tools.SleepTools;

import java.util.Objects;

/**
 * @Author: Rocky
 * @Date: 2020/2/12
 * @Description:
 */
public class UseJoin {

    public static class Goddess extends Thread {

        private Thread thread;

        public Goddess(String name, Thread thread) {
            super(name);
            this.thread = thread;
        }

        public Goddess(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("goddess 开始排队打饭...");
            if (Objects.nonNull(thread)) {
                try {
                    thread.join();
                } catch (InterruptedException e) {

                }
            }
            SleepTools.second(2);
            System.out.println("goddess 开始打饭结束！");
        }
    }

    public static class GoddessBoyFriend extends Thread {
        @Override
        public void run() {
            System.out.println("goddessBoyFriend 开始排队打饭...");
            SleepTools.second(2);
            System.out.println("goddessBoyFriend 打饭结束！");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread son = Thread.currentThread();
        son.setName("son");
        System.out.println("son开始排队打饭...");

        GoddessBoyFriend gbf = new GoddessBoyFriend();
        Goddess g = new Goddess("goddess", gbf);
        g.start();
        gbf.start();
        g.join();

        SleepTools.second(2);
        System.out.println("son开始打饭结束！");

    }
}
