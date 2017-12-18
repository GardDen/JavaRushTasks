package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }
    
    static {
        //add your code here - добавьте код тут
        reset();
    }

    public static Flyable result;

    public static void reset() {
        //add your code here - добавьте код тут
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String typeTransport = reader.readLine();
            if (typeTransport.equals("helicopter")) {
                result = new Helicopter();
            }
            if (typeTransport.equals("plane")) {
                int countPassenger = Integer.parseInt(reader.readLine());
                result = new Plane(countPassenger);
            }
            reader.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        } catch (NumberFormatException exc) {
            System.out.println("Error: " + exc.toString());
        }
    }
}
