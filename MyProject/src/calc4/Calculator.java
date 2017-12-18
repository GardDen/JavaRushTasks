package calc4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1 on 20.07.2017.
 */
public class Calculator implements ActionListener{
    JPanel panel = new JPanel();
    JTextField displayField = new JTextField();
    JButton button0;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    JButton button8;
    JButton button9;
    JButton buttonPoint;
    JButton buttonEqual;
    JPanel p1;
    static Calculator calculator;

    public JTextField getDisplayField() {
        return displayField;
    }

    Calculator() {
        calculator = this;
        panel = new JPanel();

        BorderLayout b1 = new BorderLayout();
        panel.setLayout(b1);

        //add field in panel
        displayField = new JTextField(30);
        panel.add("North", displayField);

        //inicialize button
        button0 = new JButton("0");
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");
        buttonPoint = new JButton(".");
        buttonEqual = new JButton("=");

        //determine pozition button in the field
        p1 = new JPanel();
        GridLayout gl = new GridLayout(4, 3);
        p1.setLayout(gl);

        p1.add(button0);
        p1.add(button1);
        p1.add(button2);
        p1.add(button3);
        p1.add(button4);
        p1.add(button5);
        p1.add(button6);
        p1.add(button7);
        p1.add(button8);
        p1.add(button9);
        p1.add(buttonPoint);
        p1.add(buttonEqual);

        //add panel p1 in panel panel
        panel.add("Center", p1);

        JFrame frame = new JFrame("Калькулятор");
        frame.setContentPane(panel);

        //set size window
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // связываем воедино слушателя(Активность мыши) и наше графическое представление калькулятора

        button0.addActionListener(calculator);
        button1.addActionListener(calculator);
        button2.addActionListener(calculator);
        button3.addActionListener(calculator);
        button4.addActionListener(calculator);
        button5.addActionListener(calculator);
        button6.addActionListener(calculator);
        button7.addActionListener(calculator);
        button8.addActionListener(calculator);
        button9.addActionListener(calculator);
        buttonPoint.addActionListener(calculator);
        buttonEqual.addActionListener(calculator);

    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();


        //метод выводит диалоговое сообщение с текстом
        /*JOptionPane.showConfirmDialog(
                null, "You press \"" + clickedButtonLabel + "\"",
                "Название диалогового окна",
                JOptionPane.PLAIN_MESSAGE
        );*/
        //считывает тект который отображается в дисплее
        String displeyFieldText = calculator.displayField.getText();

        //read text in button
        String clickedButtonLabel = clickedButton.getText();

        calculator.displayField.setText(displeyFieldText + clickedButtonLabel);
    }
}
