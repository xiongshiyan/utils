package top.jfunc.common.thread;

/**
 * @author xiongshiyan at 2018/5/15
 */
public class ThreadUtil {
    /**
     * 暂停毫秒
     * @param ms 毫秒
     */
    public static void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停秒
     * @param s 秒
     */
    public static void sleeps(long s){
        sleep(1000 * s);
    }
}
