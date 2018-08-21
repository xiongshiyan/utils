package top.jfunc.common.utils;

import org.junit.Ignore;
import org.junit.Test;
import top.jfunc.common.sensitiveword.FilterWordSet;
import top.jfunc.common.sensitiveword.SensitiveWordFilter;
import top.jfunc.common.sensitiveword.impl.simple.SimpleWordFilter;
import top.jfunc.common.sensitiveword.impl.stop.WithStopFilter;
import top.jfunc.common.sensitiveword.wordset.FileFilterWordSet;

import java.io.File;
import java.util.Set;

/**
 * @author 熊诗言
 * @date 2017/11/23
 */
public class SensitiveTest {
    @Test@Ignore
    public void testSimple(){
        FilterWordSet filterSensitiveWordSet = new FileFilterWordSet(new File("C:\\Users\\xiongshiyan\\Desktop\\wd.txt"));
        SensitiveWordFilter filter = new SimpleWordFilter("#");
        filter.init(filterSensitiveWordSet.getWordSet());
        testIt(filter);
    }

    @Test@Ignore
    public void testStop(){
        SensitiveWordFilter filter = new WithStopFilter('#');
        FilterWordSet filterSensitiveWordSet = new FileFilterWordSet(new File("C:\\Users\\xiongshiyan\\Desktop\\wd.txt"));
        filter.init(filterSensitiveWordSet.getWordSet());
        testIt(filter);
    }
    private void testIt(SensitiveWordFilter filter){
        String string = "你是逗比吗？ｆｕｃｋ！fUcK,你竟然用法轮功，法@!轮!%%%功，太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
        System.out.println("=========================================================");
        System.out.println("原字符串 : " + string);
        System.out.println("解析字数 : " + string.length());
        String re;
        long nano = System.nanoTime();
        nano = (System.nanoTime() - nano);
        System.out.println("加载时间 : " + nano / 1000000 + " ms");
        System.out.println("=========================================================");

        nano = System.nanoTime();
        re = filter.doFilter(string);
        nano = (System.nanoTime() - nano);
        System.out.println("输出替换结果 : " + re);
        System.out.println("解析时间 : " + nano / 1000000 + " ms");
        System.out.println("=========================================================");

        nano = System.nanoTime();
        System.out.println("是否包含敏感词: " + filter.isContains(string));
        nano = (System.nanoTime() - nano);
        System.out.println("消耗时间 : " + nano / 1000000 + " ms");
        System.out.println("=========================================================");

        nano = System.nanoTime();
        Set<String> set = filter.doGetSensitiveWords(string);
        System.out.println("语句中包含敏感词的个数为 :" + set.size() + "。包含：" + set);
        nano = (System.nanoTime() - nano);
        System.out.println("消耗时间 : " + nano / 1000000 + " ms");
    }
}
