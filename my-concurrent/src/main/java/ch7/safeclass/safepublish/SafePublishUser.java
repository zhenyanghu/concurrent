package ch7.safeclass.safepublish;

import ch7.safeclass.UserVo;

/**
 * @Author: Rocky
 * @Date: 2020/4/9
 * @Description:
 */
public class SafePublishUser {

    private final UserVo userVo;

    public SafePublishUser(UserVo userVo) {
        this.userVo = new SyncUser(userVo);
    }

    public UserVo getUserVo() {
        return userVo;
    }

    private static class SyncUser extends UserVo {
        private final UserVo userVo;
        private final Object lock = new Object();

        public SyncUser(UserVo userVo) {
            this.userVo = userVo;
        }

        @Override
        public int getAge() {
            synchronized (lock) {
                return userVo.getAge();
            }
        }

        @Override
        public void setAge(int age) {
            synchronized (lock) {
                userVo.setAge(age);
            }
        }
    }
}
