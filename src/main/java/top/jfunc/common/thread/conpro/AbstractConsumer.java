package top.jfunc.common.thread.conpro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * @author xiongshiyan
 * 通用的消费者
 */
public abstract class AbstractConsumer<T> implements Runnable , java.util.function.Consumer<T> {
    private final BlockingQueue<T> blockingQueue;

    private static Logger logger                      = LoggerFactory.getLogger(AbstractConsumer.class);

    /**
     * 构造方法传入与生产者相同的 BlockingQueue
     * @param blockingQueue 阻塞队列
     */
    public AbstractConsumer(BlockingQueue<T> blockingQueue){
        this.blockingQueue = blockingQueue;
    }


    @Override
    public void run() {

        while (true){
            try {
                T take = blockingQueue.take();
                accept(take);
            } catch (InterruptedException e) {
                logger.error(e.getMessage() , e);
            }
        }
    }

    /**
     * 消费者去消费
     * @param t 消费的数据
     */
    @Override
    abstract public void accept(T t);
}
