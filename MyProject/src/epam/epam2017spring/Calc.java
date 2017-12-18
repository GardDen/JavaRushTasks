package epam.epam2017spring;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by 1 on 14.02.2017.
 * До конца не работает, так я пока не знаю как обратиться к файлу, не зная его полный адрес, ведь когда пакет придет к Вам
 * полный адрес изменится.. печалька =(
 */
public class Calc {
    static String fileName;

    public static void main(String[] args) throws IOException {
        double result;
        System.out.println("Укажите имя входного файла: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // = reader.readLine();
        Calc.fileName = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\my_project\\epam\\epam2017spring\\Test1.txt";
        ArrayList<String> list = new ArrayList<>();
        readFile(fileName, list);

        System.out.println(new File(".").getAbsolutePath());
        SimpleCalc calc = determineCalc(list);

        //System.out.println(System.getProperty("user.dir"));
    }


    /*
    interface SimpleOperation{
        double addition(double a, double b);
        double subtraction(double a, double b);
        double multiplication(double a, double b);
        double division(double a, double b);
    }
    */
    interface EngineerOperation{
        double cos(double a);
        double exp(double a);
        double sqrt(double a);
    }

    interface Memory{
        //use memory.txt
        void mc();//memory clean MC
        void ms();//memory set MS
        double mr(); //memory read MR
        void mplus();//M+
        void mminus();//M-
    }


    static void readFile(String fileName, ArrayList<String> list)
    {
        String string = null;
        int count = 0;
        try{
            //Create  stream reading
            FileReader fileReader = new FileReader(fileName);
            System.out.println("Stream reading opened.");
            //Create buffer
            BufferedReader br = new BufferedReader(fileReader);
            //Read data in bufer (построчно) and printField in the console
            System.out.println("Read data from file: " + fileName + ".");
            while((string = br.readLine())!= null){
                System.out.println("row " + ++count + " read:" + string);
                list.add(string);
            }

            br.close();
            fileReader.close();
            System.out.println("Buffer and stream reading closed.");
        }
        catch (IOException e){
            System.out.println("Ошибка ввода/вывода: " + e.toString());
        }
    }

    //determine type use the calc
    public static SimpleCalc determineCalc(ArrayList<String> list){
        SimpleCalc calc = null;
        int typeCalc = Integer.parseInt(list.get(0));
        list.remove(0);

        if (typeCalc == 1) {
            calc = new SimpleCalc(list);
        } else if (typeCalc == 2){
            calc = new SimpleCalcWithMemory(list);
        } else if (typeCalc == 3){
            calc = new EngineerCalc(list);
        } else if (typeCalc == 4){
            calc = new EngineerCalcWithMemory(list);
        } else System.out.println("Wrong type the calculator.");
        return calc;
    }

    //analyse strings input file
    public static void analyze(SimpleCalc calc){//Анализирует список строк
        ArrayList<String> list = calc.list;
        String stringArgument1 = "", stringArgument2 = "", typeOperation = "";
        for (int i = 0; i < list.size(); i++){
            char[] simbol = list.get(i).toCharArray();
            //склеивание многозначных чисел или первого отрицательного числа происходит в if, в else  операции
            if (Character.isDigit(simbol[0]) || (list.get(i).equals("." )&& i!=0) || ((i==0)&& list.get(i)== "-")) {
                if (typeOperation.equals("")){
                    stringArgument1 += list.get(i);
                    calc.a = Double.parseDouble(stringArgument1);
                }
                else {
                    stringArgument2 += list.get(i);
                    calc.b = Double.parseDouble(stringArgument2);
                }
            }
            else {
                if(typeOperation.equals("")) {
                    typeOperation = list.get(i);//метод определяет по символу вид операции
                }
                else {
                    calc.a = calculate(typeOperation, calc);
                    typeOperation = list.get(i);
                    stringArgument2 = "";
                }
            }
        }
        System.out.println(calculate(typeOperation, calc));
    }

    //determine arifmetic operation and calculate
    public static double calculate(String typeOperation, SimpleCalc calc){
        double result = 0;
        if (typeOperation.equals("+")) result = calc.addition();
        else if (typeOperation.equals("-")) result = calc.subtraction();
        else if (typeOperation.equals("*")) result = calc.multiplication();
        else if (typeOperation.equals("/")) result = calc.division();
        else if (typeOperation.equals("cos")) result = ((EngineerCalc)calc).cos();
        else if (typeOperation.equals("exp")) result = ((EngineerCalc)calc).exp();
        else if (typeOperation.equals("sqrt")) result = ((EngineerCalc)calc).sqrt();

        else System.out.println("Wrong the arifmetic simbol.");
        return result;
    }

    public static class SimpleCalc{
        /**1. Калькулятор простой. Выполняет четыре арифметические операции: сложение, вычитание, умножение, деление.*/
        public double a;
        public double b;
        /*
        public double addition = a + b;
        public double subtraction = a - b;
        public double multiplication = a * b;
        public double division = a / b;
        */
        public ArrayList<String> list;


        SimpleCalc(ArrayList<String> list){
            this.list = list;
            analyze(this);
        }


         public  double addition() {
            return a + b;
         }

         public double subtraction() {
             return a - b;
         }

         public double multiplication() {
             return a * b;
         }

         public double division() {
             if (b==0){
                 System.out.println("Error! деление на ноль, калькулятор не работает с бесконечными числами");
             }
             return a / b;
         }
    }

    public static class SimpleCalcWithMemory extends SimpleCalc implements Memory{
         /**2. Калькулятор простой с памятью. Выполняет четыре арифметические операции и имеет одну ячейку памяти.
          С данной ячейкой памяти калькулятор выполняет четыре операции: очистить ячейку памяти, записать в ячейку памяти
          текущее значение, присвоить текущему значению содержимое ячейки памяти, увеличить или уменьшить значение в
          ячейке памяти на текущее значение.*/
         public SimpleCalcWithMemory(ArrayList<String> list) {
             super(list);
         }

         public void mc() {

         }

         public void ms() {

         }

         public double mr() {
             ArrayList<String> list = new ArrayList<>();
             String fileName = Calc.fileName;
             //fileName = Calc.Memory;
             //readFile();
             return 0;
         }

         public void mplus() {

         }

         public void mminus() {

         }
     }

    public static class EngineerCalc extends SimpleCalc{//} implements EngineerOperation{
        /**3. Калькулятор инженерный. Выполняет четыре арифметические операции и вычисляет значения трех функций:
         косинус, экспонента, корень квадратный.*/
        EngineerCalc(ArrayList<String> list) {
             super(list);
         }

        public double cos() { return Math.cos(this.a); }

        public double exp() {
             return Math.exp(this.a);
         }

        public double sqrt() {
            if (this.a < 0) System.out.println("Error! Попытка извлечь квадратный корень из отрицательного числа.");
            return Math.sqrt(this.a);
        }
    }

    static class EngineerCalcWithMemory extends EngineerCalc implements Memory{
         /**4. Калькулятор инженерный с памятью. Это комбинация пунктов 2 и 3.*/
         EngineerCalcWithMemory(ArrayList<String> list) {
             super(list);
         }

         public void mc() {

         }

         public void ms() {

         }


         public double mr() {
             return 0;
         }


         public void mplus() {

         }

         public void mminus() {

         }
     }
}


