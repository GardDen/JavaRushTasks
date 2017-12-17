package com.javarush.task.task20.task2025;

import java.util.ArrayList;


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
    public static long[] getNumbers(long N) {
        long[] result;
        ArrayList<Long> list = new ArrayList<Long>();
        String nubmerText;
        while (N > 0) {
            nubmerText = Long.toString(N);
            int lenght = nubmerText.length();
            long summa = 0;
            int position = 0;
            int valueOfThePosition;
            while (position < lenght) {
                valueOfThePosition = nubmerText.charAt(position);
                summa += Math.pow(valueOfThePosition, lenght);
                position++;
            }
            if (summa == N) {
                list.add(N);
            }
            N--;
        }
        result = list.toArray(new long[list.size()]);

        return result;
    }

    public static void main(String[] args) {
        for (long number : getNumbers(10)) {
            System.out.println(number);
        }
        System.out.println("???");
        System.out.println("???");

    }
}
