package top.jfunc.common.utils;

import java.util.Collection;

/**
 * @author xiongshiyan at 2019/5/21 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class CollectionUtil {
    private CollectionUtil(){}

    /**
     * 判断一个集合是否为空
     * @param c 待判断集合
     * @return true if null or 0==size()
     */
    public static <E> boolean isEmpty(Collection<E> c){
        return null == c || 0 == c.size();
    }
    /**
     * 判断一个集合是否非空
     * @param c 待判断集合
     * @return true if not null && size()>0
     */
    public static <E> boolean notEmpty(Collection<E> c){
        return !isEmpty(c);
    }

    /**
     * 合并两个集合
     */
    public static <E , T extends Collection<E>> T merge(T t1 , T t2){
        if(null == t1){return t2;}
        if(null == t2){return t1;}

        t1.addAll(t2);
        return t1;
    }
}
