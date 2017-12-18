package com.javarush.task.task29.task2909.human;

import java.util.Comparator;
import java.util.Date;
public class Student extends UniversityPerson {
    private double averageGrade;
    private Date beginningOfSession;
    private Date endOfSession;
    private int course;
    public static Comparator<Student> studentComparator = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return Double.compare(o2.getAverageGrade(), o1.getAverageGrade());
        }
    };

    public Student(String name, int age, double averageGrade) {
        super(name, age);
        this.name = name;
        this.age = age;
        this.averageGrade = averageGrade;
    }


    @Override
    public String getPosition() {
        return "Студент";
    }

    public void live() {
        learn();
    }

    public void learn() {
    }

    public int getCourse() {
        return course;
    }

    public void incAverageGrade(double delta) {
        setAverageGrade(getAverageGrade() + delta);
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setCourse(int course) {
        this.course = course;
    }


    public void setBeginningOfSession(Date date) {
        beginningOfSession = date;
    }

    public void setEndOfSession(Date date)  {
        endOfSession = date;
    }

    public double getAverageGrade() {
        return averageGrade;
    }
}
