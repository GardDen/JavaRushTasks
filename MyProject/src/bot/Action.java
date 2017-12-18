package bot;

/**
 * Created by 1 on 19.03.2017.
 */
interface Action {
    double calculate(String text);

    String ask();

    String answer(String question);
}
