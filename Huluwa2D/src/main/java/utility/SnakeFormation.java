package utility;

import creatures.Creature;
import scenes.Grid;

public class SnakeFormation extends Formation {
    public SnakeFormation(Creature[] creatures) {
        super(1,creatures.length);
        for(int i = 0; i < creatures.length; i++){
            creatures[i].setGrid(new Grid(0,i));
            this.creatures.add(creatures[i]);
        }
    }

    @Override
    public void reset() {
        for(Creature creature:creatures){creature.reborn();}
        for(int i = 0; i < creatures.toArray().length; i++) {
            creatures.get(i).setGrid(new Grid(0, i));
        }
    }
}
