package Games.CaS.players;

import Games.CaS.Controller;

/**
 * Created by 1 on 03.09.2017.
 */
public class Bot extends Player {
    private static final long serialVersionUID = 1L;

    public Bot(String name) {
        setName(name);
        setType(" bot ");
    }

    @Override
    public void move(Controller controller, String value) {
        super.move(controller, value);
        int width;
        int height;
        try {//The faux pause
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error in thr process bot - pause");
        }

        do {
            width = (int) (Math.random() * 3);
            height = (int) (Math.random() * 3);
        } while (controller.getField()[width][height].notIsEmpty());
        controller.getField()[width][height].setValue(value);
        controller.printField();
    }


    public void searchGoodPosition() {

    }

    public void searchBadPosition() {

    }


}
