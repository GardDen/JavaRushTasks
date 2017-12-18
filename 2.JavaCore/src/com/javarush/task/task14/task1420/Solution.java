package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n1 = Integer.parseInt(reader.readLine()) , n2 = Integer.parseInt(reader.readLine()), nod = 0;
        if (n1 <= 0 || n2 <= 0) { throw new NumberFormatException(); }
        for (int i = 1; i <= (n1 < n2 ? n1 : n2); i++ ) { if ((n1 % i == 0) && (n2 % i == 0)) nod = i; }
        System.out.println(nod);
    }
}
