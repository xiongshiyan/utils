package top.jfunc.common.utils;

import java.io.Serializable;
import java.util.*;

/**
 * Simple implementation of {@link MultiValueMap} that wraps a {@link HashMap},
 * storing multiple values in a {@link ArrayList}.
 *
 * <p>This Map implementation is generally not thread-safe. It is primarily designed
 * for data structures exposed from request objects, for use in a single thread only.
 *
 * @author xiongshiyan
 */
public class ArrayListMultiValueMap<K, V> implements MultiValueMap<K, V>, Serializable, Cloneable {

	private static final long serialVersionUID = 3801124242820219131L;

	private final Map<K, List<V>> targetMap;


	/**
	 * Create a new LinkedMultiValueMap that wraps a {@link HashMap}.
	 */
	public ArrayListMultiValueMap() {
		this.targetMap = new HashMap<>();
	}

	/**
	 * Create a new LinkedMultiValueMap that wraps a {@link HashMap}
	 * with the given initial capacity.
	 * @param initialCapacity the initial capacity
	 */
	public ArrayListMultiValueMap(int initialCapacity) {
		this.targetMap = new HashMap<>(initialCapacity);
	}

	/**
	 * Copy constructor: Create a new LinkedMultiValueMap with the same mappings as
	 * the specified Map. Note that this will be a shallow copy; its value-holding
	 * List entries will get reused and therefore cannot get modified independently.
	 * @param otherMap the Map whose mappings are to be placed in this Map
	 * @see #clone()
	 * @see #deepCopy()
	 */
	public ArrayListMultiValueMap(Map<K, List<V>> otherMap) {
		this.targetMap = new HashMap<>(otherMap);
	}


	// MultiValueMap implementation

    /**
     * //无论values存在不（不存在就新建一个），都加进去
     * @param key the key
     * @param value the value to be added
     * @see this#put(Object, List)
     */
	@Override
	public void add(K key, V value) {
		List<V> values = this.targetMap.get(key);

		if (null == values) {
			values = new ArrayList<>(1);
			this.targetMap.put(key, values);
		}
		values.add(value);
	}

	@Override
	public V getFirst(K key) {
		List<V> values = this.targetMap.get(key);
		return (values != null ? values.get(0) : null);
	}

    /**
     * 覆盖性的
     * @param key the key
     * @param value the value to set
     */
	@Override
	public void set(K key, V value) {
		List<V> values = new ArrayList<>(1);
		values.add(value);
		this.targetMap.put(key, values);
	}

	@Override
	public void setAll(Map<K, V> values) {
		for (Entry<K, V> entry : values.entrySet()) {
			set(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public Map<K, V> toSingleValueMap() {
		Map<K, V> singleValueMap = new HashMap<>(this.targetMap.size());
		for (Entry<K, List<V>> entry : this.targetMap.entrySet()) {
			singleValueMap.put(entry.getKey(), entry.getValue().get(0));
		}
		return singleValueMap;
	}

	@Override
	public Map<K, List<V>> getMap() {
		return this.targetMap;
	}

	// Map implementation

	@Override
	public int size() {
		return this.targetMap.size();
	}

	@Override
	public boolean isEmpty() {
		return this.targetMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return this.targetMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.targetMap.containsValue(value);
	}

	@Override
	public List<V> get(Object key) {
		return this.targetMap.get(key);
	}

	@Override
	public List<V> put(K key, List<V> value) {
		return this.targetMap.put(key, value);
	}

	@Override
	public List<V> remove(Object key) {
		return this.targetMap.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends List<V>> map) {
		this.targetMap.putAll(map);
	}

	@Override
	public void clear() {
		this.targetMap.clear();
	}

	@Override
	public Set<K> keySet() {
		return this.targetMap.keySet();
	}

	@Override
	public Collection<List<V>> values() {
		return this.targetMap.values();
	}

	@Override
	public Set<Entry<K, List<V>>> entrySet() {
		return this.targetMap.entrySet();
	}


	public MultiValueMap<K, V> deepCopy() {
		ArrayListMultiValueMap<K, V> copy = new ArrayListMultiValueMap<>(this.targetMap.size());
		for (Entry<K, List<V>> entry : this.targetMap.entrySet()) {
			copy.put(entry.getKey(), new LinkedList<>(entry.getValue()));
		}
		return copy;
	}

	/**
	 * Create a regular copy of this Map.
	 * @return a shallow copy of this Map, reusing this Map's value-holding List entries
	 * @since 4.2
	 * @see ArrayListMultiValueMap#ArrayListMultiValueMap() (Map)
	 * @see #deepCopy()
	 */
	@Override
	public ArrayListMultiValueMap<K, V> clone() {
		return new ArrayListMultiValueMap<>(this);
	}

	@Override
	public boolean equals(Object obj) {
		return this.targetMap.equals(obj);
	}

	@Override
	public int hashCode() {
		return this.targetMap.hashCode();
	}

	@Override
	public String toString() {
		return this.targetMap.toString();
	}


	/**
	 * 从普通map转换而来
	 */
	public static <K1,V1> MultiValueMap<K1,V1> fromMap(Map<K1,V1> map){
		if(null == map){
			return null;
		}
		MultiValueMap<K1, V1> mapList = new ArrayListMultiValueMap<>(map.size());
		map.forEach(mapList::add);
		return mapList;
	}
}
