package ch2.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: Rocky
 * @Date: 2020/4/10
 * @Description:
 */
public class UseFuture {

    private static class TestCallable implements Callable<Integer> {
        private int sum;

        @Override
        public Integer call() throws Exception {
            System.out.println("Callable子线程开始计算！");
            for (int i = 0; i < 5000; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("程序中断。。。");
                    return null;
                }
                sum = sum + i;
                System.out.println("sum=" + sum);
            }
            System.out.println("Callable子线程计算结束！结果为: " + sum);
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable callable = new TestCallable();
        FutureTask futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        Random r = new Random();
        Thread.sleep(1);
        if (r.nextInt(100) < 50) {
            System.out.println("结果：" + futureTask.get());
        } else {
            System.out.println("Cancel.....");
            futureTask.cancel(true);
        }
    }

}
