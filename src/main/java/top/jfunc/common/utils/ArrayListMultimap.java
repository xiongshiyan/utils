package top.jfunc.common.utils;

import java.util.*;

/**
 * 自己实现的ArrayListMultimap 重复key的map.
 * 目前使用的地方 ：
 * 1.使用监听的type，取出所有的监听器
 * 2.Http请求封装参数和header
 * @author L.cm email: 596392912@qq.com site:http://www.dreamlu.net date 2015年6月25日下午8:36:17
 */
public class ArrayListMultimap<K, V> {

    private transient final Map<K, List<V>> map;

    public ArrayListMultimap(){
        map = new HashMap<>();
    }
    public ArrayListMultimap(int capacity){
        map = new HashMap<>(capacity);
    }

    private static <V1> List<V1> createList(){
        return new ArrayList<>();
    }

    /**
     * put to ArrayListMultimap
     * @param key 键
     * @param value 值
     * @return boolean
     */
    public boolean put(K key, V value){
        List<V> list = map.get(key);
        if(list == null){
            list = createList();
            if(list.add(value)){
                map.put(key, list);
                return true;
            } else{
                throw new AssertionError("New list violated the list spec");
            }
        } else if(list.add(value)){
            return true;
        } else{
            return false;
        }
    }

    public Map<K, List<V>> getMap() {
        return map;
    }

    /**
     * get List by key
     * @param key 键
     * @return List
     */
    public List<V> get(K key){
        List<V> list = map.get(key);
        if(list == null){
            list = createList();
        }
        return list;
    }

    /**
     * 获取第一个，对于只有一个值的来说
     */
    public V getFirst(K key){
        List<V> list = map.get(key);
        return (null == list || list.size() == 0) ? null : list.get(0);
    }

    public Set<K> keySet(){
        return map.keySet();
    }
    /**
     * clear ArrayListMultimap
     */
    public void clear(){
        map.clear();
    }

    /**
     * 从普通map转换而来
     */
    public static <K1,V1> ArrayListMultimap<K1,V1> fromMap(Map<K1,V1> map){
        if(null == map){
            return null;
        }
        ArrayListMultimap<K1, V1> mapList = new ArrayListMultimap<>(map.size());
        map.forEach(mapList::put);
        return mapList;
    }
    /**
     * 从MultiValueMap转换而来
     */
    public static <K1,V1> ArrayListMultimap<K1,V1> fromMap(MultiValueMap<K1,V1> map){
        if(null == map){
            return null;
        }
        ArrayListMultimap<K1, V1> mapList = new ArrayListMultimap<>(map.size());
        map.forEach((k1, v1s) -> v1s.forEach(v1 -> mapList.put(k1 , v1)));
        return mapList;
    }
}
