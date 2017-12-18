package epam.epam2017autumn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by 1 on 05.09.2017.
 */
public class Solution {
    public static ArrayList<Speed> listSpeeds = new ArrayList<>();

    public static void main(String[] args) {
        execute(args[0]);
    }

    public static void execute(String fileName) {
        try(BufferedReader bufferedReader1 = new BufferedReader(new FileReader(fileName)))
        {
            String s;
            while((s=bufferedReader1.readLine()) != null){
                String[] temp = s.trim().split(" ");
                Speed speed = new Speed(temp[0], temp[1]);
                listSpeeds.add(speed);
            }
        } catch(IOException exс){
            System.out.println(exс.getMessage());
        }
        printListInMs();
        printList(valueInMsComporator);
        printListContains2();
        printList(unitAndValueComporator);
    }

    public static void printListInMs() {
        NumberFormat nf = new DecimalFormat("#.###");
        for (int i = 0; i <  listSpeeds.size(); i++) {
            System.out.format("%s %s = %s ms%n", listSpeeds.get(i).getValueText(), listSpeeds.get(i).getUnit(),
                    nf.format(listSpeeds.get(i).getValueInMs()));
        }
        System.out.println();
    }

    public static void printList(Comparator comparator) {
        Collections.sort(listSpeeds, comparator);
        for (int i = 0; i < listSpeeds.size(); i++) {
            System.out.format("%s %s%n",listSpeeds.get(i).getValueText(), listSpeeds.get(i).getUnit());
        }
        System.out.println();
    }

    public static void printListContains2() {
        if (listSpeeds.contains(new Speed("2", "kmh"))) {
            System.out.println("yes");
        } else System.out.println("no");
        System.out.println();
    }

    public static Comparator<Speed> unitAndValueComporator = new Comparator<Speed>() {
        @Override
        public int compare(Speed o1, Speed o2) {
            int flag = o2.getUnit().length() - o1.getUnit().length();
            if (flag == 0) {
                flag = o1.getUnit().compareTo(o2.getUnit());
            }
            if (flag == 0) {
                flag = Double.compare(o2.getValueInMs(), o1.getValueInMs());
            }
            return flag;
        }
    };

    public static Comparator<Speed> valueInMsComporator = new Comparator<Speed>() {
        @Override
        public int compare(Speed o1, Speed o2) {
            return Double.compare(o1.getValueInMs(), o2.getValueInMs());
        }
    };
}
