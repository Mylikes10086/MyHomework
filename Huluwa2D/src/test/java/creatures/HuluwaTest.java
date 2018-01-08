package creatures;

import org.junit.Test;
import scenes.BattleField;
import scenes.Grid;

import static org.junit.Assert.*;

public class HuluwaTest {
    @Test
    public void wounded() throws Exception {
        Huluwa h = new Huluwa(1,new BattleField(15,10));
        h.wounded(3);
        assertEquals(2,h.current_HP);
    }

    @Test
    public void move() throws Exception {
        BattleField field = new BattleField(15,10);
        Huluwa h = new Huluwa(1,field);
        field.layoutCreature(h,new Grid(0,0));
        h.move(1,1);
        assertEquals(field.getGrids()[1][1],h.getGrid());
    }

    @Test
    public void getClosestEnemy() throws Exception {
        BattleField field  = new BattleField(15,10);
        Huluwa h = new Huluwa(1,field);
        Monster m1 = new Monster();
        Monster m2 = new Monster();
        field.layoutCreature(h,new Grid(0,0));
        field.layoutCreature(m1,new Grid(5,4));
        field.layoutCreature(m2,new Grid(2,5));
        Creature c = h.getClosestEnemy();
        assertEquals(m2,c);


    }



}
