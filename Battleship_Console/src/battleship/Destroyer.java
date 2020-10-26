package battleship;

/**
 * class represents two-field ship
 */

public class Destroyer extends Ship {
    public Destroyer() {
        length = 2;
        hit = new boolean[2];
    }

    /**
     * overridden method
     * @return type of this ship
     */
    @Override
    String getShipType(){
        return "destroyer";
    }
}
