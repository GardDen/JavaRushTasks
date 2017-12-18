package bot;

/**
 * Created by 1 on 19.03.2017.
 * Класс II реализует работу искуственного интелекта.
 */
public class II implements Action {
    public String name;

    private static II ii = new II();

    public static II getII() {
        return ii;
    }

    /**Constructor II.*/
    public II() {
        System.out.println("Инициализация искуственного интелекта:");
        for (int i = 0; i < 30; i++){
            System.out.print("|");
            Bot.delay(0.1);
        }
        System.out.println("\n" + "Инициализация завершена.");
    }


    @Override
    public double calculate(String text) {
        return 0;
    }

    @Override
    public String ask() {
        return null;
    }

    @Override
    public String answer(String question) {
        return null;
    }
}
