package coding;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by 1 on 15.02.2017.
 *
 * данный код считывает из файла данные написаные через ентер и добавляет в список только числа.
 */
public class ImputStream {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        ArrayList<Integer> list = new ArrayList<>();
        fileName = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task13\\task1326\\Test.txt";
        InputStream inputStream = new FileInputStream(fileName);
        int data;
        String text = "";
        char simbol;
        while (inputStream.available() > 0) {
            data = inputStream.read();
            simbol = (char) data;
            if (Character.isDigit(simbol)) {
                text += String.valueOf(simbol);
            } else if (simbol == 13) {
                list.add(Integer.parseInt(text));
                text = "";
            }
        }
        list.add(Integer.parseInt(text));
        inputStream.close();
    }
}
