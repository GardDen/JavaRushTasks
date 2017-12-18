package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.List;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {
    public List<String> list = new ArrayList<>();

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        getCause(e);
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.uncaughtException(Thread.currentThread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }

    public void getCause(Throwable e) {
        list.add(e.toString());
        if (e.getCause() != null) {
            getCause(e.getCause());
        }
    }

}
