package com.javarush.task.task08.task0818;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static  HashMap<String, Integer> createMap() {
        //напишите тут ваш код
        HashMap<String, Integer> map = new HashMap();
        for (int i =495; i < 505; i++){
                map.put(Integer.toString(i - 495), i);
                //System.out.println(map);
        }
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> item: map.entrySet()){
            if(item.getValue() < 500) list.add(item.getKey());
        }
        for (int i = 0; i < list.size(); i++ ){
            map.remove(list.get(i));
        }
        //System.out.println(" dfgdfgf"  + map);
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = createMap();
        removeItemFromMap(map);
    }
}