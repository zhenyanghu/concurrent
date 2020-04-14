package ch7.tranfer.service;

import ch7.tranfer.UserAccount;

/**
 * @Author: Rocky
 * @Date: 2020/4/9
 * @Description: 不安全的转账动作的实现
 */
public class TrasnferAccount implements ITransfer {

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount) {
        synchronized (from) {
            System.out.println(Thread.currentThread().getName() + " get " + from.getName());
            synchronized (to) {
                System.out.println(Thread.currentThread().getName() + " get " + to.getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                from.flyMoney(amount);
                to.addMoney(amount);
            }
        }
    }

}
