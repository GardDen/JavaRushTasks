package com.javarush.task.task20.task2014;

import com.sun.corba.se.pept.encoding.InputObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) {
        System.out.println(new Solution(4));

        Solution savedObject = new Solution(20);
        File file = null;
        try {
            file = File.createTempFile("Test", null);
            ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(file));
            objectOutput.writeObject(savedObject);
            objectOutput.close();

            ObjectInput objectInput = new ObjectInputStream(new FileInputStream(file));
            Solution loadObject = (Solution) objectInput.readObject();
            objectInput.close();

            System.out.println(loadObject.string.equals(savedObject.string));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
