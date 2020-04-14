package ch2.future.tools.semaphore;

import java.util.Random;

/**
 * @Author: Rocky
 * @Date: 2020/4/14
 * @Description:
 */
public class AppTest {

    public static void main(String[] args) {
        DBPoolSemaphore dbPool = new DBPoolSemaphore();
//        DBPoolSemaphoreNoUseless dbPool = new DBPoolSemaphoreNoUseless();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    long start = System.currentTimeMillis();
//                    Connection connection = dbPool.takeConnection();
                    System.out.println(Thread.currentThread().getName() + " 耗时 [" + (System.currentTimeMillis() - start)
                            + "] ms get Connection ");
                    Thread.sleep(100 + random.nextInt(100));
                    System.out.println(Thread.currentThread().getName() + " do something");
//                    dbPool.returnConnection(connection);
                    dbPool.returnConnection(new SqlConnectionImpl());
                    System.out.println(Thread.currentThread().getName() + " return Connection");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
