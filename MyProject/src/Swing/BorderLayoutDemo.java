package Swing;

/**
 * Created by 1 on 27.07.2017.
 */
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BorderLayoutDemo {
    public static void main(String[] args) {
        // создаем фрейм и устанавливаем его размер.
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setVisible(true);

        // создаем панель.
        JPanel panel = new JPanel();
        frame.add(panel);

        // к панели добавляем менеджер BorderLayout.
        panel.setLayout(new java.awt.BorderLayout());

        // к панели добавляем кнопку и устанавливаем для нее менеджер в верхнее расположение.
        panel.add(new JButton("Okay"), BorderLayout.PAGE_END);
        panel.add(new JButton("No"), BorderLayout.PAGE_START);
        panel.add(new JButton("Cancel"), BorderLayout.EAST);
        panel.add(new JButton("Start"), BorderLayout.WEST);

    }
}

