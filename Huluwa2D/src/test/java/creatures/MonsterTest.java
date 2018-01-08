package creatures;


import org.junit.Test;
import scenes.BattleField;
import scenes.Grid;

import static org.junit.Assert.*;


public class MonsterTest {
    @Test
    public void wounded() throws Exception {
        Huluwa h = new Huluwa(1,new BattleField(15,10));
        h.wounded(3);
        assertEquals(2,h.current_HP);
    }

    @Test
    public void move() throws Exception {
        BattleField field = new BattleField(15,10);
        Monster m = new Monster();
        field.layoutCreature(m,new Grid(0,0));
        m.move(1,1);
        assertEquals(field.getGrids()[1][1],m.getGrid());
    }

    @Test
    public void getClosestEnemy() throws Exception {
        BattleField field  = new BattleField(15,10);
        Monster m = new Monster();
        Huluwa h1 = new Huluwa(1,field);
        Huluwa h2 = new Huluwa(2,field);
        field.layoutCreature(m,new Grid(0,0));
        field.layoutCreature(h1,new Grid(5,4));
        field.layoutCreature(h2,new Grid(2,5));
        Creature c = m.getClosestEnemy();
        assertEquals(h2,c);
    }

}
