package top.jfunc.common.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author xiongshiyan at 2017/12/7
 */
public class RadixTest {

    private static final String SRC = "123";

    @Test
    public void testJoinMap(){
        String od = RadixUtil.radix(SRC, 8, 10);
        Assert.assertEquals(Integer.valueOf(SRC , 8).toString() , od);

        String oh = RadixUtil.radix(SRC, 8, 16);
        Assert.assertEquals(Integer.toString(Integer.parseInt(SRC , 8 ) , 16) , oh);
    }
}
