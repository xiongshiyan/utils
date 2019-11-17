package top.jfunc.common.thread;

import java.util.concurrent.TimeUnit;

/**
 * 使程序不退出，保证至少一个前台进程
 * @see <a href="https://dubbo.apache.org/zh-cn/blog/spring-boot-dubbo-start-stop-analysis.html" target="_blank">spring-boot-dubbo-start-stop-analysis</a>
 * @author xiongshiyan at 2019/10/16 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class HoldProcessor {
    private volatile boolean stopAwait = false;
    /**
     * Thread that currently is inside our await() method.
     */
    private volatile Thread awaitThread = null;

    /**
     * 开始等待
     */
    public void startAwait(){
        Thread awaitThread = new Thread(this::await,"hold-process-thread");
        awaitThread.setContextClassLoader(getClass().getClassLoader());
        //这一步很关键，保证至少一个前台进程
        awaitThread.setDaemon(false);
        awaitThread.start();
    }

    /**
     * 停止等待，退出程序，一般放在shutdown hook中执行
     * @see Runtime#addShutdownHook(Thread)
     */
    public void stopAwait() {
        //此变量
        stopAwait=true;
        Thread t = awaitThread;
        if (null != t) {
            t.interrupt();
            try {
                t.join(1000);
            } catch (InterruptedException e) {
                // Ignored
            }
        }
    }

    private void await(){
        try {
            awaitThread = Thread.currentThread();
            while(!stopAwait) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch( InterruptedException ex ) {
                    // continue and check the flag
                }
            }
        } finally {
            awaitThread = null;
        }
    }
}
