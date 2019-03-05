package top.jfunc.common.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author xiongshiyan at 2019/3/5 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class StrUtilTest {
    @Test
    public void testToUnderlineCase(){
        Assert.assertEquals("hello_world" , StrUtil.toUnderlineCase("HelloWorld"));
        Assert.assertEquals("hello_world" , StrUtil.toUnderlineCase("helloWorld"));
        Assert.assertEquals("hello_world" , StrUtil.toUnderlineCase("helloWORLD"));
    }
}
