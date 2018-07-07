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
        for (Word word : detectAllWords(crossword, "home", "same", "derl", "emas")) {
            System.out.println(word);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> listWord = new ArrayList<>();


        //listWord.addAll(checkHorizontal(crossword, words));
        //listWord.addAll(checkRevers(crossword, words));




        return listWord;
    }

    public static List<Word> checkRevers(int[][] crossword, String... words) {
        //reversed massiv
        String reversedWords[] = new String[words.length];
        int i = 0;
        for (String word : words) {
            reversedWords[i] = new StringBuilder(word).reverse().toString();
            i++;
        }

        List<Word> listWord = checkHorizontal(crossword, reversedWords);

        //так как слово перевернуто то для правильного вывода необходимо поменять начало и конец позиции координаты
        for (Word word : listWord) {
            int startX = word.endX;
            int startY = word.endY;
            word.setEndPoint(word.startX, word.startY);
            word.setStartPoint(startX, startY);
        }

        return listWord;
    }

    public static List<Word> checkHorizontal(int[][] crossword, String... words) {
        List<Word> listWord = new ArrayList<>();
        //перебор слов из списка words
        for (String word : words) {
            char wordToChar[] = word.toCharArray();
            Word newWord = new Word(word);
            int startX = 0;
            int startY = 0;
            int endX = 0;
            int endY = 0;
            //по строкам
            for (int y = 0; y < crossword.length; y++) {
                //перебор по строке
                for (int x = 0; x < crossword[y].length; x++) {
                    System.out.println((char) crossword[y][x]);
                    //совпаление первой буквы
                    if (wordToChar[0] == crossword[y][x]) {
                        //проверка совпадения остальных букв
                        for (int i = 0; i < wordToChar.length && (i + x) < crossword[y].length; i++) {
                            if (wordToChar[i] == crossword[y][i + x]) {
                                System.out.println(String.format("%c - совпадение буквы №%d", (char) crossword[y][i + x], i));
                                if (i == wordToChar.length - 1) {
                                    startX = x;
                                    startY = y;
                                    endX = x + i;
                                    endY = y;
                                    newWord.setStartPoint(startX,startY);
                                    newWord.setEndPoint(endX, endY );
                                    listWord.add(newWord);
                                    break;
                                }
                            } else break;
                        }
                    }
                }
                System.out.println();
            }
        }
        return listWord;
    }

    public static List<Word> checkVertical(int[][] crossword, String... words) {
        List<Word> listWord = new ArrayList<>();
        //перебор слов из списка words
        for (String word : words) {
            char wordToChar[] = word.toCharArray();
            Word newWord = new Word(word);
            int startX = 0;
            int startY = 0;
            int endX = 0;
            int endY = 0;
            //по строкам
            int y = 0;
            for (int x = 0; x < crossword[y].length; y++) {
                //перебор по строке
                for (y = 0; y < crossword.length; x++) {
                    System.out.println((char) crossword[y][x]);
                    //совпаление первой буквы
                    if (wordToChar[0] == crossword[y][x]) {
                        //проверка совпадения остальных букв
                        for (int i = 0; i < wordToChar.length && (i + x) < crossword[y].length; i++) {
                            if (wordToChar[i] == crossword[y][i + x]) {
                                System.out.println(String.format("%c - совпадение буквы №%d", (char) crossword[y][i + x], i));
                                if (i == wordToChar.length - 1) {
                                    startX = x;
                                    startY = y;
                                    endX = x + i;
                                    endY = y;
                                    newWord.setStartPoint(startX,startY);
                                    newWord.setEndPoint(endX, endY );
                                    listWord.add(newWord);
                                    break;
                                }
                            } else break;
                        }
                    }
                }
                System.out.println();
            }
        }
        return listWord;
    }

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
