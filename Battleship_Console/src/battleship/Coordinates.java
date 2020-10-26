package battleship;

/**
 * class represents coordinates on the game field
 */

final class Coordinates {
    /**
     * Represents a row coordinate of the ship
     */
    int row;

    /**
     * Represents a column coordinate of the ship
     */
    int column;

    public Coordinates(int row, int column){
        this.row = row;
        this.column = column;
    }

    /**
     * Get-accessor for getting row
     * @return integer number of row
     */
    public int getRow(){
        return row;
    }

    /**
     * Get-accessor for getting column
     * @return integer number of column
     */
    public int getColumn(){
        return column;
    }
}
