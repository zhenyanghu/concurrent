package ch7.tranfer.service;

import ch7.tranfer.UserAccount;

import java.util.Random;

/**
 * @Author: Rocky
 * @Date: 2020/4/9
 * @Description: 不会产生死锁的第二种方法：尝试拿锁
 */
public class SafeOperateToo implements ITransfer {

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount) {
        Random r = new Random();
        while (true) {
            if (from.getLock().tryLock()) {
                System.out.println(Thread.currentThread().getName() + " get " + from.getName());
                try {
                    if (to.getLock().tryLock()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " get " + to.getName());
                            from.flyMoney(amount);
                            to.addMoney(amount);
                            System.out.println(from);
                            System.out.println(to);
                            break;
                        } finally {
                            to.getLock().unlock();
                        }
                    }
                } finally {
                    from.getLock().unlock();
                }
            }
            try {
                Thread.sleep(r.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
