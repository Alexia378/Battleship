package battleship;

/**
 * class represents Ship with basic methods for
 * all types of ships
 */

abstract class Ship {

    /**
     * constants of maximum and minimum coordinates on the field
     */
    private static final int MAX_COORDINATE = 9;
    private static final int MIN_COORDINATE = 0;

    /**
     * the row (0 to 9) which contains the bow (front) of the ship
     */
    private int bowRow;

    /**
     * the column (0 to 9) which contains the bow (front) of the ship
     */
    private int bowColumn;

    /**
     * the number of squares occupied by the ship.
     * An "empty sea" location has length 1
     */
    int length;

    /**
     * true if the ship occupies a single row, false otherwise
     */
    private boolean horizontal;

    /**
     * an array of booleans telling whether that part of the ship has been hit.
     * Only battleships use all four locations;
     * cruisers use the first three; destroyers 2;
     * submarines 1;
     * and "empty sea" either one or none.
     */
    boolean[] hit = new boolean[4];

    /**
     * getter for array of hits for the ship
     * @return array of hits
     */
    public boolean[] getHit() { return hit; }

    /**
     * Returns the length of this particular ship
     * @return length of the ship
     */
    public int getLength() { return length; }

    /**
     * getter for bowRow
     * @return bowRow's value
     */
    public int getBowRow() { return bowRow; }

    /**
     * getter for bowColumn
     * @return bowColumn's value
     */
    public int getBowColumn() {return bowColumn;}

    /**
     * getter for horizontal
     * @return horizontal's value
     */
    public boolean isHorizontal() { return horizontal; }

    /**
     * setter for bowRow
     * @param row - new value of bowRow
     */
    public void setBowRow(int row) { bowRow = row; }

    /**
     * setter for bowColumn
     * @param column - new value of bowColumn
     */
    public void setBowColumn(int column) { bowColumn = column; }

    /**
     * setter for horizontal
     * @param horizontal-new value for horizontal
     */
    public void setHorizontal(boolean horizontal) { this.horizontal = horizontal; }

    /**
     * method is to be overridden
     * @return the type of the ship
     */
    String getShipType() {return "Ship"; }

    /**
     * method which helps to check if it's ok to place ship in this particular field
     * @param row - row coordinate on the field
     * @param column - column coordinate on the field
     * @param ocean - game field
     * @return true if it's ok to place ship here6 false otherwise
     */
    boolean check(int row, int column, Ocean ocean){
        boolean ok = true;
        if(row >= MIN_COORDINATE && row <= MAX_COORDINATE &&
                column >= MIN_COORDINATE && column <= MAX_COORDINATE && ocean.isOccupied(row, column))
            ok = false;

        return ok;
    }

    /**
     * method checks if it's ok to place ship
     * @param row - row to place at
     * @param column - column to place at
     * @param horizontal - orientation
     * @param ocean - field
     * @return true if it is okay to put a ship of this length with its bow in this location
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
        if(horizontal && column + getLength() - 1 > MAX_COORDINATE)
            return false;
        if(!horizontal && row + getLength() - 1 > MAX_COORDINATE)
            return false;

        boolean ok = true;

        if(horizontal){
            for (int i = row - 1; i <= row + 1; i++){
                for (int j = column - 1; j <= column + getLength() + 1; j++)
                {
                    ok = check (i, j, ocean);
                }
            }
        } else {
            for (int i = row - 1; i <= row + getLength() + 1; i++){
                for (int j = column - 1; j <= column + 1; j++){
                    ok = check (i, j, ocean);
                }
            }
        }

        return ok;
    }

    /**
     * method, which places ship at given position
     * @param row - row to place at
     * @param column - column to place at
     * @param horizontal - orientation
     * @param ocean - field
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){
        setBowColumn(column);
        setBowRow(row);
        setHorizontal(horizontal);

        if (isHorizontal()){
            for(int i = 0; i < getLength(); i++)
                ocean.getShipArray()[row][column + i] = this;
        } else {
            for(int i = 0; i < getLength(); i++)
                ocean.getShipArray()[row + i][column] = this;
        }
    }

    /**
     * method checks if shot can be done
     * @param row - row to shoot at
     * @param column - column to shoot at
     * @return true if it's possible to make shot
     */
    boolean shootAt(int row, int column){
        if (!isSunk()) {
            if(isHorizontal()) {
                hit[column - getBowColumn()] = true;
            } else {
                hit[row - getBowRow()] = true;
            }
        } else
            return false;
        return true;
    }

    /**
     * method checks if this ship is already sunk
     * @return true if every part of the ship has been hit
     */
    boolean isSunk(){
        boolean sunk = true;
        for(int i = 0; i < getLength(); i++)
        {
            if (!hit[i]) {
                sunk = false;
                break;
            }
        }

        return sunk;
    }

    /**
     * returns simbol for ship if it's sunk or not for player's field
     * @return X if sunk, S if not
     */
    @Override
    public String toString(){
        if(this.isSunk())
            return "X";
        else return "S";
    }
}
