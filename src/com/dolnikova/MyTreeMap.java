package com.dolnikova;

import java.util.ArrayList;
import java.util.List;

public class MyTreeMap<K extends Comparable<K>, V> implements TreeMap<K, V>, Comparable<Entry<K, V>> {

    private Entry<K, V> root;
    private int count = 0;

    @Override
    public void put(K key, V val) {
        Entry treeNode = new Entry();
        treeNode.key = key;
        treeNode.value = val;

        if (root == null) {
            root = treeNode;
        } else {
            addRecursive(root, treeNode);
        }

        count++;
    }

    private void addRecursive(Entry root, Entry treeNode) {

        if (treeNode.key.compareTo(root.key) < 0) {
            if (root.left == null) {
                root.left = treeNode;
            } else {
                root = root.left;
                addRecursive(root, treeNode);
            }
        } else if (treeNode.key.compareTo(root.key) > 0) {
            if (root.right == null) {
                root.right = treeNode;
            } else {
                root = root.right;
                addRecursive(root, treeNode);
            }
        }
    }


    @Override
    public V getVal(K key) {
        getValRecursive(root, key);
        return getValRecursive(root, key);
    }

    private V getValRecursive(Entry current, K key) {
        if (current == null) {
            return null;
        }
        if (current.key == key) {
            return (V) current.value;
        }

        return current.key.compareTo(key) >= 0
                ? getValRecursive(current.left, key)
                : getValRecursive(current.right, key);
    }

    @Override
    public List<V> allValues() {
        if (!isEmpty()) {
            List<V> list = new ArrayList<>();
            allValuesRecursive(root, list);
            return list;
        }
        return null;
    }

    private void allValuesRecursive(Entry current, List<V> list) {
        if (current != null) {
            list.add((V) current.value);
        }

        if (current.left != null) {
            allValuesRecursive(current.left, list);
        }
        if (current.right != null) {
            allValuesRecursive(current.right, list);
        }
    }


    @Override
    public K getKey(V val) {
        List<K> list = new ArrayList<>();
        getKeyRecursive(root, val, list);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                return list.get(i);
            }
        }

        return null;
    }

    private void getKeyRecursive(Entry entry, V val, List<K> list) {
        if (entry == null) {
            list.add(null);
        } else if (entry.value == val) {
            list.add((K) entry.key);
        } else {
            getKeyRecursive(entry.left, val, list);
            getKeyRecursive(entry.right, val, list);
        }
    }

    @Override
    public List<K> allKeys() {
        if (isEmpty() == false) {
            List<K> list = new ArrayList<>();
            allKeysRecursive(root, list);
            return list;
        }
        return null;
    }

    private void allKeysRecursive(Entry current, List<K> list) {
        if (current != null) {
            list.add((K) current.key);
        }

        if (current.left != null) {
            allKeysRecursive(current.left, list);
        }
        if (current.right != null) {
            allKeysRecursive(current.right, list);
        }
    }


    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void clear() {
        count = 0;
        root = null;
    }


    @Override
    public void remove(K key) {
        deleteRecursive(root, key);
    }

    private Entry deleteRecursive(Entry current, K key) {
        if (current == null) {
            return null;
        }

        if (current.key == key) {
            if (current.left == null && current.right == null) {
                return null;
            } else if (current.right == null && current.left != null) {
                return current.left;
            } else if (current.left == null && current.right != null) {
                return current.right;
            } else {

                Entry entry = new Entry();

                if (current.right != null) {
                    entry = current.right;
                } else if (current.left != null) {
                    entry = current.left;
                }

                current.key = entry.key;
                current.value = entry.value;
                current.right = deleteRecursive(current.right, (K) entry.key);
                current.left = deleteRecursive(current.left, (K) entry.key);

                return current;
            }
        }

        // key < current.key
        if (current.key.compareTo(key) >= 0) {
            current.left = deleteRecursive(current.left, key);
            return current;
        }
        current.right = deleteRecursive(current.right, key);
        return current;
    }


    @Override
    public boolean containsKey(K key) {
        return containsKeyRecursive(root, key);
    }

    private boolean containsKeyRecursive(Entry current, K key) {
        if (current == null) {
            return false;
        }
        if (current.key == key) {
            return true;
        }

        return current.key.compareTo(key) >= 0
                ? containsKeyRecursive(current.left, key)
                : containsKeyRecursive(current.right, key);
    }

    @Override
    public boolean containsValue(V value) {
        List<Boolean> list = new ArrayList<>();
        containsValueRecursive(root, value, list);

        if (list.contains(true)) {
            return true;
        } else {
            return false;
        }
    }

    private void containsValueRecursive(Entry current, V value, List<Boolean> list) {
        if (current == null) {
            list.add(false);
        } else if (current.value == value) {
            list.add(true);
        } else {
            containsValueRecursive(current.left, value, list);
            containsValueRecursive(current.right, value, list);
        }
    }


    public void print() {
        BTreePrinter.printNode(root);
    }

    public K getKey() {
        return (K) root.key;
    }

    @Override
    public int compareTo(Entry<K, V> o) {
        return getKey().compareTo(o.getKey());
    }
}
