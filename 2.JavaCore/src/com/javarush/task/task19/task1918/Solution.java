package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        String text = "";
        if (args.length > 0) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();
            reader = new BufferedReader(new FileReader(fileName));
            while (reader.ready()) {
                text += reader.readLine();
            }
            String regex = "(<" + args[0] + ")|(</" + args[0] + "[^>]*>)";
            List<String> list = new ArrayList<>();
            Deque<Integer> queue = new LinkedList<>();
            Matcher matcher = Pattern.compile(regex).matcher(text);
            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    queue.add(matcher.start());
                    System.out.println(queue.getLast());
                } else {
                    list.add(text.substring(queue.removeLast(), matcher.end()));
                    System.out.println(list.get(list.size() - 1));
                }
            }
            for (int i = 0; i < list.size(); i++) {
                //System.out.println(list.get(i));
            }
        }
    }

}
