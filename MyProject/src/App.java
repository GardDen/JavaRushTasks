import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class App {

    private final static int NUMB_OF_CALCS = 4;

    private static double         res       = 0;
    private static String         line      = null;
    private static String         calcTypet = null;
    private static BufferedReader reader    = null;


    public static void main(String[] args) throws IOException {

        List<String>     list     = new ArrayList<>();
        List<File>       fileList = new ArrayList<>();
        BufferedReader[] readers  = new BufferedReader[NUMB_OF_CALCS];

        fileList.add(new File("SmpInput.txt"));
        fileList.add(new File("SmpMemInput.txt"));
        fileList.add(new File("EngInput.txt"));
        fileList.add(new File("EngMemInput.txt"));

        for (int i = 0; i < fileList.size(); i++) {
            readers[i] = new BufferedReader(new FileReader(fileList.get(i)));
        }

        for (int i = 0; i < NUMB_OF_CALCS; i++) {
            list.clear();
            calcTypet = readers[i].readLine();

            while ((line = readers[i].readLine()) != null) {
                list.add(line);
            }

            /*switch (calcTypet) {
                case "1":
                    SimpleCalc smp = new SimpleCalc();
                    res = smp.calulate(list);
                    System.out.println("1 calc result: " + res);
                    break;
                case "2":
                    SimpleMemCalc smpm = new SimpleMemCalc();
                    res = smpm.calulate(list);
                    System.out.println("2 calc result: " + res);
                    break;
                case "3":
                    EngineeringCalc eng = new EngineeringCalc();
                    res = eng.calulate(list);
                    System.out.println("3 calc result: " + res);
                    break;
                case "4":
                    EngineeringMemCalc engm = new EngineeringMemCalc();
                    res = engm.calulate(list);
                    System.out.println("4 calc result: " + res);
                    break;
            }*/
        }
        for (int i = 0; i < NUMB_OF_CALCS; i++) {
            readers[i].close();
        }
    }
}
