package battleship;

public class EmptySea extends Ship {
    public EmptySea() {
        length = 1;
        hit = new boolean[1];
    }

    @Override
    boolean shootAt(int row, int column){
        hit[0] = true;
        return false;
    }

    @Override
    boolean isSunk() {
        return false;
    }

    @Override
    public String toString() {
        if(hit[0])
            return "-";
        return ".";
    }
}
