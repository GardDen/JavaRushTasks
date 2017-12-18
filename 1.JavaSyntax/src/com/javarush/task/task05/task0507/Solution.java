package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        int data = scanner.nextInt();
        while (data != -1){
            list.add(data);
            data = scanner.nextInt();
        }
        data = 0;
        for (int i = 0; i < list.size(); i++){
            data +=list.get(i);
        }

        System.out.println((double)data/list.size());
    }
}

