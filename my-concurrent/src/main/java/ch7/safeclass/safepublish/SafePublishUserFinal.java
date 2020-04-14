package ch7.safeclass.safepublish;

/**
 * @Author: Rocky
 * @Date: 2020/4/9
 * @Description:
 */
public class SafePublishUserFinal {

    private final SyncFinalUser userVo;

    public SafePublishUserFinal(FinalUserVo userVo) {
        this.userVo = new SyncFinalUser(userVo);
    }

    public SyncFinalUser getUserVo() {
        return userVo;
    }

    private static class SyncFinalUser {
        private final FinalUserVo userVo;
        private final Object lock = new Object();

        public SyncFinalUser(FinalUserVo userVo) {
            this.userVo = userVo;
        }

        public int getAge() {
            synchronized (lock) {
                return userVo.getAge();
            }
        }

        public void setAge(int age) {
            synchronized (lock) {
                userVo.setAge(age);
            }
        }
    }
}
