package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {

    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
        public InnerClass() {
        }
    }

    public Solution() {
    }

    public Solution(InnerClass[] innerClasses) {
        this.innerClasses = innerClasses;
    }

    public static Solution[] getTwoSolutions() {
        Solution solution = new Solution();
        Solution.InnerClass innerClass1 = solution.new InnerClass();
        Solution.InnerClass innerClass2 = solution.new InnerClass();
        InnerClass[] innerClasses = {innerClass1, innerClass2};
        Solution solution1 = new Solution(innerClasses);
        Solution solution2 = new Solution(innerClasses);
        Solution[] solutions = {solution1, solution2};
        return solutions;
    }

    public static void main(String[] args) {

    }
}
