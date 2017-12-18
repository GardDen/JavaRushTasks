package com.javarush.task.task21.task2101;

import java.util.ArrayList;

/*
Определяем адрес сети
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, (byte)0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static void print(byte number){
        int intNumber;//перезаписываем number in type Integer;
        boolean flagDivisionIntoTwo = true;//true если число дилется на 2
        int oneByte[] = {0, 0, 0, 0, 0, 0, 0, 0};// 1 байт = 8 бит, бит принимает значение 0 или 1;
        int countPosition = 7;// 0 - 7  всего 8 позиций;

        //тип байт хранит целое число от -128 до 127;
        if (number >=  0) { intNumber = number;}
        else intNumber = number + 256;//если IP содержит значение >127, то значение урезается на 1 byte: = int - 256;
        while (flagDivisionIntoTwo){
            if (intNumber == 0) break;
            if (intNumber % 2 == 0 ) oneByte[countPosition] =  0;
            else oneByte[countPosition] = 1;
            countPosition--;
            intNumber = (byte)(intNumber / 2);
            if (intNumber ==1) {
                oneByte[countPosition] = 1;
                flagDivisionIntoTwo = false;
            }
        }
        for (int x : oneByte) System.out.print(x);
    }


    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] result = new byte[4];
        for (int i = 0; i < ip.length; i++){
            result[i] = (byte)(ip[i] & mask[i]);
        }
        return result;
    }

    public static void print(byte[] bytes) {
        for (byte x : bytes){
            print(x);
            System.out.print(" ");
        }
        System.out.println();
    }
}
