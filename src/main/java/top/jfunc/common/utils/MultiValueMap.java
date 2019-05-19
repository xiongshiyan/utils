package top.jfunc.common.utils;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Extension of the {@code Map} interface that stores multiple values.
 * 在原来spring的基础上新增了一些特性
 * @author Arjen Poutsma
 * @author xiongshiyan
 * @since 3.0
 */
public interface MultiValueMap<K, V> extends Map<K, List<V>> {

    /**
     * Return the first value for the given key.
     * @param key the key
     * @return the first value for the specified key, or {@code null}
     */
    V getFirst(K key);
    /**
     * Return the last value for the given key.
     * @param key the key
     * @return the last value for the specified key, or {@code null}
     */
    V getLast(K key);

    /**
     * Add the given single value to the current list of values for the given key.
     * @param key the key
     * @param value the value to be added
     */
    void add(K key, V value);

    /**
     * Set the given single value under the given key.
     * @param key the key
     * @param value the value to set
     */
    void set(K key, V value);

    /**
     * Set the given values under.
     * @param values the values.
     */
    void setAll(Map<K, V> values);

    /**
     * Returns the first values contained in this {@code MultiValueMap}.
     * @return a single value representation of this map
     */
    Map<K, V> toSingleValueMap();

    /**
     * 获取Map，value为list
     * @return map
     */
    Map<K, List<V>> getMap();

    /**
     * 对每一个key、value遍历
     * @param action 动作
     */
    default void forEachKeyValue(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (Map.Entry<K, List<V>> entry : entrySet()) {
            K k;
            List<V> vs;
            try {
                k = entry.getKey();
                vs = entry.getValue();
            } catch(IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
            vs.forEach(v -> action.accept(k, v));
        }
    }
}
