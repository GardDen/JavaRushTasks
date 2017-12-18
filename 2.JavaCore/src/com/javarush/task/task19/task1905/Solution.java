package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {
     /*   String text = "+38(050)123-45-67";
        System.out.println(text);
        char[] s = text.toCharArray();
        text = "callto://+" + s[1] + s[2] + s[4] + s[5] + s[6] + s[8] + s[9] + s[10] + s[12] + s[13] +s[15] + s[16];
        System.out.println(text);
        text =  "callto://" + "+38(050)123-45-67".replaceAll("[^+0-9]", "");
        System.out.println(text);*/
    }

    public static class DataAdapter implements RowItem {
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String result = null;
            for (Map.Entry<String, String> entry: countries.entrySet()) {
                if (entry.getValue().equals(customer.getCountryName())) {
                    result = entry.getKey();
                }
            }
            return result;
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            return contact.getName().split(", ")[1];
        }

        @Override
        public String getContactLastName() {
            return contact.getName().split(", ")[0];
        }

        @Override
        public String getDialString() {
            return  "callto://" + contact.getPhoneNumber().replaceAll("[^+0-9]", "");
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}