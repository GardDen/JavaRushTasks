package Games.CaS.players;

import Games.CaS.Controller;

import static Games.CaS.ConcoleHelper.readNumberTile;

/**
 * Created by 1 on 03.09.2017.
 */
public class Human extends Player {
    private static final long serialVersionUID = 1L;

    public Human(String name) {
        setName(name);
        setType("human");
    }

    /**
     * Реализует ход игрока. Записывает в выбранную позицию крестик и выводит результат.
     */
    @Override
    public void move(Controller controller, String value){
        super.move(controller, value);
        int height = 0;
        int width = 0;
        //ход таких то
        System.out.println("Выберите позицию:");
        int numberPosition = 0;
        do {
            numberPosition = readNumberTile();
            switch (numberPosition) {
                case 7:
                    width = 0;
                    height = 0;
                    break;
                case 8:
                    width = 0;
                    height = 1;
                    break;
                case 9:
                    width = 0;
                    height = 2;
                    break;
                case 4:
                    width = 1;
                    height = 0;
                    break;
                case 5:
                    width = 1;
                    height = 1;
                    break;
                case 6:
                    width = 1;
                    height = 2;
                    break;
                case 1:
                    width = 2;
                    height = 0;
                    break;
                case 2:
                    width = 2;
                    height = 1;
                    break;
                case 3:
                    width = 2;
                    height = 2;
                    break;

            }
            if (controller.getField()[width][height].notIsEmpty()) {
                System.out.println("Позиция уже занята попробуйте ещё раз.");
            } else break;
        } while (true);
        controller.getField()[width][height].setValue(value);
        controller.printField();
    }
}
