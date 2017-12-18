import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 16.03.2017.
 * Данный класс является заметкой,  в который я записываю различные интересные и полезные куски кода
 */
public class Info {
    public static void main(String[] args) {
        /** Выводит сеписок всех файлов в корневом катологе
         */
        /*String dirPath = "/";
        File file = new File(dirPath);
        File[] files = file.listFiles();
        for (File file1 : files) System.out.println(file1);*/

        System.out.println(readFile("xz"));
    }

    /**
     * Метод считывает файл построчно и возвращаетв список строк
     * @param nameFile имя файла, который читать
     * @return список строк считанных из файла
     */
    public static ArrayList<String> readFile(String nameFile) {
        ArrayList<String> buffer = new ArrayList<>();
        String data;
        try{
            BufferedReader br = new BufferedReader(new FileReader(nameFile));
            while((data = br.readLine())!= null) {
                buffer.add(data);
            }
            br.close();
        }
        catch (IOException exception){
            System.out.println("Ошибка чтения из файла: " + exception.toString());
        }
        return buffer;
    }

    /**
     * Построчное считывание из файла
     */
    String fileName ="d:\\text.txt";
    List<String> list = new ArrayList<>();

    public void readFile(String fileName, List<String> list) {
        String s = null;
        int count = 0;
        try{
            //Create stream and buffer? read dara from is fileName
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            // Считывать данные, отображая на экран
            while((s = br.readLine())!= null) {
                System.out.println("row " + ++count + " read:" + s);
                list.add(s);
            }
            br.close();
            fileReader.close();
        }
        catch (IOException e){
            System.out.println("Ошибка чтене из файла: " + e.toString());
        }
    }
}
