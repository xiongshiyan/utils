package top.jfunc.common.utils;

import java.util.Map;

/**
 * @author xiongshiyan at 2019/5/21 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class MapUtil {
    private MapUtil(){}

    /**
     * 合并两个Map，如果一个为{@code null}就返回另外一个
     * @param first 第一个
     * @param second 第二个
     * @param <K> K
     * @param <V> V
     * @return 合并后的Map
     */
    public static <K , V> MultiValueMap<K , V> mergeMap(MultiValueMap<K , V> first , MultiValueMap<K , V> second){
        if(null == first){
            return second;
        }
        if(null == second){
            return first;
        }
        second.forEachKeyValue(first::add);

        return first;
    }

    /**
     * 合并两个Map，如果一个为{@code null}就返回另外一个
     * @param first 第一个
     * @param second 第二个
     * @param holdFirst 同样的key的时候first覆盖second的 if true
     * @param <K> K
     * @param <V> V
     * @return 合并后的Map
     */
    public static <K , V> Map<K , V> mergeMap(Map<K , V> first , Map<K , V> second , boolean holdFirst){
        if(null == first){
            return second;
        }
        if(null == second){
            return first;
        }
        //保留第一个：同样的key的时候first覆盖second的
        if(holdFirst){
            first.forEach(second::put);
            return second;
        }else {
            second.forEach(first::put);
            return first;
        }
    }
}
