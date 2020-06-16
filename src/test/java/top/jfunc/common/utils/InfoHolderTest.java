package top.jfunc.common.utils;

import top.jfunc.common.thread.ThreadUtil;
import top.jfunc.common.utils.local.MapInfoHolder;
import top.jfunc.common.utils.local.NormalInfoHolderUtil;
import top.jfunc.common.utils.local.StringMapInfoHolderUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xiongshiyan at 2017/12/27
 */
public class InfoHolderTest {
    @Test
    public void testHolder(){
        MapInfoHolder<String,String> holder = new MapInfoHolder<>();
        holder.add("ss","ss");
        String s = holder.get("ss");
        Assert.assertEquals("ss",s);
        holder.add("sd","sd");
        s = holder.get("sd");
        Assert.assertEquals("sd",s);
        new Thread(()->{
            String s1 = holder.get("ss");
            Assert.assertNull(s1);
        });
        holder.clear();
        s = holder.get("ss");
        Assert.assertNull(s);
    }
    @Test
    public void testStringMapHolder(){
        StringMapInfoHolderUtil.put("ss" , "ss");
        String s = StringMapInfoHolderUtil.get("ss");
        Assert.assertEquals("ss",s);
        StringMapInfoHolderUtil.put("sd" , "sd");
        s = StringMapInfoHolderUtil.get("sd");
        Assert.assertEquals("sd",s);
        new Thread(()->{
            String s1 = StringMapInfoHolderUtil.get("ss");
            Assert.assertNull(s1);
        });
        StringMapInfoHolderUtil.clear();
        s = StringMapInfoHolderUtil.get("ss");
        Assert.assertNull(s);
    }
    @Test
    public void testNormalHolder(){
        NormalInfoHolderUtil.set("ss");
        String s = NormalInfoHolderUtil.get();
        Assert.assertEquals("ss",s);
        NormalInfoHolderUtil.set("sd");
        fun1();
        NormalInfoHolderUtil.clear();
        s = NormalInfoHolderUtil.get();
        Assert.assertNull(s);
    }

    private void fun1() {
        String s;
        s = NormalInfoHolderUtil.get();
        Assert.assertEquals("sd",s);
        new Thread(()->{
            String s1 = NormalInfoHolderUtil.get();
            Assert.assertNull(s1);
        });
    }

    public static final ThreadLocal<String> t = ThreadLocal.withInitial(()->"xx");
    @SuppressWarnings("unchecked")
    public static final ThreadLocal<String> it = new InheritableThreadLocal(){
        @Override
        protected Object initialValue() {
            return "xx";
        }
    };

    /**
     *
     11111
     Thread[Thread-0,5,main]xx     不能拿到父线程设置的值
     Thread[Thread-0,5,main]22222  获取到自己设置的值
     11111                         父线程拿不到子线程设置的值
     */
    @Test
    public void testThreadLocal(){
        print(t);
    }

    /**
     *
     11111
     Thread[Thread-0,5,main]11111  能拿到父线程设置的值
     Thread[Thread-0,5,main]22222  获取到自己设置的值
     11111                         父线程拿不到子线程设置的值
     */
    @Test
    public void testInheritableThreadLocal(){
        print(it);
    }

    private void print(ThreadLocal<String> t){
        t.set("11111");
        System.out.println(t.get());
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + t.get());
                t.set("22222");
                System.out.println(Thread.currentThread() + t.get());
            }
        }.start();

        ThreadUtil.sleeps(2);
        System.out.println(t.get());
    }
}
