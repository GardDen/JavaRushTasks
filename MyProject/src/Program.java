public class Program{
     
    public static void main (String args[]){
     
        Calculator calc = new Calculator();
        calc.add(2,3);
    }
}
 
class Calculator{
     
    public void add(int x, int y){
         
        int z = x+y;
        System.out.printf("����� %d � %d ����� %d", x, y, z);
    }
}