package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new FileReader(new BufferedReader(new InputStreamReader(System.in)).readLine()))) {
            String temp;
            StringBuilder stringBuilder = new StringBuilder();
            while ((temp = bf.readLine()) != null) {
                stringBuilder.append(temp + " ");
            }
            ArrayList<String> list = new ArrayList<>(Arrays.asList(stringBuilder.toString().split(" ")));
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    String revers = new StringBuilder(list.get(j)).reverse().toString();
                    if (list.get(i).equals(revers)) {
                        Pair pair = new Pair();
                        pair.first = list.get(i);
                        pair.second = list.get(j);
                        result.add(pair);
                        list.remove(i);
                        list.remove(j - 1);
                        i = 0;
                        break;
                    }
                }
            }

            for (Pair item : result
                    ) {
                System.out.println(item);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
