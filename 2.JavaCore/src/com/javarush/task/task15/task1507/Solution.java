package com.javarush.task.task15.task1507;

/* 
ООП - Перегрузка
*/

public class Solution {
    public static void main(String[] args) {
        printMatrix(2, 3, "8");
    }
    //1
    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }
    //2
    public static void printMatrix(int m, int n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
    }
    //3
    public static void printMatrix(int m, int n){
        printMatrix(m, n, "8");
    }
    //4
    public static void printMatrix(int m){
        printMatrix(m, 3, "8");
    }
    //5
    public static void printMatrix(String value){
        printMatrix(2, 3, value);
    }
    //6
    public static void printMatrix(Object value){
        printMatrix(2, 3, value);
    }
    //7
    public static void printMatrix(int m, String value){
        printMatrix(m, 3, value);
    }
    //8
    public static void printMatrix(int m, Object value){
        printMatrix(m, 3, value);
    }
    //9
    public static void printMatrix(String value_m, String value){
        printMatrix(Integer.parseInt(value_m), value);
    }
    //10
    public static void printMatrix(String value_m, String value_n, String value){
        printMatrix(Integer.parseInt(value_m), Integer.parseInt(value_n), value);
    }
}
