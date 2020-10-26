package battleship;

import java.util.Objects;
import java.util.Scanner;

public class BattleshipGame {

    /**
     * Method which basically runs the game.
     */
    static void startGame(){
        printRules();

        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();

        while(!ocean.isGameOver()){
            printOcean(ocean);
            Coordinates  coordinates = getCoordinates();
            if (ocean.shootAt(coordinates.getRow(), coordinates.getColumn())){
                System.out.println("---> Hit! <---");
            } else {
                System.out.println("---> Miss! <---");
            }
            if (ocean.getShipArray()[coordinates.getRow()][coordinates.getColumn()].isSunk()){
                System.out.println("---> Enemy is sunk <---" +
                        ocean.getShipArray()[coordinates.getRow()][coordinates.getColumn()].getShipType() + "!");
            }
        }

        System.out.println("---> Congratulations! You've won this game! <---");
        System.out.println("---> Here is your statistics: ");
        printOcean(ocean);

        System.out.println("If you want to leave the game, type 'exit'.");
        System.out.println("If you want to play again press any key to start a new game!");
    }

    /**
     * Method which prints the rules of the game.
     */
    static void printRules(){
        System.out.println();
        System.out.println("---> Hello! Welcome to a new game of Battleship! <---");
        System.out.println("---> Our main aim and the rules! <---");
        System.out.println("---> The enemy fleet consists of 10 ships. They are placed in the limited area (10x10). <---");
        System.out.println("---> Since their ships are very large, they can't be placed near each other horizontally, " +
                "vertically or diagonally!");
        System.out.println("---> Our battleship has only one cannon, so you need to tell us where to shoot! <---");
        System.out.println("---> You need to type coordinates of enemy ships, when asked! <---");
        System.out.println("---> Let's play! <---");
        System.out.println();
    }

    /**
     * Method prints introduction info,
     * when user runs the application for the first time.
     */
    static void printIntroduction(){
        System.out.println("---> Dear user, you've just started the BattleShip game! <---");
        System.out.println("---> To win this game you need to sink all enemy ships first! <---");
        System.out.print("---> ");
    }

    /**
     * method prints Ocean with its ships.
     * At the same time prints statistics about shots, hits and sunk ships.
     * @param ocean represents the battlefield
     */
    static void printOcean(Ocean ocean){
        ocean.print();
        System.out.println("Shots: " + ocean.getShotsFired());
        System.out.println("Hits: " + ocean.getHitCount());
        System.out.println("Ships sunk: " + ocean.getShipsSunk());
    }

    /**
     * Method for getting coordinates from user.
     * We need to get row and column here, so it asks to enter them.
     * @return instance of Coordinates class, which contains row and column coordinate
     */
    static Coordinates getCoordinates(){
        System.out.println("---> Set the coordinates to shoot at! <---");
        int row = readInt("Enter row coordinate (0-9): ");
        int column = readInt("Enter column coordinate (0-9): ");
        System.out.println("---> Fire! <---");
        System.out.println("---> ...");

        return new Coordinates(row, column);
    }

    /**
     * Gets users input. If it isn't correct informs the user and asks to enter a new number.
     * @param msg message, which informs user, what he should enter
     * @return integer number
     */
    static int readInt(String msg){
        int temp = 0;
        boolean flag;

        do {
            try {
                System.out.print(msg);

                // read new input
                Scanner in = new Scanner(System.in);
                String input = in.nextLine();
                temp = Integer.parseInt(input);

                // if it is out of bounds, inform the user
                if (temp > 9 || temp < 0){
                    flag = false;
                    System.out.println("---> Coordinate is out of the field!");
                } else {
                    flag = true;
                }
            } catch(NumberFormatException ex) {
                System.out.println("Incorrect coordinate!");
                flag = false;
            }
        } while (!flag);
        return temp;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        printIntroduction();
        input = in.nextLine();

        while(!Objects.equals(input, "exit")){
            startGame();
            input = in.nextLine();
        }

        System.out.println("Good bye!");
    }
}
