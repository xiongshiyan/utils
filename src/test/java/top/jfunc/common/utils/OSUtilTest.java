package top.jfunc.common.utils;

import org.junit.Test;

/**
 * @author xiongshiyan at 2019/11/15 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class OSUtilTest {
    @Test
    public void testOS(){
        System.out.println(OSUtil.osName());
        System.out.println(OSUtil.osVersion());
        System.out.println(OSUtil.osArch());
    }
}
