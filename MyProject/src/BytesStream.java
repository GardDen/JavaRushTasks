/**
 * Created by 1 on 12.12.2016.
 */

import java.io.*;

public class BytesStream
{
    public static void main(String[] args)
    {


        byte[] ratingToWrite = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        byte[] retingReaded = new byte[10];
        String ratingStringWrite = "ывыаырпыврпвр 1 0 1 0 ";
        int[] ratingReaded = new int[10];
        String fileName = "";// = "d:\\text.txt";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            System.out.println("Укажите путь к файлу для чтения данных (\"d:\\\\text.txt\")");
            fileName = reader.readLine();

            //Create output stream
            FileOutputStream outFile = new FileOutputStream(fileName);
            System.out.println("Файл открыт для записи.");

            //write array
            outFile.write(ratingToWrite);
            outFile.close();
            System.out.println("Выходной поток закрыт.");

            //Create input stream
            FileInputStream inFile = new FileInputStream(fileName);
            System.out.println("Входной поток открыт для чтения.");

            // Узнать, сколько байт готово к считыванию
            int bytesAvailable = inFile.available();
            System.out.println("Готово к считыванию: " + bytesAvailable + " байт");

            // Считать в массив
            int count = inFile.read(retingReaded, 0, bytesAvailable);
            System.out.println("Считано: " + count + " байт");
            for (int i = 0; i < count; i++)
                System.out.print(retingReaded[i] + ",");
            System.out.println();
            inFile.close();
            System.out.println("Входной поток закрыт");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Невозможно произвести запись в файл" + fileName);
        }
        catch (IOException e)
        {
            System.out.println("Ошибка ввода/вывода: " + e.toString());
        }
    }
}
