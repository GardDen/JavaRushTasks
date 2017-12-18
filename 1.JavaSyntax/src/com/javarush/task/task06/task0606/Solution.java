package com.javarush.task.task06.task0606;

import java.io.*;
import java.util.Scanner;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();
        char[] ch = number.toCharArray();
        for (int i = 0; i < ch.length; i++){
            if ((int)ch[i]%2==0) {
                even++;
            }
            else {
                odd++;
            }
        }
        System.out.printf("Even: %d Odd: %d", even, odd);
    }
}
