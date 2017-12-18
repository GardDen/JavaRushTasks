package games.game2048;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 96;
    private static final int TILE_MARGIN = 12;

    private Controller controller;

    boolean isGameWon = false;
    boolean isGameLost = false;

    /**
     * Cosnstructor.
     * @param controller controller.
     */
    public View(Controller controller) {
        setFocusable(true);
        this.controller = controller;
        addKeyListener(controller);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.setColor(BG_COLOR);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                drawTile(graphics, controller.getGameTiles()[y][x], x, y);
            }
        }

        graphics.drawString("Score: " + controller.getScore(), 140, 465);
    }

    private void drawTile(Graphics g2, Tile tile, int x, int y) {
        Graphics2D graphics = ((Graphics2D) g2);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int value = tile.value;
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);
        graphics.setColor(tile.getTileColor());
        graphics.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE , 8, 8);
        graphics.setColor(tile.getFontColor());
        final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
        final Font font = new Font(FONT_NAME, Font.BOLD, size);
        graphics.setFont(font);

        String text = String.valueOf(value);
        final FontMetrics fm = getFontMetrics(font);

        final int w = fm.stringWidth(text);
        final int h = -(int) fm.getLineMetrics(text, graphics).getBaselineOffsets()[2];

        if (value != 0) {
            graphics.drawString(text, xOffset
                    + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);
        }
    }

    private static int offsetCoors(int arg) {
        return arg * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
    }
}
