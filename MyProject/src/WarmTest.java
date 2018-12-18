public class WarmTest {
    public static long measure() {
        long st, en;
        st = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            ;
        }
        en = System.nanoTime();
        return en - st;
    }
    public static void main(String[] args) {
        System.out.println("cold start time " + String.format("%,12d",measure()) + " ns");
        System.out.println("warmed JRE time " + String.format("%,12d",measure()) + " ns");
    }
}
