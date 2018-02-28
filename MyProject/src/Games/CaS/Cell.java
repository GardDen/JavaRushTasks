package Games.CaS;

/**
 * Created by 1 on 04.09.2017.
 */
public class Cell {
    private int widthPosition;
    private int heightPosition;
    private String value;


    public Cell(int widthPosition, int heightPosition, String valueInitial) {
        this.widthPosition = widthPosition;
        this.heightPosition = heightPosition;
        value = valueInitial;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        Cell cell = (Cell) o;
        return this.value.equals(cell.value);
    }

    /**
     * Если ячейка занята то возвращает истину
     * @return
     */
    public boolean notIsEmpty() {
        boolean result = !(this.getValue().equals(Model.getValueInitial()));
        return result;
    }

}
