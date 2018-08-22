package top.jfunc.common.servlet;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 对HttpServletRequestWrapper重写，形成一个基本的算法框架【模板方法模式】，xss和敏感词过滤可以继承于它，只需要写自己的过滤逻辑
 * @see top.jfunc.common.sensitiveword.servlet.SensitiveWordRequestWrapper
 * @author 熊诗言 2017-04-30 上午07:30:00
 */
public abstract class BaseHttpServletRequestWrapper extends javax.servlet.http.HttpServletRequestWrapper{

    public BaseHttpServletRequestWrapper(HttpServletRequest request){
        super(request);
    }

    /**
     * 重写并过滤getParameter方法
     */
    @Override
    public String getParameter(String name){
        return doFilter(name, super.getParameter(name));
    }

    /**
     * 重写并过滤getParameterValues方法
     */
    @Override
    public String[] getParameterValues(String name){
        String[] values = super.getParameterValues(name);
        if(null == values){
            return null;
        }
        String[] newValues = new String[values.length];
        for(int i = 0; i < values.length; i++){
            newValues[i] = doFilter(name, values[i]);
        }
        return newValues;
    }

    /**
     * 重写并过滤getParameterMap方法
     */
    @Override
    public Map<String, String[]> getParameterMap(){

        Map<String, String[]> temp = new HashMap<String, String[]>();
        Map<String, String[]> paraMap = super.getParameterMap();
        // 对于paraMap为空的直接return
        if(null == paraMap || paraMap.isEmpty()){
            return paraMap;
        }

        Iterator<Entry<String, String[]>> iter = paraMap.entrySet().iterator();
        while(iter.hasNext()){
            Entry<String, String[]> entry = iter.next();
            String key = entry.getKey();
            String[] values = entry.getValue();
            if(null == values){
                continue;
            }
            String[] newValues = new String[values.length];
            for(int i = 0; i < values.length; i++){
                newValues[i] = doFilter(key, values[i]);
            }
            temp.put(key, newValues);
        }

        return temp;
    }

    /**
     * 实现自己的过滤逻辑
     * @param key 参数名
     * @param value 参数值
     * @return 过滤后的参数值
     */
    abstract public String doFilter(String key, String value);
}
