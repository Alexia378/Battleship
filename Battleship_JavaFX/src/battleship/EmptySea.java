package battleship;

/**
 * class represents field of the ocean without ship
 */

public class EmptySea extends Ship {
    public EmptySea() {
        length = 1;
        hit = new boolean[1];
    }

    /**
     * overridden method
     * @param row - row to shoot at
     * @param column - column to shoot at
     * @return false
     */
    @Override
    boolean shootAt(int row, int column){
        hit[0] = true;
        return false;
    }

    /**
     * overridden method
     * @return false
     */
    @Override
    boolean isSunk() {
        return false;
    }

    /**
     * overridden method
     * @return - if there was no hit, . otherwise
     */
    @Override
    public String toString() {
        if(hit[0])
            return "-";
        return ".";
    }
}
