package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int count = 0;
        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a[0].length; x++) {
/*

                //print
                for (int m = 0; m < a.length; m++) {
                    for (int n = 0; n < a[0].length; n++) {
                        System.out.print(a[m][n] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
*/

                if (a[y][x] == 1) {
                    count++;
                    int endY = y;
                    int endX = x;
                    do {
                        if (endX + 1 < a[0].length) {
                            endX++;
                        } else {
                            break;
                        }
                    } while (a[y][endX] == 1);

                    do {
                        if (endY + 1 < a.length) {
                            endY++;
                        } else {
                            break;
                        }
                    } while (a[endY][x] == 1);

                    //обнуляем
                    for (int i = y; i <= endY; i++) {
                        for (int j = x; j <= endX; j++) {
                            a[i][j] = 0;
                        }
                    }
                }

            }
        }

        return count;
    }
}
