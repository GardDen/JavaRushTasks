package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive {
    public class Size {
        public int height;
        public int weight;
    }

    private List<Human> children = new ArrayList<>();
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;
    protected Size size;
    private BloodGroup bloodGroup;

    /*public void setBloodGroup(int code) {
        switch (code) {
            case 1:
                bloodGroup = BloodGroup.first();
                break;
            case 2:
                bloodGroup = BloodGroup.second();
                break;
            case 3:
                bloodGroup = BloodGroup.third();
                break;
            case 4:
                bloodGroup = BloodGroup.fourth();
                break;
            default:
                System.out.println("Неверный выбор группы крови!");
        }
    }*/

    /*public BloodGroup getBloodGroup() {
        return bloodGroup;
    }*/

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = nextId;
        nextId++;
    }

    public String getPosition() {
        return "Человек";
    }

    public void live(){
        
    };

    public void addChild(Human human) {
        children.add(human);
    }

    public void removeChild(Human human) {
        children.remove(human);
    }

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printData() {
        System.out.println(getPosition() + ": " + name);
    }



    public int getId() {
        return id;
    }

    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }
}