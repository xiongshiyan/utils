package top.jfunc.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 * @author 熊诗言
 */
public class ExceptionUtil {
    private ExceptionUtil(){}
    /**
     * 获取异常的堆栈信息。logback自动支持，使用logger.error(msg,Throwable e)
     * @param e 异常
     * @return 异常堆栈
     */
    public static String getExceptionStack(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}