package top.jfunc.common.thread.conpro;

import top.jfunc.common.utils.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author xiongshiyan at 2018/5/14
 */
public class ThreadPool {
    /** 线程池 */
    private static ExecutorService pool        = null;
    /**
     * 异步，默认创建5个线程
     * @param nThreads 线程池的容量，不传或小于1时默认为5，有两个参数的话第二个参数就是LinkedBlockingQueue的容量
     */
    public static void init(int... nThreads){
        ThreadFactory factory = ThreadFactoryBuilder.create().setNameFormat("event-pool-%d").setDaemon(true).build();
        pool = new ThreadPoolExecutor(nThreads.length == 0 || nThreads[0] < 1 ? 5 : nThreads[0],
                100, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(nThreads.length <= 1 || nThreads[1] < 1 ? 512 : nThreads[1]), factory, new ThreadPoolExecutor.DiscardPolicy());
    }
    public static void exe(Runnable runnable){
        if(null == pool){
            throw new IllegalStateException("线程池尚未初始化，请先调用 ThreadPool#init 方法");
        }
        pool.execute(runnable);
    }
}
