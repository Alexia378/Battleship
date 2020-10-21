package battleship;

public class Submarine extends Ship {
    public Submarine(){
        length = 1;
        hit = new boolean[1];
    }

    @Override
    String getShipType(){
        return "submarine";
    }
}
