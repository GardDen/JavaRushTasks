package Swing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 1 on 27.07.2017.
 */
public class FlowLayoutDemo {
    public static void main(String[] args) {
        // создаем окно и устанавливаем его размер.
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400, 300);
        jf.setVisible(true);

        // создаем  панель.
        JPanel p = new JPanel();
        jf.add(p);

        // к панели добавляем менеджер FlowLayout.
        p.setLayout(new FlowLayout());

        // к панели добавляем кнопки.
        p.add(new JButton("start 2"));
        p.add(new JButton("start 2"));
        p.add(new JButton("start 3"));
        p.add(new JButton("start 4"));
        p.add(new JButton("start 5"));
        p.add(new JButton("start 6"));
        p.add(new JButton("Okay"));
    }
}
