package games.game2048;


import java.awt.*;

/**
 * Created by 1 on 06.11.2017.
 * This is class describe object of field - tile.
 */
public class Tile {
    int value;

    @Override
    public String toString() {
        return "" + value;
    }

    public Tile(int value) {
        this.value = value;
    }

    public Tile() {
        this.value = 0;
    }

    /**
     * This is method checked tile for empty.
     * @return boolean value.
     */
    public boolean isEmpty() {
        if (this.value == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This is method will pick color of font of tile.
     * @return the color of font of tile.
     */
    public Color getFontColor() {
        if (value < 16) {
            return new Color(0x776e65);
        } else {
            return new Color(0xf9f6f2);
        }
    }

    /**
     * The method pesponsible for color of tile a depensiving of value of tile.
     * @return color tile.
     */
    public Color getTileColor() {
        switch (value) {
            case 0:
                return new Color(0xcdc1b4);
            case 2:
                return new Color(0xeee4da);
            case 4:
                return new Color(0xede0c8);
            case 8:
                return new Color(0xf2b179);
            case 16:
                return new Color(0xf59563);
            case 32:
                return new Color(0xf67c5f);
            case 64:
                return new Color(0xf65e3b);
            case 128:
                return new Color(0xedcf72);
            case 256:
                return new Color(0xedcc61);
            case 512:
                return new Color(0xedc850);
            case 1024:
                return new Color(0xedc53f);
            case 2048:
                return new Color(0xedc22e);
            default:
                return new Color(0xff0000);
        }
    }
}
