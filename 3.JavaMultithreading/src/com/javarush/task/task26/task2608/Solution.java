package com.javarush.task.task26.task2608;

/* 
Мудрый человек думает раз, прежде чем два раза сказать
*/
public class Solution {
    int var1;
    int var2;
    int var3;
    int var4;

    public Solution(int var1, int var2, int var3, int var4) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
    }

    public int getSumOfVar1AndVar2() {
        Integer result1 = 1;
        synchronized (result1) {
            result1 = var1 + var2;
        }
        return result1;
    }

    public int getSumOfVar3AndVar4() {
        Integer result = 0;
        synchronized (result) {
            result = var3 + var4;
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
