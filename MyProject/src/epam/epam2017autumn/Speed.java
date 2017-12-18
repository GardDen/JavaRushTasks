package epam.epam2017autumn;

import java.util.ArrayList;

/**
 * Created by 1 on 05.09.2017.
 */
public class Speed {
    private String valueText;
    private String unit;
    private double value;
    private double valueInMs;

    final private static ArrayList<String> listUnits = new ArrayList<>();
    static {
        listUnits.add("kmh");
        listUnits.add("mph");
        listUnits.add("kn");
        listUnits.add("ms");
    }

    public Speed(String value, String unit) {
        this.valueText = value;
        if (!isUnitCorrectly(unit)) System.exit(2);
        this.unit = unit;
        parseDouble();
        calculateValueInMs();
    }

    public boolean isUnitCorrectly(String unit) {
        return listUnits.contains(unit);
    }

    private void calculateValueInMs() {
        switch (this.unit) {
            case "kmh":
                valueInMs = this.value * 1000 / 3600;
                break;
            case "mph":
                valueInMs = this.value * 1609 / 3600;
                break;
            case "kn":
                valueInMs = this.value * 1852 / 3600;
                break;
            case "ms":
                valueInMs = this.value;
                break;
        }
    }

    private void parseDouble() throws NumberFormatException{
        try {
            value = Double.parseDouble(this.valueText);
        } catch (NumberFormatException exc) {
            System.exit(1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Speed)) return false;

        Speed speed = (Speed) o;

        if (getValueText() != null ? !getValueText().equals(speed.getValueText()) : speed.getValueText() != null)
            return false;
        return getUnit() != null ? getUnit().equals(speed.getUnit()) : speed.getUnit() == null;

    }

    @Override
    public int hashCode() {
        int result = getValueText() != null ? getValueText().hashCode() : 0;
        result = 31 * result + (getUnit() != null ? getUnit().hashCode() : 0);
        return result;
    }

    public String getUnit() {
        return unit;
    }

    public double getValueInMs() {
        return valueInMs;
    }

    public String getValueText() {
        return valueText;
    }
}
