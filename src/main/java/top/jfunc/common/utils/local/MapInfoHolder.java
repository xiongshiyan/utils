package top.jfunc.common.utils.local;

import java.util.HashMap;
import java.util.Map;

/**
 * Map类型值的工具类
 * @author xiongshiyan at 2017/12/27
 */
public class MapInfoHolder<K,V> extends InfoHolder<Map<K,V>> {

    public MapInfoHolder(){
        //既然是保存k-v类型的数据，所以事先就初始化，在使用的时候就没必要判空了，
        //因为每个线程都是对应独立的map，所以并没有必要使用线程安全的集合类，比如ConcurrentHashMap、HashTable，
        //用普通的HashMap即可
        holder = ThreadLocal.withInitial(HashMap::new);
    }

    /**
     * 往当前线程保存一对值
     * @param key key
     * @param value value
     */
    public synchronized void add(K key ,V value){
        Map<K, V> map = holder.get();
        map.put(key,value);
        //holder.set(map);
    }

    /**
     * 根据key获取当前线程的值
     * @param key key
     */
    public synchronized V get(K key){
        Map<K, V> map = holder.get();
        return map.get(key);
    }

    public synchronized void remove(K key){
        Map<K, V> map = holder.get();
        map.remove(key);
    }

    @Override
    public synchronized void clear(){
        Map<K, V> map = holder.get();
        map.clear();

        super.clear();
    }
}
