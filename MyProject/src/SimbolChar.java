import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Thread.sleep;

/**
 * Created by 1 on 19.02.2017.
 */
public class SimbolChar {
    public static void main(String[] args) throws InterruptedException, IOException {
        char simbol;
        /*System.out.println("ASCII");
        for (byte i = -128; i < 127; i++){
            if (i % 50 ==0) System.out.println(i);
            simbol = (char)i;
            Thread.sleep(10);
            System.out.printf("[%d - %c] ", i, simbol);
        }*/

        /*System.out.println("Unicode UTF - 16");
        for (int i = 0; i < 65536; i++){
            if (i % 50 ==0) System.out.println(i);
            simbol = (char)i;
            Thread.sleep(10);
            System.out.printField(simbol + " ");
        }*/
        System.out.println();
        System.out.print("Введите символ:");
        String simbolString = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        simbol = simbolString.charAt(0);
        System.out.printf("В кодировке ASCII %s = %d", simbolString,(byte)simbol);
    }
}
