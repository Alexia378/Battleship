package battleship;

/**
 * class that represents for-field ship
 */

public class Battleship extends Ship {
    public Battleship(){
        length = 4;
        hit = new boolean[4];
    }

    /**
     * overridden method of class Ship
     * @return type of given ship
     */
    @Override
    String getShipType(){
        return "battleship";
    }
}
