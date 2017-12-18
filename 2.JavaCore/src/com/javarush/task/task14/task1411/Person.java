package com.javarush.task.task14.task1411;

public  interface Person {

    void  coding();
    void live();
    void doNothing();
    void  enjoy();
    //void doNothing();

    class User implements Person {
        @Override
        public void coding() {

        }

        @Override
        public void live() {
            System.out.println("Usually I just live.");
        }

        @Override
        public void doNothing() {

        }

        @Override
        public void enjoy() {

        }
    }

    class Loser implements Person {
        @Override
        public void coding() {

        }

        @Override
        public void live() {

        }

        @Override
        public void  doNothing() {
            System.out.println("Usually I do nothing.");
        }

        @Override
        public void enjoy() {

        }
    }

    class Coder implements Person {
        public void  coding() {
            System.out.println("Usually I create code.");
        }

        @Override
        public void live() {

        }

        @Override
        public void doNothing() {

        }

        @Override
        public void enjoy() {

        }
    }

    class Proger implements Person {
        @Override
        public void coding() {

        }

        @Override
        public void live() {

        }

        @Override
        public void doNothing() {

        }

        public void  enjoy() {
            System.out.println("Wonderful life!");
        }
    }

}
