package Games.CaS;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 * Created by 1 on 26.09.2017.
 */
public class View extends JFrame {
    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 96;
    private static final int TILE_MARGIN = 12;
    final int WINDOW_SIZE = 300;
    final int FIELD_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;

    private Controller controller;
    Canvas canvas = new Canvas();

    public View(Controller controller) {
        this.controller = controller;
        setTitle("Crosses and Zeros");//Главное окно
        setDefaultCloseOperation(EXIT_ON_CLOSE);//закрытие окна и выход при нажатии крестика в окне
        setLocation(800, 200);
        Dimension dimension = new Dimension(400, 400);
        setSize(dimension);

        //добавляем кнопки выхода и прочие
        JButton newGame = new JButton("NEW GAME");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.game();
                canvas.repaint();
            }
        });
        JButton exit = new JButton("EXIT");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //добавляем в главное окно панель отвечающую за действия
        JPanel panel = new JPanel();
        canvas.setBackground(Color.white);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX()/CELL_SIZE;
                int y = e.getY()/CELL_SIZE;
                //human.turn(x, y);
                canvas.repaint();
            }
        });

        panel.setLayout(new GridLayout());
        panel.add(newGame);
        panel.add(exit);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);
        setVisible(true);
    }


    public void paint(Graphics g) {
        g.setColor(Color.lightGray);
        for (int i = 1; i < FIELD_SIZE; i++) {
            g.drawLine(0, i*CELL_SIZE, WINDOW_SIZE, i*CELL_SIZE);
            g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, WINDOW_SIZE);
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                if (controller.getField()[x][y].getValue().equals("X")) {
                    g.setColor(Color.blue);
                    g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4));
                    g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4));
                }
                if (controller.getField()[x][y].getValue().equals("O")) {
                    g.setColor(Color.red);
                    g2.draw(new Ellipse2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, CELL_SIZE/2, CELL_SIZE/2));
                }
            }
        }
    }

    /*@Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.setColor(BG_COLOR);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                drawTile(graphics, controller.getField()[y][x], x, y);
            }
        }

        //Счет побед поражений
        //graphics.drawString("Score: " + controller.getScore(), 140, 465);
    }

    private void drawTile(Graphics g2, Cell cell, int x, int y) {
        Graphics2D graphics = ((Graphics2D) g2);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        String value = cell.getValue();
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);
        //graphics.setColor(cell.getTileColor());
        graphics.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE , 8, 8);
        // graphics.setColor(cell.getFontColor());
        final int size = 36;
        final Font font = new Font(FONT_NAME, Font.BOLD, size);
        graphics.setFont(font);

        String text = value;
        final FontMetrics fm = getFontMetrics(font);

        final int w = fm.stringWidth(text);
        final int h = -(int) fm.getLineMetrics(text, graphics).getBaselineOffsets()[2];

        graphics.drawString(text, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);
    }

    private static int offsetCoors(int arg) {
        return arg * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
    }*/




}
