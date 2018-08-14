package top.jfunc.common.utils;

import org.junit.Test;


/**
 * 一些非常公共的工具类测试
 * @author xiongshiyan at 2017/12/11
 */
public class ExceptionUtilTest {
    @Test
    public void testGetException(){
        try {
            Object o = null;
            o.hashCode();
        } catch (Exception e) {
            String x = ExceptionUtil.getExceptionStack(e);
            System.out.println(x);
        }
    }
}
