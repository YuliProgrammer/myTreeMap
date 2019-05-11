package com.dolnikova;

import java.util.List;

public interface TreeMap<K,V> {
    void put(K key, V val);

    V getVal(K key);
    List<V> allValues();

    K getKey(V val);
    List<K> allKeys();

    int size();
    boolean isEmpty();
    void clear();
    void remove(K key);

    boolean containsKey(K key);
    boolean containsValue(V value);

}
