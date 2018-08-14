package top.jfunc.common.utils;

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
}
