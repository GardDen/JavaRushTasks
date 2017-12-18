package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        //перебор заданных слов
        for (int k = 0; k < words.length; k++) {
            //пробразование слова в массив инт
            char[] text = words[k].toCharArray();
            int textInt[] = new int[text.length];
            for (int z = 0; z < textInt.length; z++) {
                textInt[z] = (int)text[z];
            }
            for (int g : textInt) {
                System.out.print((char)g);
            }
            System.out.println();

            Word word = new Word(words[k]);
            int count = 0;
            int startX = 0;
            int startY = 0;
            //horizont
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    if (crossword[i][j] == textInt[count]) {
                        if (count == 0) {
                            startX = j;
                            startY = i;
                            count++;
                        } else if (count == (words[k].length() - 1)){
                            word.setStartPoint(startX, startY);
                            word.setEndPoint(j, i);
                            list.add(word);
                            count = 0;
                        } else {
                            count++;
                        }
                    } else {
                        count = 0;
                    }
                }
            }
            //vertical
            for (int j = 0; j < crossword[0].length; j++) {
                for (int i = 0; i < crossword.length; i++) {
                    if (crossword[i][j] == textInt[count]) {
                        if (count == 0) {
                            startX = j;
                            startY = i;
                            count++;
                        } else if (count == (words[k].length() - 1)){
                            word.setStartPoint(startX, startY);
                            word.setEndPoint(j, i);
                            list.add(word);
                            count = 0;
                        } else {
                            count++;
                        }
                    } else {
                        count = 0;
                    }
                }
            }

            //diagonal восх

            //int k = 0;
            //diagonal нис
            for (int i = 0;;) {
                for (int j = 0;;){
                    if (crossword[i][j] == textInt[count]) {
                        if (count == 0) {
                            startX = j;
                            startY = i;
                            count++;
                        } else if (count == (words[k].length() - 1)){
                            word.setStartPoint(startX, startY);
                            word.setEndPoint(j, i);
                            list.add(word);
                            count = 0;
                        } else {
                            count++;
                        }
                    } else {
                        count = 0;
                    }

                    if (i == 0) {
                        k++;
                    }
                    if (i == j) {
                        i++;
                    } else if (i > j){
                        i--;
                        j++;
                    } else {
                        //i =
                    }
                }
            }
        }
        for (Word word: list
             ) {
            System.out.println(word.toString());
        }
        return list;
    }

    //i,j,textInt, count, startX,startY, word, k  = add word
    //public static void ckeckAndAddWordInList(int i, int j, int[] textInt, int count, int startX, int StartY, )



    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
