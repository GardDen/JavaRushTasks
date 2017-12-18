package games.game2048;

/**
 * Created by 1 on 09.11.2017.
 */
public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    /**
     * This is constructor.
     * @param numberOfEmptyTiles number empty tiles in all field.
     * @param score current score.
     * @param move current move.
     */
    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency object) {
        int flag = Integer.compare(this.numberOfEmptyTiles, object.numberOfEmptyTiles);
        if (flag == 0) {
            flag = Integer.compare(this.score, object.score);
        }
        return flag;
    }
}
