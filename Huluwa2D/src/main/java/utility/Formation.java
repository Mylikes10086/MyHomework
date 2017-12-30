package utility;

import creatures.Creature;
import scenes.Field;

import java.awt.*;
import java.util.ArrayList;

public class Formation {
    private Field field;

    public Formation(Field field) {
        this.field = field;
    }

    public void shexingzhen(ArrayList<Creature> creatures, double x, double y) {
        double nx = x ;
        double ny = y ;
        for (Creature c :
                 creatures) {
            int offset = c.getImage().getHeight(null);
            ny += offset+5;
            nx += offset+5;
            c.setX(nx);
            c.setY(ny);
            System.out.println("huluwa");
        }
    }
}
