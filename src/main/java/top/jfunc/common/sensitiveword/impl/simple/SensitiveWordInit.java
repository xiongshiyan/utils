package top.jfunc.common.sensitiveword.impl.simple;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 * @author  : 熊诗言 2017-04-29 02:02:02
 */
public class SensitiveWordInit{
    public static final String IS_END = "IS_END";

    public static final String IS_END_1 = "1";
    public static final String IS_END_0 = "0";
    @SuppressWarnings("rawtypes")
    public HashMap             sensitiveWordMap;

    public SensitiveWordInit(){}

    /**
     * @Author : 熊诗言
     * @Date : 2017-04-29 02:02:02
     * @version 1.0
     */
    @SuppressWarnings("rawtypes")
    public Map initKeyWord(Set<String> keyWordSet){
        // 将敏感词库加入到HashMap中
        addSensitiveWordToHashMap(keyWordSet);
        // spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
        return sensitiveWordMap;
    }

    /**
     * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
     * 中 = { IS_END = 0 国 = {<br>
     * IS_END = 1 人 = {IS_END = 0 民 = {IS_END = 1} } 男 = { IS_END = 0 人 = { IS_END =
     * 1 } } } } 五 = { IS_END = 0 星 = { IS_END = 0 红 = { IS_END = 0 旗 = { IS_END = 1
     * } } } }
     * 
     * @Author : 熊诗言
     * @Date : 2017-04-29 02:02:02
     * @param keyWordSet
     *            敏感词库
     * @version 1.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void addSensitiveWordToHashMap(Set<String> keyWordSet){
        sensitiveWordMap = new HashMap(keyWordSet.size()); // 初始化敏感词容器，减少扩容操作
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        // 迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()){
            key = iterator.next(); // 关键字
            nowMap = sensitiveWordMap;
            for(int i = 0; i < key.length(); i++){
                char keyChar = key.charAt(i); // 转换成char型
                Object wordMap = nowMap.get(keyChar); // 获取

                if(wordMap != null){ // 如果存在该key，直接赋值
                    nowMap = (Map)wordMap;
                } else{ // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String, String>();
                    newWorMap.put(IS_END, IS_END_0); // 不是最后一个
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if(i == key.length() - 1){
                    nowMap.put(IS_END, IS_END_1); // 最后一个
                }
            }
        }
    }
}
