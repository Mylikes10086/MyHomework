package utility;

import creatures.Creature;
import scenes.Grid;

public class CraneFormation extends Formation{
    public CraneFormation(Creature[] creatures) {
        super(creatures.length/2+1, creatures.length);
        creatures[0].setGrid(new Grid(0, height /2));
        int i=0;
        for(int j = creatures.length/2; i <= creatures.length/2; i++, j--){
            creatures[i + 1].setGrid(new Grid(j, i));
            this.creatures.add(creatures[i]);
        }
        for(int j = 1; i < height; i++, j++){
            creatures[i].setGrid(new Grid(j, i));
            this.creatures.add(creatures[i]);
        }
    }

    @Override
    public void reset() {
        for (Creature c:creatures) {
            c.reborn();
        }
        creatures.get(0).setGrid(new Grid(0, height /2));
        int i = 0;
        for(int j = creatures.toArray().length/2; i <= creatures.toArray().length/2; i++, j--){
            creatures.get(i + 1).setGrid(new Grid(j, i));
        }
        for(int j = 1; i < height; i++, j++){
            creatures.get(i).setGrid(new Grid(j, i));
        }
    }
}
