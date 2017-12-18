package com.javarush.task.task14.task1408;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by 1 on 08.02.2017.
 */
abstract public class Hen implements Country {

    //abstract public String getCounry(Hen hen);

    public String getDescription() {
         return  "Я - курица.";
    }

    abstract public int getCountOfEggsPerMonth();
}
