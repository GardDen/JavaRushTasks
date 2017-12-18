package com.javarush.task.task26.task2601;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

    }
    /*ArrayList<Integer> list = new ArrayList<>(Arrays.asList(sort(new Integer[]{13, 8, 15, 5, 17, 13})));
            for (Integer item : list) {
                System.out.println(item + " ");
            }*/
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        int mediana;
        if (list.size() % 2 == 0) {
            mediana = (list.get(list.size()/2 - 1) + list.get(list.size()/2))/2;
        } else {
            mediana = list.get(list.size()/2);
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(Math.abs(mediana - o1), Math.abs(mediana - o2));
            }
        });
        return list.toArray(new Integer[list.size()]);
    }
}
