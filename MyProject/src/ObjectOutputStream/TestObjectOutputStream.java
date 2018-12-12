package ObjectOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class TestObjectOutputStream {
    private static String fileName = "D:\\Google\\Project\\Java\\JavaRushTasks\\MyProject\\src\\ObjectOutputStream\\data.txt";
    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeInt(12345);
            objectOutputStream.writeObject("Today");
            objectOutputStream.writeObject(new Date());

            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileNotFoundException!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
