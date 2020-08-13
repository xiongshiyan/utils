package top.jfunc.common;

/**
 * 可以抛出异常的runnable,{@link Runnable}
 * @author xiongshiyan at 2020/8/12 , contact me with email yanshixiong@126.com or phone 15208384257
 */
@FunctionalInterface
public interface ExceptionalRunnable {
    /**
     * 执行的代码
     * @throws Exception 可以抛出异常
     */
    void run() throws Exception;
}
