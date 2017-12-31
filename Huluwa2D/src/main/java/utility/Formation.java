package utility;

import creatures.Creature;
import scenes.Field;
import scenes.Grid;

import java.awt.*;
import java.util.ArrayList;

public class Formation {
    private Field field;

    public Formation(Field field) {
        this.field = field;
    }



    public void shexingzhen(ArrayList<Creature> creatures, int x, int y) {
        int nx = x ;
        int ny = y ;
        for (Creature c :
                 creatures) {
            c.setGrid(field.getGrid(nx, ny));
            field.getGrid(nx, ny).setHolder(c);
            ny += 1;
            System.out.println("huluwa");
        }
    }
}
