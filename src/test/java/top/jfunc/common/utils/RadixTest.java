package top.jfunc.common.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author xiongshiyan at 2017/12/7
 */
public class RadixTest {

    private static final String SRC = "123";

    @Test
    public void testRadix(){
        String od = RadixUtil.radix(SRC, 8, 10);
        Assert.assertEquals(Integer.valueOf(SRC , 8).toString() , od);

        String oh = RadixUtil.radix(SRC, 8, 16);
        Assert.assertEquals(Integer.toString(Integer.parseInt(SRC , 8 ) , 16) , oh);
    }
    @Test
    public void testBinHex(){
        byte[] bs = new byte[]{12,13,14,15};
        String s = RadixUtil.toHexLower(bs);
        System.out.println(s);
        byte[] bytes = RadixUtil.toBytes(s);
        for (int i = 0; i < bs.length; i++) {
            Assert.assertEquals(bs[i] , bytes[i]);
        }
    }
}
