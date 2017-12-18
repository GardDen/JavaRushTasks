package com.javarush.task.task19.task1909;

/* 
Замена знаков
Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Считать содержимое первого файла и заменить все точки «.» на знак «!«.
Результат вывести во второй файл.
Закрыть потоки.

Требования:
1. Программа должна считывать имена файлов с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое первого файла (используй BufferedReader c конструктором FileReader).
4. Поток чтения из файла (BufferedReader) должен быть закрыт.
5. Программа должна записывать во второй файл содержимое первого файла, где заменены все точки "." на знак "!" (Для записи в файл используй BufferedWriter с конструктором FileWriter).
6. Поток записи в файл (BufferedWriter) должен быть закрыт.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine(), fileName2 = reader.readLine();
        reader.close();

       /* String fileName1 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\1.txt";
        String fileName2 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\2.txt";*/


        FileReader fileReader = new FileReader(fileName1);//поток чтения из файлв
        BufferedReader bufferedReader = new BufferedReader(fileReader);//буфер, хз как он работает

        FileWriter fileWriter = new FileWriter(fileName2);// поток записи в файл
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);// буфер для записи

        String temp = "";//переменная в которую записываем весь текст из первого файла используем её вместо буфера
        while (fileReader.ready()) {//метод реади проверяет все ли считали из файла если есть что читать то возращает истину
            temp += ((char) fileReader.read());//чар в с кобках это приведение типа инт к чару, потому что метод реад возращает считанные байты в типе инт
        }

        temp = temp.replace(".", "!");//здесь и так сам поймешь

        bufferedWriter.write(temp);//собственно

        bufferedReader.close();//непонятная фигня которая закрывает потоки и освобождает ресурсы компа
        bufferedWriter.close();
    }
}
