import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by 1 on 15.10.2016.
 */


public class Benzin
{
    public static void main(String[] args) throws IOException
    {
        double po_trasse = 7.5;
        double po_gorodu = 13;
        double cost = 1.2;
        int way_I_Tania = 10, way_Tania_Terexovka = 42;
        double sum_cost_ac = (way_I_Tania * po_gorodu + way_Tania_Terexovka * po_trasse) / 100 * cost;
        double sum_cost_ab = (way_I_Tania * po_gorodu) / 100 * cost;
        double sum_cost_bc = (way_Tania_Terexovka * po_trasse) / 100 * cost;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(" Выберите маршрут");
        System.out.println("Я - точка а, Таня точка - b, Тереховка точка - с; маршруты - ab , bc, ac;");
        String way = reader.readLine();
        System.out.println("Введите количество человек: ");
        int N = Integer.parseInt(reader.readLine());
        switch (way){
            case "ab":
                System.out.println("Город: " + sum_cost_ab);
                System.out.println("С человека по: " + sum_cost_ab/N);
                break;
            case "bc":
                System.out.println("Трасса: " + sum_cost_bc);
                System.out.println("С человека по: " + sum_cost_bc/N);
                break;
            case "ac":
                System.out.println("В одну сторону: " + sum_cost_ac);
                System.out.println("С человека по: " + sum_cost_ac/N);
                break;
            default:
                System.out.println("Неправильно выбран маршрут!");
        }

        //double sum_tania = sum_cost_bc/2 * 5;
        //System.out.println(sum_tania);
    }
}
