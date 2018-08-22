package top.jfunc.common.sensitiveword.servlet;

import top.jfunc.common.sensitiveword.SensitiveWordFilter;
import top.jfunc.common.servlet.BaseHttpServletRequestWrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * 对HttpServletRequestWrapper重写，统一敏感词过滤处理
 * @author 熊诗言 2017-04-30 上午07:30:00
 */
public class SensitiveWordRequestWrapper extends BaseHttpServletRequestWrapper {

    private SensitiveWordFilter filter;

    public SensitiveWordRequestWrapper(HttpServletRequest request, SensitiveWordFilter filter){
        super(request);
        this.filter = filter;
    }

    @Override
    public String doFilter(String name, String value){
        return filter.doFilter(value);
    }
}
