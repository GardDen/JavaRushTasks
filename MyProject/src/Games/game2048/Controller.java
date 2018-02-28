package Games.game2048;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by 1 on 06.11.2017.
 */
public class Controller extends KeyAdapter {
    private static int WINNING_TILE = 2048;
    Model model;
    View view;

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    /**
     * The method start new menu.
     */
    public void resetGame() {
        view.isGameLost = false;
        view.isGameWon = false;
        model = new Model();


    }

    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        int cod = event.getKeyCode();
        if (KeyEvent.VK_ESCAPE == cod) {
            resetGame();
        }
        if (!model.canMove()) {
            view.isGameLost = true;
        }
        if (!view.isGameLost && !view.isGameWon) {
            switch (cod) {
                case KeyEvent.VK_LEFT:
                    model.left();
                    break;
                case KeyEvent.VK_RIGHT:
                    model.right();
                    break;
                case KeyEvent.VK_UP:
                    model.up();
                    break;
                case KeyEvent.VK_DOWN:
                    model.down();
                    break;
                case KeyEvent.VK_Z:
                    model.rollback();
                    break;
                case KeyEvent.VK_R:
                    model.randomMove();
                    break;
                case KeyEvent.VK_A:
                    model.autoMove();
                    break;
                default:
                    break;
            }
        } else {
            if (view.isGameWon) {
                JOptionPane.showMessageDialog(view,"You've won!");
            } else if (view.isGameLost) {
                JOptionPane.showMessageDialog(view, "You've lost :(");
            }
        }
        if (model.maxTile == WINNING_TILE) {
            view.isGameWon = true;
        }
        view.repaint();
    }

    public View getView() {
        return view;
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.score;
    }

}
