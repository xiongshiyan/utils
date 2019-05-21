package top.jfunc.common.utils;

import java.util.Collection;

/**
 * @author xiongshiyan at 2019/5/21 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class CollectionUtil {
    private CollectionUtil(){}

    public static <E , T extends Collection<E>> T merge(T t1 , T t2){
        if(null == t1){return t2;}
        if(null == t2){return t1;}

        t1.addAll(t2);
        return t1;
    }
}
