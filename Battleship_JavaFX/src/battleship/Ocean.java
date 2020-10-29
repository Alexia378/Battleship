package battleship;

/*
  class represents game field
 */

import java.util.Random;

class Ocean {

    /**
     * randomizer
     */
    final Random rand = new Random();

    /**
     * Used to quickly determine which ship is in any given location.
     */
    Ship[][] ships = new Ship[10][10];

    /**
     * The total number of shots fired by the user
     */
    int shotsFired;

    /**
     * The number of times a shot hit a ship
     */
    int hitCount;

    /**
     * The number of ships sunk (10 ships in all)
     */
    int shipsSunk;

    public Ocean(){
        for(int i = 0; i < 10; i++){
            for(int j = 0;j < 10; j++){
                ships[i][j] = new EmptySea();
            }
        }

        hitCount = 0;
        shipsSunk = 0;
        shotsFired = 0;
    }

    /**
     * Place all ten ships randomly on the (initially empty) ocean
     */
    public void placeAllShipsRandomly() {
        for(int i = 0; i < 1; i++)
            placeShip(new Battleship());
        for(int i = 0; i < 2; i++)
            placeShip(new Cruiser());
        for(int i = 0; i < 3; i++)
            placeShip(new Destroyer());
        for(int i = 0; i < 4; i++)
            placeShip(new Submarine());
    }

    /**
     * method to place particular ship
     * @param s - current ship
     */
    void placeShip(Ship s) {
        int row;
        int column;
        boolean horizontal;

        do{
            horizontal = rand.nextBoolean();

            if (horizontal){
                row = rand.nextInt(10);
                column = rand.nextInt(10 - s.getLength());
            } else {
                row = rand.nextInt(10 - s.getLength());
                column = rand.nextInt(10);
            }
        }while(!s.okToPlaceShipAt(row, column, horizontal, this));
    }
    /**
     * method checks if field is occupied
     * @param row - row of the field
     * @param column - column of the field
     * @return true if the given location contains a ship, false if it does not.
     */
    public boolean isOccupied(int row, int column) {
        if(column < 0 || column > 9 || row < 0 || row > 9)
            return true;
        return !(getShipArray()[row][column] instanceof EmptySea);
    }

    /**
     * method checks6 if there's still a ship here
     * @param row - row of the field
     * @param column - column of the field
     * @return true if the given location contains a "real" ship, still afloa
     */
    public boolean shootAt(int row, int column) {
        shotsFired++;
        Ship s = getShipArray()[row][column];
        boolean result = s.shootAt(row, column);
        if(result){
            hitCount++;
            if(s.isSunk())
                shipsSunk++;
        }

        return result;
    }

    /**
     * getter of the shotsFired
     * @return the number of shots fired
     */
    public int getShotsFired() { return shotsFired; }

    /**
     * getter of the hitCount
     * @return the number of the hits
     */
    public int getHitCount() { return hitCount; }

    /**
     * getter of the shipsSunk
     * @return the number of ships sunk
     */
    public int getShipsSunk() { return shipsSunk; }

    /**
     * method checks if all the ships are sunk
     * @return true if all ships have been sunk
     */
    public boolean isGameOver() {
        return getShipsSunk() == 10;
    }

    /**
     * method represents field of ships
     * @return the 10x10 array of ships
     */
    public Ship[][] getShipArray(){
        return ships;
    }

    /**
     * method checks if this field has already been shot at
     * @param row - row of the field
     * @param column - column of the field
     * @param s - current ship/empty sea
     * @return true6 if field has already been fired at
     */
    boolean fired(int row, int column, Ship s){
        boolean fired;
        if(s instanceof EmptySea)
            fired = true;
        else if (s.isHorizontal())
            fired = s.getHit()[column - s.getBowColumn()];
        else
            fired = s.getHit()[row - s.getBowRow()];

        return fired;
    }
    /**
     * method prints the ocean
     */
    public void print() {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 10; i++){
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++){
                Ship s = getShipArray()[i][j];
                if (fired(i, j, s))
                    System.out.print(s.toString());
                else
                    System.out.print(".");

                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}
