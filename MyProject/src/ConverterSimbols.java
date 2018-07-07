import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by 1 on 19.02.2017.
 */
public class ConverterSimbols {
    public static void main(String[] args) throws InterruptedException, IOException {
        char simbol;
        /** Print a list of pare simbols in system ASCII.*/
        /*System.out.println("ASCII");
        for (byte i = -128; i < 127; i++){
            if (i % 50 ==0) System.out.println(i);
            simbol = (char)i;
            System.out.printf("[%d - %c] ", i, simbol);
        }*/

        /** Print a list of pare simbols in system Unicode UTF - 16.*/
        System.out.println("Unicode UTF - 16");
        for (int i = 0; i < 65536; i++){
            if (i % 50 ==0) System.out.print("\n" + i);
            simbol = (char)i;
            System.out.print(simbol + " ");
        }
        System.out.println();
        System.out.print("Введите символ в пределе от -127 до 127, и программа выведент значение символа в системе ASCII:");
        String simbolString = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        simbol = simbolString.charAt(0);
        System.out.printf("В кодировке ASCII %s = %d", simbolString,(byte)simbol);
    }
}
