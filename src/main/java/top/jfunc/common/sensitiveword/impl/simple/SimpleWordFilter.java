package top.jfunc.common.sensitiveword.impl.simple;

import top.jfunc.common.sensitiveword.AbstractSensitiveWordFilter;
import top.jfunc.common.sensitiveword.SensitiveWordFilter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 敏感词过滤 2017-04-29 02:02:02
 * @author 熊诗言
 */
public class SimpleWordFilter extends AbstractSensitiveWordFilter implements SensitiveWordFilter {
    @SuppressWarnings("rawtypes")
    private Map             sensitiveWordMap = null;
    /** 最小匹配规则 */
    public static final int MIN_MATCH_TYPE = 1;
    /** 最大匹配规则 */
    public static final int MAX_MATCH_TYPE = 2;

    private int             matchType        = MIN_MATCH_TYPE;
    private String          replaceChar      = "*";

    /**
     * 构造函数，初始化敏感词库，字符集用默认的GBK
     */
    public SimpleWordFilter(){}

    public SimpleWordFilter(String replaceChar){
        this.replaceChar = replaceChar;
    }

    public SimpleWordFilter(int matchType, String replaceChar){
        this.matchType = matchType;
        this.replaceChar = replaceChar;
    }

    @Override
    public void init(Set<String> sensitiveWords){
        sensitiveWordMap = new SensitiveWordInit().initKeyWord(sensitiveWords);
        isInit = true;
    }

    @Override
    public String doFilter(String src){
        checkInit();
        if(null == src || "".equals(src)){
            return src;
        }

        String resultTxt = src;
        Set<String> set = doGetSensitiveWords(src); // 获取所有的敏感词
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while(iterator.hasNext()){
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }

        return resultTxt;
    }

    @Override
    public boolean isContains(String src){
        checkInit();
        if(null == src || "".equals(src)){
            return false;
        }
        boolean flag = false;
        for(int i = 0; i < src.length(); i++){
            int matchFlag = this.checkSensitiveWord(src, i, matchType); // 判断是否包含敏感字符
            if(matchFlag > 0){ // 大于0存在，返回true
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Set<String> doGetSensitiveWords(String src){
        Set<String> sensitiveWordSet = new HashSet<String>();
        checkInit();
        if(null == src || "".equals(src)){
            return sensitiveWordSet;
        }

        for(int i = 0; i < src.length(); i++){
            int length = checkSensitiveWord(src, i, matchType); // 判断是否包含敏感字符
            if(length > 0){ // 存在,加入list中
                sensitiveWordSet.add(src.substring(i, i + length));
                i = i + length - 1; // 减1的原因，是因为for会自增
            }
        }

        return sensitiveWordSet;
    }

    /**
     * 获取替换字符串
     * 
     * @param replaceChar
     * @param length
     * @return
     * @version 1.0
     */
    private String getReplaceChars(String replaceChar, int length){
        String resultReplace = replaceChar;
        for(int i = 1; i < length; i++){
            resultReplace += replaceChar;
        }

        return resultReplace;
    }

    /**
     * 检查文字中是否包含敏感字符，检查规则如下：<br>
     * 
     * @Author : 熊诗言
     * @Date : 2017-04-29 02:02:02
     * @param txt
     * @param beginIndex
     * @param matchType
     * @return，如果存在，则返回敏感词字符的长度，不存在返回0
     * @version 1.0
     */
    @SuppressWarnings({ "rawtypes" })
    private int checkSensitiveWord(String txt, int beginIndex, int matchType){
        boolean flag = false; // 敏感词结束标识位：用于敏感词只有1位的情况
        int matchFlag = 0; // 匹配标识数默认为0
        char word = 0;
        Map nowMap = sensitiveWordMap;
        for(int i = beginIndex; i < txt.length(); i++){
            word = txt.charAt(i);
            nowMap = (Map)nowMap.get(word); // 获取指定key
            if(nowMap != null){ // 存在，则判断是否为最后一个
                matchFlag++; // 找到相应key，匹配标识+1
                if(SensitiveWordInit.IS_END_1.equals(nowMap.get(SensitiveWordInit.IS_END))){ // 如果为最后一个匹配规则,结束循环，返回匹配标识数
                    flag = true; // 结束标志位为true
                    if(SimpleWordFilter.MIN_MATCH_TYPE == matchType){ // 最小规则，直接返回,最大规则还需继续查找
                        break;
                    }
                }
            } else{ // 不存在，直接返回
                break;
            }
        }
        if(matchFlag < 2 || !flag){ // 长度必须大于等于1，为词
            matchFlag = 0;
        }
        return matchFlag;
    }
}
