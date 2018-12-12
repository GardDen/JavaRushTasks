import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String str, writer;
        int checker = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (checker==0) {
            str = reader.readLine();
            switch (str) {
                case "txt":
                    writer = FileWriterFactory.getWriter(FileTypes.TXT);
                    checker = 1;
                    break;
                case "doc":
                    writer = FileWriterFactory.getWriter(FileTypes.DOC);
                    checker = 1;
                    break;
                case "rtf":
                    writer = FileWriterFactory.getWriter(FileTypes.RTF);
                    checker = 1;
                    break;
                default:
                    System.out.println("Вы ввели неверный формат файла!");
                    break;
            }
        }
    }

    private static class FileWriterFactory {
        static String getWriter(FileTypes file) {
            return file.toString();
        }
    }

    enum  FileTypes {
        TXT,
        DOC,
        RTF
    }
}
