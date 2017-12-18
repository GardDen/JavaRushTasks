package calc3;

/**
 * Created by 1 on 16.02.2017.
 */

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        ExpressionUtils main = new ExpressionUtils();
        main.read();
        main.write();
    }
}
