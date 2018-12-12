package Games.CaS.players;

import Games.CaS.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static Games.CaS.ConcoleHelper.*;
import static Games.CaS.Model.listPlayers;

/**
 * Класс - родитель всех игроков
 * Created by 1 on 03.09.2017.
 */
public abstract class Player implements Serializable {
    private String name;
    private String type;//human or bot
    private int countLoss = 0;
    private int countWin = 0;
    private static final long serialVersionUID = 1L;//хз как этим пользоваться, номер от балды...


     /**
     * Возвращает список игроков выбранного типа.
     * @param type - type player
     * @return list players a certain type.
     */
    public static List<Player> getPlayersByType(String type) {
        ArrayList<Player> list = new ArrayList<>();
        if (!listPlayers.isEmpty()) {
            for (Player player : listPlayers) {
                if (player.getType().equals(type)) {
                    list.add(player);
                }
            }
        }
        return list;
    }

    /**
     * Печатает список игроков определенного типа.
     * @param type
     * @return true if have player find type
     */
    public static boolean printListOfPlayersOfACetrainType(String type) {
        boolean flag = false;
        List<Player> list = getPlayersByType(type);
        System.out.print ("Игроки типа - " + type);
        if (!list.isEmpty()) {
            flag = true;
            writeMessage(":");
            for (Player player : list) {
                writeMessage(player.getName());
            }
        } else {
            writeMessage(" не найдены.");
        }
        return flag;
    }

    public void move(Controller controller, String value) {
        writeMessage("Ходит " + this.getName() + ".");
    }

    @Override
    public String toString() {
        String name = this.name.codePointCount(0, this.name.length()) > 10 ?
                this.name.substring(0, this.name.offsetByCodePoints(0, 10)) : this.name;
        String result = String.format("%-10s  %-5s  %4d  %4d  %4.0f %9d",
                name, type, countLoss, countWin, getWinningPercentage(), getCountGame());
        return  result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (!getName().equals(player.getName())) return false;
        return getType().equals(player.getType());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getType().hashCode();
        return result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountWin() {
        this.countWin++;
    }

    public void setCountLoss() {
        this.countLoss++;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Double getWinningPercentage() {
        Double winningPercentage;
        try {
            winningPercentage = (double)countWin / getCountGame() * 100;
        } catch (ArithmeticException exc) {
            winningPercentage = 0.0;
        }
        return winningPercentage;
    }

    public String getType() {
        return type;
    }

    public int getCountGame() {
        return countLoss + countWin;
    }
}
