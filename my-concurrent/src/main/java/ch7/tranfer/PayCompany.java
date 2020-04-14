package ch7.tranfer;

import ch7.tranfer.service.ITransfer;
import ch7.tranfer.service.SafeOperateToo;

/**
 * @Author: Rocky
 * @Date: 2020/4/9
 * @Description:
 */
public class PayCompany {

    /**
     * 执行转账动作的线程
     */
    private static class TestThread extends Thread {

        private String name; //线程名
        private UserAccount from;
        private UserAccount to;
        private int amount;
        private ITransfer transfer;

        public TestThread(String name, UserAccount from, UserAccount to, int amount, ITransfer transfer) {
            this.name = name;
            this.from = from;
            this.to = to;
            this.amount = amount;
            this.transfer = transfer;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            transfer.transfer(from, to, amount);
        }
    }

    public static void main(String[] args) {
        ITransfer transfer = new SafeOperateToo();
        UserAccount zhangsan = new UserAccount("zhangsan", 2000);
        UserAccount lisi = new UserAccount("lisi", 2000);
        TestThread toLi = new TestThread("zhangsan", zhangsan, lisi, 100, transfer);
        TestThread toZhang = new TestThread("lisi", lisi, zhangsan, 200, transfer);
        toLi.start();
        toZhang.start();
    }
}
