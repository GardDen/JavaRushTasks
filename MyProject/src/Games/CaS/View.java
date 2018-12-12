package Games.CaS;


import javax.swing.*;
import java.awt.*;

import static Games.CaS.Command.*;

/**
 * Created by 1 on 26.09.2017.
 */
public class View extends JFrame {
    private JButton newGame = new JButton();
    private JButton leaderBoards = new JButton();
    private JButton profiles = new JButton();
    private JButton options = new JButton();



    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);//закрытие окна и выход при нажатии крестика в окне
        setSize(250,250);



        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4, 1, 5, 5));

        //Заменить на текст енума Команды
        newGame.addActionListener(e -> controller.newGame());
        leaderBoards.addActionListener(e -> controller.leaderBoards());
        profiles.addActionListener(e -> controller.profiles());
        options.addActionListener(e -> controller.options());

        container.add(newGame);
        container.add(leaderBoards);
        container.add(profiles);
        container.add(options);

        repaint();
    }

    public void repaint() {
        newGame.setText(NEW_GAME.getName(controller));
        leaderBoards.setText(LEADER_BOARDS.getName(controller));
        profiles.setText(PROFILES.getName(controller));
        options.setText(OPTIONS.getName(controller));
    }

}
