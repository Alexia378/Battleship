package battleship;

/**
 * class that represents one-field ship
 */

public class Submarine extends Ship {
    public Submarine(){
        length = 1;
        hit = new boolean[1];
    }

    /**
     * overridden method of class Ship
     * @return type of given ship
     */
    @Override
    String getShipType(){
        return "submarine";
    }
}
