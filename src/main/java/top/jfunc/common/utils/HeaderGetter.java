package top.jfunc.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 因为基于Servlet的和其他的框架例如vertx的获取方式不一样
 * @author xiongshiyan at 2020/6/16 , contact me with email yanshixiong@126.com or phone 15208384257
 */
@FunctionalInterface
public interface HeaderGetter {
    /**
     * 从请求中获取header
     * @see HttpServletRequest
     * @param headerKey headerKey
     * @return header
     */
    String getHeader(String headerKey);
}