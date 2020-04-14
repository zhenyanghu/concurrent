package ch1.wn;

/**
 * @Author: Rocky
 * @Date: 2020/2/13
 * @Description:
 */
public class Express {

    private int km;

    private String city;

    public Express(int km, String city) {
        this.km = km;
        this.city = city;
    }

    public synchronized void changeKm() {
        km = 101;
        this.notify();
    }

    public synchronized void changeCity() {
        city = "wuhan";
        this.notifyAll();
    }

    public synchronized void waitKm() {
        while (km <= 100) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 等待距离改变被唤醒");
        }
        System.out.println(Thread.currentThread().getName() + " 距离改变写数据库");
    }

    public synchronized void waitCity() {
        while (!city.equals("wuhan")) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 等待目的地唤醒");
        }
        System.out.println(Thread.currentThread().getName() + " 目的地改变写数据库");
    }
}
