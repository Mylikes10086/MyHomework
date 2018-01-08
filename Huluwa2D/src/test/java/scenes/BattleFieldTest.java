package scenes;

import creatures.Huluwa;
import creatures.Monster;
import junit.framework.TestCase;

public class BattleFieldTest extends TestCase {

    public void testConflicts() throws Exception {
        BattleField f = new BattleField(10,10);
        f.layoutCreature(new Huluwa(1,f),new Grid(0,0));
        assertTrue(f.conflicts(f.getGrids()[0][0]));

    }

    public void testAlreadyLaid() throws Exception {
        BattleField f = new BattleField(10,10);
        Huluwa h = new Huluwa(1,f);
        f.layoutCreature(h,new Grid(0,0));
        assertTrue(f.alreadyLaid(h));
    }


}