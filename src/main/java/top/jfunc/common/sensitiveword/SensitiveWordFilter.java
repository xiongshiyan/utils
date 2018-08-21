package top.jfunc.common.sensitiveword;

import java.util.Set;

/**
 * 过滤器接口
 * @author 熊诗言
 * @see top.jfunc.common.sensitiveword.impl.simple.SimpleWordFilter
 * @see top.jfunc.common.sensitiveword.impl.stop.WithStopFilter
 */
public interface SensitiveWordFilter{

    /**
     * 初始化敏感词
     * @param sensitiveWords 敏感词集合
     */
    void init(Set<String> sensitiveWords);

    /**
     * 判断是否包含敏感字符串
     * @param src 源字符串
     * @return 是否包含敏感词
     */
    boolean isContains(String src);

    /**
     * 输出包含的敏感词
     * @param src 源字符串
     * @return 获取包含的敏感词
     */
    Set<String> doGetSensitiveWords(String src);

    /**
     * 传入源字符串，输出过滤后的字符串
     * @param src 源字符串
     * @return 过滤后的字符串
     */
    String doFilter(String src);
}
