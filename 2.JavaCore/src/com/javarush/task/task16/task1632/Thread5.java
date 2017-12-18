package com.javarush.task.task16.task1632;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 07.04.2017.
 */
public class Thread5 extends Thread {
    public void run() {
        String number;
        Integer sum = 0;
        List<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (!(number = reader.readLine()).equals("N")) {
                list.add(number);
            }
            for (String text : list) {
                sum += Integer.parseInt(text);
            }
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
