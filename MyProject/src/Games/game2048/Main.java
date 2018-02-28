package Games.game2048;

import javax.swing.*;

/**
 * Created by 1 on 06.11.2017.
 */
public class Main {
    /**
     * This is main method.
     * @p aram args in the task args empty defoult.
     */
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);

        JFrame game = new JFrame();
        game.setTitle("2048");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(450, 500);
        game.setResizable(false);

        game.add(controller.getView());


        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}
