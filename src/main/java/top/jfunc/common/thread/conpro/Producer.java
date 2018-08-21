package top.jfunc.common.thread.conpro;

import java.util.concurrent.BlockingQueue;

/**
 * @author xiongshiyan
 * 通用的消费者
 */
public class Producer {
    public   static <T> void offer(BlockingQueue<T> blockingQueue , T t){
        blockingQueue.offer(t);
    }
    public   static <T> void offer(String pipelineName , T t){
        BlockingQueue<T> blockingQueue = QueueHolder.getBlockingQueue(pipelineName);
        blockingQueue.offer(t);
    }
}
