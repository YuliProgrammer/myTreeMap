package com.dolnikova;

public class Main {

    public static void main(String[] args) {
        MyTreeMap<Integer, String> treeMap = new MyTreeMap<>();
        treeMap.put(1, "java");
        treeMap.put(-1, "Yuli");
        treeMap.put(-2, "c");
        treeMap.put(-3, "12");
        treeMap.put(7, "love");
        treeMap.put(4, "55");
        treeMap.put(8, "3");
        treeMap.put(10, "smile");
        treeMap.put(11, "-10");
        treeMap.put(9, ":)");
        treeMap.put(12, "-");
        treeMap.put(5, "-22");
        treeMap.put(2, "Alex");
        treeMap.put(6, "7");
        treeMap.put(3, "-6");

        System.out.println("TreeMap #1 : ");
        treeMap.print();

        System.out.println("size = " + treeMap.size());
        System.out.println("is Empty? " + treeMap.isEmpty());

        System.out.println("containsKey: " + treeMap.containsKey(-1));
        System.out.println("containsValue: " + treeMap.containsValue("7"));

        System.out.println("value by key: " + treeMap.getVal(7));
        System.out.println("key by value: " + treeMap.getKey("7"));

        System.out.println("all keys: " + treeMap.allKeys());
        System.out.println("all values: " + treeMap.allValues());

        treeMap.remove(7);
        System.out.println();
        System.out.println("TreeMap #2 : ");
        treeMap.print();

        treeMap.remove(6);
        System.out.println("TreeMap #3 : ");
        treeMap.print();

        treeMap.remove(11);
        System.out.println("TreeMap #4 : ");
        treeMap.print();

        treeMap.clear();
        System.out.println("all keys: " + treeMap.allKeys());
        System.out.println("all values: " + treeMap.allValues());
        System.out.println("TreeMap #5 : ");
        treeMap.print();
    }
}
