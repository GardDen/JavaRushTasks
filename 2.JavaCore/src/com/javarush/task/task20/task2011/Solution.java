package com.javarush.task.task20.task2011;

import java.io.*;

/*
Externalizable для апартаментов
*/
public class Solution {

    public static class Apartment implements Externalizable {

        private String address;
        private int year;

        /**
         * Mandatory public no-arg constructor.
         */
        public Apartment() { super(); }

        public Apartment(String adr, int y) {
            address = adr;
            year = y;
        }

        /**
         * Prints out the fields. used for testing!
         */
        public String toString() {
            return("Address: " + address + "\n" + "Year: " + year);
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(address);
            out.writeInt(year);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            address = (String) in.readObject();
            year = in.readInt();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Apartment apartment = new Apartment("Gomel", 2017);
        File file = new File("D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2011\\test.txt");
        ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(file));
        apartment.writeExternal(objectOutput);
        objectOutput.close();

        ObjectInput objectInput = new ObjectInputStream(new FileInputStream(file));
        Apartment newApartment = new Apartment();
        newApartment.readExternal(objectInput);
        objectInput.close();

        System.out.println(apartment.toString());
        System.out.println(newApartment.toString());
    }
}
