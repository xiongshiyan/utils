package top.jfunc.common.utils.local;

import java.util.HashMap;
import java.util.Map;

/**
 * Map类型值的工具类
 * @author xiongshiyan at 2017/12/27
 */
public class MapInfoHolder<K,V> extends InfoHolder<Map<K,V>> {

    /**
     * 往当前线程保存一对值
     * @param key key
     * @param value value
     */
    public void add(K key ,V value){
        Map<K, V> map = holder.get();
        if(null == map){
            map = new HashMap<>();
        }
        map.put(key,value);
        holder.set(map);
    }

    /**
     * 根据key获取当前线程的值
     * @param key key
     */
    public V get(K key){
        Map<K, V> map = holder.get();
        if (null == map){
            return null;
        }
        return map.get(key);
    }

    public void remove(K key){
        Map<K, V> map = holder.get();
        if (null == map){
            return ;
        }
        map.remove(key);
    }
}
