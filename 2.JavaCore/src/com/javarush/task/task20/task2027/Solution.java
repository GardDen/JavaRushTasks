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
                {'p', 'o', 'e', 'e', 'j', 'j'},
                {'p', 'o', 'e', 'e', 'j', 'j'},
                {'p', 'o', 'e', 'e', 'j', 'j'},
                {'p', 'o', 'e', 'e', 'j', 'j'},
        };
        for (Word word : detectAllWords(crossword, "home", "same")) {
            System.out.println(word);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();
        wordList.addAll(checkHorizontal(crossword, words));
        wordList.addAll(normalizationWord(checkHorizontal(crossword, reversMasiv(words))));
        wordList.addAll(checkVertical(crossword, words));
        wordList.addAll(normalizationWord(checkVertical(crossword, reversMasiv(words))));
        wordList.addAll(checkDiagonalUp(crossword, words));
        wordList.addAll(normalizationWord(checkDiagonalUp(crossword, reversMasiv(words))));
        wordList.addAll(checkDiagonalDown(crossword, words));
        wordList.addAll(normalizationWord(checkDiagonalDown(crossword, reversMasiv(words))));
        return wordList;
    }

    public static String[] reversMasiv(String... words) {
        String reversedWords[] = new String[words.length];
        int i = 0;
        for (String word : words) {
            reversedWords[i] = new StringBuilder(word).reverse().toString();
            i++;
        }
        return reversedWords;
    }

    /**
     * так как слово перевернуто то для правильного вывода необходимо поменять начало и конец позиции координаты
     * @return
     */
    public static List<Word> normalizationWord(List<Word> wordList) {
        for (Word word : wordList) {
            int startX = word.endX;
            int startY = word.endY;
            word.setEndPoint(word.startX, word.startY);
            word.setStartPoint(startX, startY);
            word.setText(new StringBuilder(word.text).reverse().toString());
        }
        return wordList;
    }

    public static List<Word> checkHorizontal(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();
        //перебор слов из списка words
        for (String word : words) {
            char wordToChar[] = word.toCharArray();
            Word newWord = new Word(word);
            //по строкам
            for (int y = 0; y < crossword.length; y++) {
                //перебор по строке
                for (int x = 0; x < crossword[y].length; x++) {
                    //совпаление первой буквы
                    if (wordToChar[0] == crossword[y][x]) {
                        //проверка совпадения остальных букв
                        for (int i = 0; i < wordToChar.length && (i + x) < crossword[y].length; i++) {
                            if (wordToChar[i] == crossword[y][i + x]) {
                                if (i == wordToChar.length - 1) {
                                    newWord.setStartPoint(x, y);
                                    newWord.setEndPoint(x +i, y );
                                    wordList.add(newWord);
                                    break;
                                }
                            } else break;
                        }
                    }
                }
            }
        }
        return wordList;
    }

    public static List<Word> checkVertical(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();
        //перебор слов из списка words
        for (String word : words) {
            char wordToChar[] = word.toCharArray();
            Word newWord = new Word(word);
            //по строкам
            int y = 0;
            for (int x = 0; x < crossword[0].length; x++) {
                //перебор по строке
                for (y = 0; y < crossword.length; y++) {
                    //совпаление первой буквы
                    if (wordToChar[0] == crossword[y][x]) {
                        //проверка совпадения остальных букв
                        for (int i = 0; i < wordToChar.length && (i + y) < crossword.length; i++) {
                            if (wordToChar[i] == crossword[i + y][x]) {
                                if (i == wordToChar.length - 1) {
                                    newWord.setStartPoint(x, y);
                                    newWord.setEndPoint(x, y + i);
                                    wordList.add(newWord);
                                    break;
                                }
                            } else break;
                        }
                    }
                }
            }
        }
        return wordList;
    }

    public static List<Word> checkDiagonalUp(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();
        for (String word : words) {
            char wordToChar[] = word.toCharArray();
            Word newWord = new Word(word);

            for (int y = 0; y < crossword.length; y++) {
                int countPos = 0;//
                for (int x = 0; x <= y; x++) {
                    try {
                        int temp = y - x;
                        //
                        if (wordToChar[countPos] == crossword[temp][x]) {
                            countPos++;
                        } else {
                            countPos = 0;
                        }
                        if (countPos == wordToChar.length) {
                            newWord.setEndPoint(x, temp);
                            newWord.setStartPoint(newWord.endX - newWord.text.length() + 1, newWord.endY + newWord.text.length() - 1);
                            wordList.add(newWord);
                            break;
                        }
                    } catch (Exception e) {
                        break;
                    }
                }
            }

            for (int y = crossword.length - 2; y >= -1; y--) {
                int countPos = 0;//
                for (int x = 0; x <= y + 1; x++) {

                    try {
                        int temp = y - x;
                        //
                        if (wordToChar[countPos] == crossword[crossword.length - x - 1][crossword.length - temp - 1]) {
                            countPos++;
                        } else {
                            countPos = 0;
                        }
                        if (countPos == wordToChar.length) {
                            newWord.setEndPoint(crossword.length - temp - 1,crossword.length - x - 1);
                            newWord.setStartPoint(newWord.endX - newWord.text.length() + 1, newWord.endY + newWord.text.length() - 1);
                            wordList.add(newWord);
                            break;
                        }
                        //
                    } catch (Exception e) {
                        break;
                    }
                }
            }
        }
        return wordList;
    }

    public static List<Word> checkDiagonalDown(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();
        for (String word : words) {
            char wordToChar[] = word.toCharArray();
            Word newWord = new Word(word);

            for (int i = crossword.length - 1; i >= 0; i--) {
                //String temp = "";
                int countPos = 0;//
                for (int j = 0, x = i; x <= crossword.length - 1; j++, x++) {
                    try {
                        //temp = temp + (char) crossword[x][j];
                        if (wordToChar[countPos] == crossword[x][j]) {
                            countPos++;
                        } else {
                            countPos = 0;
                        }
                        if (countPos == wordToChar.length) {
                            newWord.setEndPoint(j, x);
                            newWord.setStartPoint(newWord.endX - newWord.text.length() + 1, newWord.endY - newWord.text.length() + 1);
                            wordList.add(newWord);
                            break;
                        }
                    } catch (Exception e) {
                        break;
                    }
                }
                //System.out.println(temp);
            }

            for (int i = 1; i <= crossword.length; i++) {
                //String temp = "";
                int countPos = 0;//

                for (int j = 0, y = i; y <= crossword.length; j++, y++) {
                    try {
                        //temp = temp + (char) crossword[j][y];
                        if (wordToChar[countPos] == crossword[j][y]) {
                            countPos++;
                        } else {
                            countPos = 0;
                        }
                        if (countPos == wordToChar.length) {
                            newWord.setEndPoint(y, j);
                            newWord.setStartPoint(newWord.endX - newWord.text.length() + 1, newWord.endY - newWord.text.length() + 1);
                            wordList.add(newWord);
                            break;
                        }
                    } catch (Exception e) {
                        break;
                    }
                }
                //System.out.println(temp);
            }
        }
        return wordList;
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

        public void setText(String text) {
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
