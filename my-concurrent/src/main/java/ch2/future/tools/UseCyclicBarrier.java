package ch2.future.tools;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: Rocky
 * @Date: 2020/4/14
 * @Description:
 */
public class UseCyclicBarrier {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new CollectionRunnable());

    private static ConcurrentHashMap<Long, Long> resultMap = new ConcurrentHashMap<>();

    private static class CollectionRunnable implements Runnable {
        @Override
        public void run() {
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<Long, Long> entry : resultMap.entrySet()) {
                sb.append(" [" + entry.getValue() + "] ");
            }
            System.out.println("Result = " + sb.toString());
        }
    }

    private static class SubThread implements Runnable {
        @Override
        public void run() {
            try {
                resultMap.put(Thread.currentThread().getId(), Thread.currentThread().getId());
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + " do something");
                cyclicBarrier.await();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + " do business");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(new SubThread()).start();
        }
    }


}
