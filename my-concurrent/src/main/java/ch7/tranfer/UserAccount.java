package ch7.tranfer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Rocky
 * @Date: 2020/4/9
 * @Description: 用户账户
 */
public class UserAccount {

    private String name; //账号姓名
    private int money; //账户余额

    private final Lock lock = new ReentrantLock();

    public Lock getLock() {
        return lock;
    }

    public UserAccount(String name, int money) {
        this.name = name;
        this.money = money;
    }


    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", lock=" + lock +
                '}';
    }

    // 转入
    public void addMoney(int amount) {
        money = money + amount;
    }

    // 转出
    public void flyMoney(int amount) {
        money = money - amount;
    }

    public static void main(String[] args) {
        UserAccount userAccount = new UserAccount("zhangsan", 200);
        System.out.println(userAccount.getName());
        System.out.println(userAccount.getLock());
    }

}
