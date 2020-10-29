package battleship;

/**
 * class represents three-field ship
 */

public class Cruiser extends Ship{
    public Cruiser() {
        length = 3;
        hit = new boolean[3];
    }

    /**
     * overridden method
     * @return type of this ship
     */
    @Override
    String getShipType() {
        return "cruiser";
    }
}
