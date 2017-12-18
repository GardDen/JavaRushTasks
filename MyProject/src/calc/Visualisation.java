package com.my_project.calc;

import javax.swing.JOptionPane;
/**
 * Created by 1 on 05.12.2016.
 */
public class Visualisation
{
    int number;
    String text;

    public void scanN()
    {
        text = JOptionPane.showInputDialog("Вы готовы?");
        number = Integer.parseInt(text);
    }

    public int scan(String s)
    {
        text = JOptionPane.showInputDialog(s);
        number = Integer.parseInt(text);
        return number;
    }


}
