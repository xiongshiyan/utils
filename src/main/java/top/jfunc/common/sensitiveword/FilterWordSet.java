package top.jfunc.common.sensitiveword;

import java.util.Set;

/**
 * 获取所有敏感词，或者停顿词的接口
 * @author 熊诗言
 */
public interface FilterWordSet{
    /**
     * 词集合，可以是敏感词，也可以是停顿词，取决于你的使用
     * @return 词集合
     */
    Set<String> getWordSet();
}
