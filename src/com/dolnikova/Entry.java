package com.dolnikova;

public class Entry<K extends Comparable<?>, V> {

    K key;
    V value;
    Entry<K, V> left;
    Entry<K, V> right;

    public K getKey() {
        return key;
    }

}