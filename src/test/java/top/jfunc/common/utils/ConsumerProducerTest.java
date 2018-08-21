package top.jfunc.common.utils;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import top.jfunc.common.thread.ThreadUtil;
import top.jfunc.common.thread.conpro.AbstractConsumer;
import top.jfunc.common.thread.conpro.Producer;
import top.jfunc.common.thread.conpro.QueueHolder;
import top.jfunc.common.thread.conpro.ThreadPool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xiongshiyan at 2018/5/14
 */
public class ConsumerProducerTest {

    /**
     * 在程序初始化的时候需要初始化线程池和消费者
     */
    @BeforeClass
    @Ignore
    public static void init(){
        //初始化线程池
        ThreadPool.init();
        //一对队列
        BlockingQueue<Map<String , Object>> blockingDeque = new LinkedBlockingQueue<>();
        QueueHolder.put("login_count" , blockingDeque);

        ThreadPool.exe(new AbstractConsumer<Map<String , Object>>(blockingDeque) {
            @Override
            public void accept(Map<String, Object> o) {
                System.out.println(o);
                ThreadUtil.sleeps(1);
            }
        });
        //一对队列
        BlockingQueue<Banner> blockingDeque2 = new LinkedBlockingQueue<>();
        QueueHolder.put("banner" , blockingDeque2);
        System.out.println("------------");

        ThreadPool.exe(new AbstractConsumer<Banner>(blockingDeque2) {
            @Override
            public void accept(Banner o) {
                System.out.println("消费者 1：" + o);
                ThreadUtil.sleeps(1);
            }
        });
        ThreadPool.exe(new AbstractConsumer<Banner>(blockingDeque2) {
            @Override
            public void accept(Banner o) {
                System.out.println("消费者 2：" + o);
                ThreadUtil.sleeps(1);
            }

        });
    }
    public static class Banner{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Banner{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    @Test
    @Ignore
    public void testProducer() throws Exception{
        String in = "123";
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("xx",in);
        BlockingQueue<Map<String , Object>> blockingQueue = QueueHolder.getBlockingQueue("login_count");
        Producer.offer(blockingQueue , hashMap);
        //模拟并发
        for (int i = 0 ; i<10000 ; i++) {
            Banner banner = new Banner();
            banner.setName("banner " + i);
            Producer.offer("banner" , banner);
        }
        System.out.println("发送完成");
        Thread.sleep(100000);
    }
}
