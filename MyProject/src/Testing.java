import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Testing {
    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(1, 2);
        System.out.println(map.toString());
        new ArrayList<>();

    }






        /*matrix = createMatrix(3, 3);
        printDiagonalUpper(matrix);
        matrix = createMatrix(5, 5);
        printDiagonalUpper(matrix);
        matrix = createMatrix(3, 8);
        printDiagonalUpper(matrix);
        matrix = createMatrix(7, 4);
        printDiagonalUpper(matrix);*/
    public static int[][] matrix;


    /**
     * Создает матрицу согласно переданным аргументам (ширина и высота)
     * и заполняет её целыми числами от 1 построчно сверху вниз и слева направо
     * начиная с field[0][0].
     * @param m - число строк
     * @param n - число столбцов
     * @return - возвращает заполненную матрицу.
     */
    public static int[][] createMatrix(int m, int n) {
        int[][] matrix = new int[m][n];
        int count = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = count;
                count++;
            }
        }
        return matrix;
    }

    public static void printDiagonalUpper(int[][] matrix) {
        print(matrix);
        // число диагоналей восходящих
        int numberDiagonal = matrix.length + matrix[0].length - 1;
        //координаты начала диагонали
        int startI;
        int startJ;
        int lenghtCurrentDiagonal;//количество элементов в текущей диагонали

        //цикл перебора диагоналей
        //k - это номер диагонали от 0 до numberDiagonal,
        //в цикле перебираем диагонали и выводим их элеменнты построчно черех пробел
        for (int k = 0; k < numberDiagonal; k++) {
            //определяем длинну текущей диагонали
            if (k < matrix.length) {
                lenghtCurrentDiagonal = k + 1;
            } else {
                lenghtCurrentDiagonal = numberDiagonal - k;
            }

            //определение координат начала диагонали
            if (k >= matrix.length) {
                startI = matrix.length - 1;
            } else {
                startI = k;
            }
            if (k >= matrix.length) {
                startJ = k + 1 - matrix.length;// k + 1 = поправка на нумерацию диагоналей с нуля
            } else {
                startJ = 0;
            }

            //i и j это счетчики координат в матрице field[i][j]
            //присваеиваем счетчикам координаты начала диагонали, ведь вывод диагонали начинается сначала
            //и заканчивается концом диагонали
            int i = startI;
            int j = startJ;

            //цикл перебора элементов диагонали
            System.out.print("diagonal №" + k + ": ");
            while (j < startJ + lenghtCurrentDiagonal) {
                //если счетчики вышли за границы матрицы, значит уже все элементы диагонали пропечатались
                //выходим из цикла диагонали и переходим к следующей диагонали
                if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length) {
                    break;
                }
                System.out.print(conversionElement(matrix[i][j] + "") + " ");
                i--;
                j++;
            }
            System.out.println();
        }
    }

    /**
     * Печатает в консоль матрицу field
     */
    public static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(conversionElement(matrix[i][j] + "") + " ");
            }
            System.out.println();
        }
    }

    //добавляет пробелы спереди чтобы все числа при выводе стояли ровными столбцами
    public static String conversionElement (String element){
        int maxNumberSimbolInNumber = (matrix[matrix.length - 1][matrix[0].length - 1] + "").length();
        while (element.length() < maxNumberSimbolInNumber) {
            element = " " + element;
        }
        return element;
    }
}