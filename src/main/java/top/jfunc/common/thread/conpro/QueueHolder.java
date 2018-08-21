package top.jfunc.common.thread.conpro;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * @author xiongshiyan
 * 通用的阻塞队列保持者，通过名字保存和获取
 */
public class QueueHolder {
    private static Map<String , BlockingQueue> blockingQueueMap = new HashMap<>();
    public static <T> void put(String pipelineName , BlockingQueue<T> blockingQueue){
        blockingQueueMap.put(pipelineName , blockingQueue);
    }
    public static <T> BlockingQueue<T> getBlockingQueue(String pipelineName) {
        if(null == pipelineName || "".equals(pipelineName)){
            throw new IllegalArgumentException(" pipelineName 不能为空 ");
        }
        BlockingQueue blockingQueue = blockingQueueMap.get(pipelineName);
        if(null == blockingQueue){
            throw new IllegalStateException(pipelineName + " 对应的队列不存在 ...");
        }
        return blockingQueue;
    }
}
