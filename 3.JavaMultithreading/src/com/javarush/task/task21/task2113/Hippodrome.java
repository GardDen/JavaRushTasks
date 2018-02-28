package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 27.03.2017.
 */
public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static void main(String[] args) {
        game = new Hippodrome(new ArrayList<Horse>());
        game.getHorses().add(new Horse("Sterva", 3.0, 0));
        game.getHorses().add(new Horse("Gogo", 3.0, 0));
        game.getHorses().add(new Horse("Murka", 3.0, 0));

        game.run();
        game.printWinner();

        //System.out.println(horses.get(1).getSpeed());
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void move(){
        for (int i = 0; i < getHorses().size(); i++) {
            getHorses().get(i).move();
        }
    }

    public void print(){
        for (int i = 0; i < getHorses().size(); i++) {
            getHorses().get(i).print();
        }
        for (int i = 0; i < 10; i++){
            System.out.println();
        }
    }

    public void run(){
        for (int i = 0; i < 100; i++){
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Error Thtead in metod run");
            }
        }
    }

    public Horse getWinner() {
        Horse winner = null;
        double maxDistance = 0;
        for (Horse h:horses) {
            if(h.getDistance()>maxDistance)
                maxDistance = h.getDistance();
        }
        for (Horse h:horses) {
            if(h.getDistance()==maxDistance)
                winner = h;
        }
        return winner;

        /*int numberWinner = 0;
        Horse horseWin = Games.getHorses().get(0);
        for (int i = 1; i < 3; i++) {
            if (horseWin.getDistance() < Games.getHorses().get(i).getDistance()) {
                horseWin = Games.getHorses().get(i);
                numberWinner = i;
            }
        }
        return Games.getHorses().get(numberWinner);*/
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
