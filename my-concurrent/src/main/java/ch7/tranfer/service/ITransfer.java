package ch7.tranfer.service;

import ch7.tranfer.UserAccount;

/**
 * @Author: Rocky
 * @Date: 2020/4/9
 * @Description:
 */
public interface ITransfer {

    void transfer(UserAccount from, UserAccount to, int amount);
}
