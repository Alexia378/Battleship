package battleship;

import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;
import java.awt.*;

public class Field extends GridPane {

    double FIELD_SIZE;
    double side;

    final String miss=".";
    final String hit="X";

    TextArea info;
    TextArea log;

    Ocean ocean;

    public Field(TextArea info, TextArea log){
        this.log = log;
        this.info = info;
    }

    void setStyle(){

    }

    void generateOcean(){
        ocean=new Ocean();
        ocean.placeAllShipsRandomly();
        info.clear();
        log.clear();
    }

    void showInfo(){

    }
}
