package battleship;

public class Destroyer extends Ship {
    public Destroyer() {
        length = 2;
        hit = new boolean[2];
    }

    @Override
    String getShipType(){
        return "destroyer";
    }
}
