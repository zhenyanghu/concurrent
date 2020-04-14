package ch7.tranfer.service;

import ch7.tranfer.UserAccount;

/**
 * @Author: Rocky
 * @Date: 2020/4/9
 * @Description: 不会产生死锁的安全转账
 */
public class SafeOperate implements ITransfer {

    private static Object lock = new  Object(); //第三把锁

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount) {

        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if (fromHash < toHash) {
            synchronized (from) {
                System.out.println(Thread.currentThread().getName() + " get " + from.getName());
                synchronized (to) {
                    System.out.println(Thread.currentThread().getName() + " get " + to.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from);
                    System.out.println(to);
                }
            }
        } else if (toHash < fromHash) {
            synchronized (to) {
                System.out.println(Thread.currentThread().getName() + " get " + to.getName());
                synchronized (from) {
                    System.out.println(Thread.currentThread().getName() + " get " + from.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from);
                    System.out.println(to);
                }
            }
        } else {
            synchronized (lock) {
                synchronized (from) {
                    synchronized (to) {
                        from.flyMoney(amount);
                        to.addMoney(amount);
                    }
                }
            }
        }
    }
}
