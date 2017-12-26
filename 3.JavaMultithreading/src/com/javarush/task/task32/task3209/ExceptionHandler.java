package com.javarush.task.task32.task3209;

/**
 * Created by 1 on 19.12.2017.
 */
public class ExceptionHandler {
    /**
     * Это вроде как логгер. Он вроде и не логер. Но в принципе в консоль он будет логировать. ХЗ
     * @param e Exception
     */
    public static void log(Exception e) {
        System.out.println(e.toString());
    }
}
