package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    /*
    1) при передаче программе неправильного количества аргументов <4 она может крашиться,
    лишние аргументы игнорируются;
    2) параметры хранящиеся в Файле должны быть не меньше указанного размера, могут превышать;
    3) длинна входящих параметров не важна, короткие должы быть увеличены до заданного размера;
    4) id может превышать 99999999, при переполнении номер ид может записываться в имя продукта;
    5) id определяется по первым 8-ми символам последней строки в файле, искать максимальный id в файл не нужно;
    6) файл нужно не дописать а перезаписать;
    7) При запуске тестов Валидатора, есть общий контент у джунита, так как он не чистится то он некорректно проверят программы со статическими изменяемыми элементами.
    Если используете List то в конце используйте метод list.clear();
     */

    static List<String> listProduct = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        if ("-c".equals(args[0])) {
            String fileName;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            fileName = reader.readLine();
            reader.close();
            reader = new BufferedReader(new FileReader(fileName));
            while (reader.ready()) {
                listProduct.add(String.format("%s%n", reader.readLine()));
            }
            reader.close();
            listProduct.add(String.format("%-8d%-30s%-8s%-4s", Integer.parseInt(listProduct.get(listProduct.size() - 1).substring(0, 8).trim()) + 1, args[1], args[2], args[3]));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String data : listProduct) {
                writer.write(data);
            }
            writer.close();
        }
        listProduct.clear();
    }
}

