package com.javarush.task.task14.task1421;

/**
 * Created by 1 on 28.02.2017.
 */
class Singleton {
    private static Singleton instance = null;

    private Singleton(){}

    public static Singleton getInstance(){
        if (instance ==null){
            instance = new Singleton();
        }
        return instance;
    }
}
