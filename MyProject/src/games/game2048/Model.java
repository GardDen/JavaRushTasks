package games.game2048;

import java.util.*;

/**
 * Created by 1 on 06.11.2017.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    protected int score = 0;
    protected int maxTile = 2;
    private boolean isSaveNeeded = true;
    private Stack previousStates;
    private Stack previousScores;

    /**
     * Constructor
     * Initialize field of tiles a value of zero.
     */
    public Model() {
        resetGameTiles();
        previousScores = new Stack();
        previousStates = new Stack();
    }

    /**
     * The method implements work II.
     */
    public void autoMove() {
        Comparator<MoveEfficiency> comporator = Collections.reverseOrder();
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<MoveEfficiency>(4, comporator);
        queue.offer(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                left();
            }
        }));
        queue.offer(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                right();
            }
        }));
        queue.offer(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                up();
            }
        }));
        queue.offer(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                down();
            }
        }));
        queue.peek().getMove().move();

    }

    /**
     * The method calcullate efficiency of move.
     * @param move I dont know what is do!
     * @return object - moveEfficiency
     */
    public MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()) {
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
            rollback();
        } else {
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        }
        return  moveEfficiency;
    }

    /**
     * The method determine was change field or was not.
     * @return true if field will change
     */
    public boolean hasBoardChanged() {
        Tile[][] temp = (Tile[][])previousStates.peek();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value != temp[i][j].value) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * The method save state of ame.
     * @param tiles the is field of menu
     */
    private void saveState(Tile[][] tiles) {
        Tile[][] temp = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                temp[i][j] = new Tile(tiles[i][j].value);
            }
        }
        //push
        previousStates.push(temp);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    /**
     * The method load saving state of menu.
     */
    public void rollback() {
        if (!previousScores.isEmpty() && !previousStates.isEmpty()) {
            gameTiles = (Tile[][]) previousStates.pop();
            score = (int) previousScores.pop();
        }
    }

    /**
     * The method implements random move.
     */
    public void randomMove() {
        int random = ((int) (Math.random() * 100)) % 4;
        switch (random) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
            default:
                break;
        }
    }

    /**
     * This method dertermine will can move or no.
     * @return true if can do move
     */
    public boolean canMove() {
        if (getEmptyTiles().size() > 0) {
            return true;
        } else {
            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH - 1; j++) {
                    if (gameTiles[i][j].value == gameTiles[i][j + 1].value) {
                        return true;
                    }
                }
            }
            for (int i = 0; i < FIELD_WIDTH - 1; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    if (gameTiles[i][j].value == gameTiles[i + 1][j].value) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * The method turn matix the angle 90 degree counterclockwise.
     */
    private void turn() {
        int m = FIELD_WIDTH;
        Tile[][] A = gameTiles;

        // rotate
        for (int k=0; k<m/2; k++) // border -> center
        {
            for (int j=k; j<m-1-k; j++) // left -> right
            {
                // меняем местами 4 угла
                Tile tmp        = A[k][j];
                A[k][j]         = A[j][m-1-k];
                A[j][m-1-k]     = A[m-1-k][m-1-j];
                A[m-1-k][m-1-j] = A[m-1-j][k];
                A[m-1-j][k]     = tmp;
            }
        }
        gameTiles = A;
    }

    /**
     * The method responsible for move left.
     */
    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        boolean flag = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) || mergeTiles(gameTiles[i])) {
                flag = true;
            }
        }
        if (flag) {
            addTile();
        }
        isSaveNeeded = true;
    }

    /**
     * The method responsible for move up.
     */
    public void up() {
        saveState(gameTiles);
        turn();
        left();
        turn();
        turn();
        turn();
    }

    /**
     * The method responsible for move right.
     */
    public void right() {
        saveState(gameTiles);
        turn();
        turn();
        left();
        turn();
        turn();
    }

    /**
     * The method responsible for move down.
     */
    public void down() {
        saveState(gameTiles);
        turn();
        turn();
        turn();
        left();
        turn();
    }

    /**
     * This is method perform the compress tiles. All tile, what is value zero, move in the end.
     * @param tiles is list tile one string of field.
     * @return flag changed field.
     */
    private boolean compressTiles(Tile[] tiles) {
        boolean flag = false;
        for (int i = tiles.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (tiles[j].isEmpty() && !tiles[j + 1].isEmpty()) {
                    flag = true;
                    Tile temp = tiles[j];
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = temp;
                }
            }
        }
        return  flag;
    }


    /**
     * This is method perform the merger equal tiles in pairs.
     * @param tiles merged list tile of one string.
     * @return flag changed field
     */
    private boolean mergeTiles(Tile[] tiles) {
        boolean flag = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (!tiles[i].isEmpty() && tiles[i].value == tiles[i + 1].value) {
                flag = true;
                tiles[i].value += tiles[i + 1].value;
                tiles[i + 1].value = 0;

                if (tiles[i].value > maxTile) {
                    maxTile = tiles[i].value;
                }
                score += tiles[i].value;
                i++;
            }
        }
        if (compressTiles(tiles)) {
            flag = true;
        }
        return flag;
    }

    /**
     * This is method reset a menu.
     */
    public void resetGameTiles() {
        for (int i = 0; i < FIELD_WIDTH; i ++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    /**
     * This is method find the empty tiles a changed their value.
     */
    private void addTile() {
        ArrayList<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() > 0) {
            int numberTile = (int) (Math.random() * emptyTiles.size());
            emptyTiles.get(numberTile).value = Math.random() < 0.9 ? 2 : 4;
        }
    }


    /**
     * This is method find the empty tiles.
     * @return list contains empty tiles of field.
     */
    private ArrayList<Tile> getEmptyTiles() {
        ArrayList<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i ++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTiles;
    }

    /**
     * Getter.
     * @return field
     */
    public Tile[][] getGameTiles() {
        return gameTiles;
    }
}
