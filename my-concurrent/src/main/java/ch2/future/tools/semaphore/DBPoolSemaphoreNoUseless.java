package ch2.future.tools.semaphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @Author: Rocky
 * @Date: 2020/4/14
 * @Description:
 */
public class DBPoolSemaphoreNoUseless {

    // 连接池的大小
    private static final int POOL_SIZE = 10;

    // 可用的连接
    private final Semaphore useful;

    // 连接池
    private static LinkedList<Connection> pool = new LinkedList<>();

    // 初始化连接池
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.add(SqlConnectionImpl.fetchConnection());
        }
    }

    public DBPoolSemaphoreNoUseless() {
        this.useful = new Semaphore(10);
    }

    /**
     * 获取连接
     * @return
     * @throws InterruptedException
     */
    public Connection takeConnection() throws InterruptedException {
        useful.acquire();
        Connection connection;
        synchronized (pool) {
            connection = pool.removeFirst();
        }
        return connection;
    }

    public void returnConnection(Connection connection) throws InterruptedException {
        if (connection != null) {
            System.out.println("当前有 " + useful.getQueueLength() + " 个线程在等待连接！！！" +
                    "可以用连接数为：" + useful.availablePermits());
            synchronized (pool) {
                pool.addLast(connection);
            }
            useful.release();
        }
    }


}
