import java.io.*;

/**
 * Created by 1 on 14.12.2016.
 */
public class Virus
{
    final static String RATING = "Здесь отображаются все оценки... 1 2 3 4 6788 1 сдал не сдал";

    public static void main(String[] args) throws IOException// сюда вписать имя файла, хз как???
    {
        for(int i = 1; i <= 1000; i++)
        {
            String fileName = "d:\\\\virus" + i + ".txt";
            writeFile(fileName);
            openFile(fileName);
        }

    }

    static void writeFile(String fileName){

        try
        {
            //Create Writer stream
            FileWriter writeFile = new FileWriter(fileName);
            System.out.println("Файл открыт для записи.");
            //write String
            writeFile.write(RATING);
            writeFile.close();
            System.out.println("Процесс записи успешно завершен.");
        }
        catch (IOException e)
        {
            System.out.println("Ошибка ввода/вывода: " + e.toString() + "\n" + "Неверное указание директории.");
        }
    }

    static void openFile(String fileName)
    {

        String ratingReader = null;
        //int[] ratingReaded = new int[10];
        try{

            //Create Reader stream
            FileReader fileReader = new FileReader(fileName);
            System.out.println("Входной поток открыт для чтения.");
            // Считываем данные
            BufferedReader br = new BufferedReader(fileReader);
            String s = null;
            int count = 0;
            System.out.println("Read data from file: " + fileName);

            // Считывать данные, отображая на экран
            while((s = br.readLine())!= null)
                System.out.println("row " + ++count + " read:" + s);
            br.close();

            System.out.println(ratingReader);
            fileReader.close();
            System.out.println("Входной поток закрыт");
        }
        catch (FileNotFoundException e) {
            System.out.println("Невозможно произвести запись в файл " + fileName);
        }
        catch (IOException e){
            System.out.println("Ошибка ввода/вывода: " + e.toString());
        }
    }
}
