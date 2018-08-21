package top.jfunc.common.sensitiveword.impl.stop;

import top.jfunc.common.sensitiveword.AbstractSensitiveWordFilter;
import top.jfunc.common.sensitiveword.SensitiveWordFilter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 创建时间：2016年8月30日 下午3:01:12
 * 思路： 创建一个FilterSet，枚举了0~65535的所有char是否是某个敏感词开头的状态
 * 判断是否是 敏感词开头 | | 是 不是 获取头节点 OK--下一个字 然后逐级遍历，DFA算法
 * 使用时，必须初始化，即调用init接口，传入需要的参数
 * @author andy
 * @version 2.2
 */
public class WithStopFilter extends AbstractSensitiveWordFilter implements SensitiveWordFilter {

    private final FilterSet set         = new FilterSet();                         // 存储首字
    private final Map<Integer, WordNode> nodes       = new HashMap<Integer, WordNode>(1024, 1); // 存储节点
    private final Set<Integer>           stopwdSet   = new HashSet<>();                         // 停顿词
    private char                         replaceChar = '*';                                     // 敏感词过滤替换

    private static final Set<String> STOP_WORDS = new HashSet<String>();

    static{
        STOP_WORDS.add("!");
        STOP_WORDS.add(".");
        STOP_WORDS.add(",");
        STOP_WORDS.add("#");
        STOP_WORDS.add("$");
        STOP_WORDS.add("%");
        STOP_WORDS.add("&");
        STOP_WORDS.add("*");
        STOP_WORDS.add("(");
        STOP_WORDS.add(")");
        STOP_WORDS.add("|");
        STOP_WORDS.add("?");
        STOP_WORDS.add("/");
        STOP_WORDS.add("@");
        STOP_WORDS.add("\"");
        STOP_WORDS.add("'");
        STOP_WORDS.add(";");
        STOP_WORDS.add("[");
        STOP_WORDS.add("]");
        STOP_WORDS.add("{");
        STOP_WORDS.add("}");
        STOP_WORDS.add("+");
        STOP_WORDS.add("~");
        STOP_WORDS.add("-");
        STOP_WORDS.add("_");
        STOP_WORDS.add("=");
        STOP_WORDS.add("^");
        STOP_WORDS.add("<");
        STOP_WORDS.add(">");
        STOP_WORDS.add("");
        STOP_WORDS.add("！");
        STOP_WORDS.add("。");
        STOP_WORDS.add("，");
        STOP_WORDS.add("￥");
        STOP_WORDS.add("（");
        STOP_WORDS.add("）");
        STOP_WORDS.add("？");
        STOP_WORDS.add("、");
        STOP_WORDS.add("“");
        STOP_WORDS.add("‘");
        STOP_WORDS.add("；");
        STOP_WORDS.add("【");
        STOP_WORDS.add("】");
        STOP_WORDS.add("——");
        STOP_WORDS.add("……");
        STOP_WORDS.add("《");
        STOP_WORDS.add("》");
    }

    public WithStopFilter(){}

    public WithStopFilter(char replaceChar){
        this.replaceChar = replaceChar;
    }

    /**
     * 
     * 默认停顿词
     * @param sensitiveWords 敏感词
     */
    @Override
    public void init(Set<String> sensitiveWords){
        addSensitiveWord(sensitiveWords);
        // 为了方便，直接加载在内存中
        // addStopWord(new FileFilterWordSet("stopwd.txt").getWordSet());
        addStopWord(STOP_WORDS);
        isInit = true;
    }

    /**
     * 过滤判断 将敏感词转化为成屏蔽词
     * @param src 原字符串
     * @return 过滤后的
     */
    @Override
    public final String doFilter(final String src){
        checkInit();
        if(null == src || "".equals(src)){
            return src;
        }

        char[] chs = src.toCharArray();
        int length = chs.length;
        int currc;
        int k;
        WordNode node;
        for(int i = 0; i < length; i++){
            currc = charConvert(chs[i]);
            if(!set.contains(currc)){
                continue;
            }
            node = nodes.get(currc);// 日 2
            if(node == null){// 其实不会发生，习惯性写上了
                continue;
            }
            boolean couldMark = false;
            int markNum = -1;
            if(node.isLast()){// 单字匹配（日）
                couldMark = true;
                markNum = 0;
            }
            // 继续匹配（日你/日你妹），以长的优先
            // 你-3 妹-4 夫-5
            k = i;
            for(; ++k < length;){
                int temp = charConvert(chs[k]);
                if(stopwdSet.contains(temp)){
                    continue;
                }
                node = node.querySub(temp);
                if(node == null){// 没有了
                    break;
                }
                if(node.isLast()){
                    couldMark = true;
                    markNum = k - i;// 3-2
                }
            }
            if(couldMark){
                for(k = 0; k <= markNum; k++){
                    chs[k + i] = replaceChar;
                }
                i = i + markNum;
            }
        }

        return new String(chs);
    }

    @Override
    public Set<String> doGetSensitiveWords(String src){

        Set<String> words = new HashSet<String>();// 装含有的敏感词

        checkInit();
        if(null == src || "".equals(src)){
            return words;
        }

        char[] chs = src.toCharArray();
        int length = chs.length;
        int currc;
        int k;
        WordNode node;
        for(int i = 0; i < length; i++){
            currc = charConvert(chs[i]);
            if(!set.contains(currc)){
                continue;
            }
            node = nodes.get(currc);// 日 2
            if(node == null){// 其实不会发生，习惯性写上了
                continue;
            }
            boolean couldMark = false;
            int markNum = -1;
            if(node.isLast()){// 单字匹配（日）
                couldMark = true;
                markNum = 0;
            }
            // 继续匹配（日你/日你妹），以长的优先
            // 你-3 妹-4 夫-5
            k = i;
            for(; ++k < length;){
                int temp = charConvert(chs[k]);
                if(stopwdSet.contains(temp)) {
                    continue;
                }
                node = node.querySub(temp);
                if(node == null) {// 没有了
                    break;
                }
                if(node.isLast()){
                    couldMark = true;
                    markNum = k - i;// 3-2
                }
            }
            if(couldMark){
                String sensitive = new String(chs, i, markNum + 1);
                words.add(sensitive);
                i = i + markNum;// 跳过本敏感词的字数
            }
        }

        return words;
    }

    /**
     * 是否包含敏感词
     * 
     * @param src 原字符串
     * @return 是否包含
     */
    @Override
    public final boolean isContains(final String src){
        checkInit();
        if(null == src || "".equals(src)){
            return false;
        }

        char[] chs = src.toCharArray();
        int length = chs.length;
        int currc;
        int k;
        WordNode node;
        for(int i = 0; i < length; i++){
            currc = charConvert(chs[i]);
            if(!set.contains(currc)){
                continue;
            }
            node = nodes.get(currc);// 日 2
            if(node == null) {// 其实不会发生，习惯性写上了
                continue;
            }
            boolean couldMark = false;
            if(node.isLast()){// 单字匹配（日）
                couldMark = true;
            }
            // 继续匹配（日你/日你妹），以长的优先
            // 你-3 妹-4 夫-5
            k = i;
            for(; ++k < length;){
                int temp = charConvert(chs[k]);
                if(stopwdSet.contains(temp)) {
                    continue;
                }
                node = node.querySub(temp);
                if(node == null) {// 没有了
                    break;
                }
                if(node.isLast()){
                    couldMark = true;
                }
            }
            if(couldMark){
                return true;
            }
        }

        return false;
    }

    /**
     * 增加停顿词
     * @param words 停顿词
     */
    private void addStopWord(final Set<String> words){
        if(words != null && words.size() > 0){
            char[] chs;
            for(String curr : words){
                chs = curr.toCharArray();
                for(char c : chs){
                    stopwdSet.add(charConvert(c));
                }
            }
        }
    }

    /**
     * 添加DFA节点
     * @param words 词
     */
    private void addSensitiveWord(final Set<String> words){
        if(words != null && words.size() > 0){
            char[] chs;
            int fchar;
            int lastIndex;
            WordNode fnode; // 首字母节点
            for(String curr : words){
                chs = curr.toCharArray();
                fchar = charConvert(chs[0]);
                if(!set.contains(fchar)){// 没有首字定义
                    set.add(fchar);// 首字标志位 可重复add,反正判断了，不重复了
                    fnode = new WordNode(fchar, chs.length == 1);
                    nodes.put(fchar, fnode);
                } else{
                    fnode = nodes.get(fchar);
                    if(!fnode.isLast() && chs.length == 1) {
                        fnode.setLast(true);
                    }
                }
                lastIndex = chs.length - 1;
                for(int i = 1; i < chs.length; i++){
                    fnode = fnode.addIfNoExist(charConvert(chs[i]), i == lastIndex);
                }
            }
        }
    }

    /**
     * 大写转化为小写 全角转化为半角
     * 
     * @param src 原
     * @return 转换
     */
    private static int charConvert(char src){
        int r = BCConvert.qj2bj(src);
        return (r >= 'A' && r <= 'Z') ? r + 32 : r;
    }
}