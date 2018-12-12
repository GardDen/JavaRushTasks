package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        stream = new FileOutputStream(fileName, true);
        in.defaultReadObject();
        in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) {
        Solution solution = null;
        try {
            //1) создать экземпляр класса Solution
            solution = new Solution("D:\\Google\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2022\\1.txt");
            //2) записать в него данные - writeObject
            solution.writeObject("Данные");
            //3) сериализовать класс Solution - writeObject(ObjectOutputStream out)
            solution.writeObject(new ObjectOutputStream(solution.stream));
            //4) десериализовать, получаем новый объект
            Solution newSolution =new Solution("");//(Solution) solution.readObject(new ObjectInputStream(new FileInputStream(solution.fileName)));

            //5) записать в новый объект данные - writeObject
            newSolution.writeObject("Данные2");
            //6) проверить, что в файле есть данные из п.2 и п.5
            FileInputStream fileInputStream = new FileInputStream(solution.fileName);
            String data = "";
            while (fileInputStream.available() > 0) {
                data +=(char)fileInputStream.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Неверный путь к файлу, или ошибка чтения");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ошибка записи данных");
        }


    }
}
