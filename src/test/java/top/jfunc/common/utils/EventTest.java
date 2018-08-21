package top.jfunc.common.utils;

import org.junit.Ignore;
import org.junit.Test;
import top.jfunc.common.event.EventInitializer;
import top.jfunc.common.event.EventKit;
import top.jfunc.common.event.async.AsyncEvent;
import top.jfunc.common.thread.ThreadUtil;

/**
 * @author 熊诗言 at 2017/11/23
 */
public class EventTest {
    @Test
    @Ignore
    public void exportWithConnection(){
        new EventInitializer().scanPackage("cn.zytx.common").async().start();
        for (int i=0;i<100;i++) {
            final int j = i;
            EventKit.post(new AsyncEvent(()->{
                long begin = System.currentTimeMillis();
                //CommonUtil.sleeps(RandomUtil.randomInt(10));
                //System.out.println( j +"－异步执行我，可以是非常耗时的操作－" + Thread.currentThread().getName());
                System.out.println("当前线程耗时： " + (System.currentTimeMillis() - begin) / 1000 + " s");
            }));
        }
        ThreadUtil.sleeps(1000);
    }
    @Test
    @Ignore
    public void async(){
        new EventInitializer().scanPackage("cn.zytx.common").async().start();
        for (int i=0;i<100;i++) {
            final int j = i;
            EventKit.post(new AsyncEvent(()->{
                long begin = System.currentTimeMillis();
                ThreadUtil.sleeps(RandomUtil.randomInt(10));
                System.out.println( j +"－异步执行我，可以是非常耗时的操作－" + Thread.currentThread().getName());
                System.out.println("当前线程耗时： " + (System.currentTimeMillis() - begin) / 1000 + " s");
            }));
        }
        ThreadUtil.sleeps(1000);
    }
}
