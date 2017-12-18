package com.javarush.task.task14.task1414;

/* 
MovieFactory
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        //ввести с консоли несколько ключей (строк), пункт 7
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text;
        Movie movie;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0;;i++){
            text = reader.readLine();
            if (text.equals("soapOpera") || text.equals("thriller") || text.equals("cartoon")) {
                list.add(text);
            } else {
                movie = MovieFactory.getMovie(text);
                break;
            }
        }
        /*
8 Создать переменную movie класса Movie и для каждой введенной строки(ключа):
8.1 получить объект используя MovieFactory.getMovie и присвоить его переменной movie
8.2 вывести на экран movie.getClass().getSimpleName()
        */
        for (String iter : list){
            movie = MovieFactory.getMovie(iter);
            System.out.println(movie.getClass().getSimpleName());
        }

    }

    static class MovieFactory {

        static Movie getMovie(String key) {
            Movie movie = null;

            //создание объекта SoapOpera (мыльная опера) для ключа "soapOpera"
            if ("soapOpera".equals(key)) {
                movie = new SoapOpera();
            }

            //напишите тут ваш код, пункты 5,6
            if ("cartoon".equals(key)){
                movie = new Cartoon();
            }
            if ("thriller".equals(key)){
                movie = new Thriller();
            }


            return movie;
        }
    }

    static abstract class Movie {
    }

    static class Cartoon extends Movie{

    }

    static class Thriller extends Movie{

    }


    static class SoapOpera extends Movie {
    }

    //Напишите тут ваши классы, пункт 3
}
