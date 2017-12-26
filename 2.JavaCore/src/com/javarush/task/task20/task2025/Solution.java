package com.javarush.task.task20.task2025;

/*
Алгоритмы-числа
Число S состоит из M цифр, например, S=370 и M (количество цифр) = 3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания.

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти..
*/
public class Solution {
    public static long[] getNumbers(long S) {
        long[] result = new long[0];
        long[] armstrongNum = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474,
                54748, 92727, 93084, 548834, 1741725, 4210818, 9800817, 9926315,
                24678050, 24678051, 88593477, 146511208, 472335975, 534494836,
                912985153, 4679307774L, 32164049650L, 32164049651L, 40028394225L,
                42678290603L, 44708635679L, 49388550606L, 82693916578L,
                94204591914L, 28116440335967L, 4338281769391370L,
                4338281769391371L, 21897142587612075L, 35641594208964132L,
                35875699062250035L, 1517841543307505039L, 3289582984443187032L,
                4498128791164624869L, 4929273885928088826L};
        //начинаем с -1 чтобы проверить S <= 1;
        for (int i = 0; i < armstrongNum.length; i++) {
            if (S <= 1) {
                break;
            }
            if (S > armstrongNum[i] && S <= armstrongNum[i + 1]) {
                result = new long[i + 1];
                for (int j = 0; j < i + 1; j++) {
                    result[j] = armstrongNum[j];
                }
                break;
            }
        }
        /*for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }*/

        //способ 2 - свой алгоритм
        /*
        //int[] cimbol = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        //int[] exponent = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

        //создаем буфер значений для быстрого вычисления сумм
        //!!!! изначально можно уменьшить размер создаваемого буфера если припрет канешно
        long[][] bufer = new long[10][20];
        for (int i = 0; i < 10;i++) {
            for (int j = 0; j < 20; j++ ) {
                bufer[i][j] = pow(i, j);
            }
        }

        long[] tempBuffer = new long[50];
        char[] chBuf;
        int countTempBuffer = 0;
        long countN = 1;

        while (countN < S) {
            String text = Long.toString(countN);
            chBuf = text.toCharArray();
            long sum = 0;
            int count = chBuf.length;
            int M = chBuf.length;

            while (count > 0) {
                //char c = s.charAt(0);
                int digit = chBuf[count - 1] - '0';
                sum += bufer[digit][M];//
                if (sum > countN) {
                    break;
                }
                count--;
            }
            if (sum == countN) {
                tempBuffer[countTempBuffer++] = sum;
            }
            countN++;
        }
        long[] result = new long[countTempBuffer];
        System.out.println();
        for (int i = 0; i < countTempBuffer; i++) {
            System.out.println(tempBuffer[i]);
            result[i] = tempBuffer[i];
        }
        */
        return result;
    }

    /*
    public static long pow(int base, int exponent) {
        long result = 1;
        while (exponent > 0) {
            result *= base;
            exponent--;
        }
        return result;
    }
    */

    public static void main(String[] args) {
        //System.out.println(pow(1,1) + " " + pow(2, 2) + " " + pow(10, 4));
        long startTime = System.currentTimeMillis();
        long before = mem();
        getNumbers(1_000_000_000_000L);
        long after = mem();

        System.out.println("\nПамяти задействовано: " + (after - before)/1024/1024 + " Mb");
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("Программа выполнялась " + timeSpent/1000 + " секунд");
    }

    public static long mem() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

}
