package com.dolnikova;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTreePrinter {

    public static <K extends Comparable<?>, V> void printNode(Entry<K, V> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <K extends Comparable<?>, V> void printNodeInternal(List<Entry<K, V>> entries, int level, int maxLevel) {
        if (entries.isEmpty() || BTreePrinter.isAllElementsNull(entries))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Entry<K, V>> newEntries = new ArrayList<>();
        for (Entry<K, V> entry : entries) {
            if (entry != null) {
                System.out.print(entry.value);
                newEntries.add(entry.left);
                newEntries.add(entry.right);
            } else {
                newEntries.add(null);
                newEntries.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < entries.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (entries.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (entries.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (entries.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newEntries, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <K extends Comparable<?>, V> int maxLevel(Entry<K, V> entry) {
        if (entry == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(entry.left), BTreePrinter.maxLevel(entry.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}