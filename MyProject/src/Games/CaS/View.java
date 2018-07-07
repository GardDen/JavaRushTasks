package Games.CaS;


import javax.swing.*;
import java.awt.*;

/**
 * Created by 1 on 26.09.2017.
 */
public class View extends JFrame {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
        this.setVisible(true);
        setTitle("Crosses and Zeros");//Главное окно
        setDefaultCloseOperation(EXIT_ON_CLOSE);//закрытие окна и выход при нажатии крестика в окне
        setSize(250,250);



        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4, 1, 5, 5));

        //Заменить на текст енума Команды
        JButton newGame = new JButton("NEW GAME");
        newGame.addActionListener(e -> {
            controller.newGame();
        });
        JButton leaderBoards = new JButton("LEADERBOARDS");
        leaderBoards.addActionListener(e -> {
            controller.leaderBoards();
        });
        JButton profiles = new JButton("PROFILES");
        profiles.addActionListener(e -> {
            controller.profiles();
        });
        JButton options = new JButton("OPTIONS");
        options.addActionListener(e -> {
            controller.options();
        });

        container.add(newGame);
        container.add(leaderBoards);
        container.add(profiles);
        container.add(options);
    }

}
