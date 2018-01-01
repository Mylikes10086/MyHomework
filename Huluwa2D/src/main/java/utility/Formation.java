package utility;

import creatures.Creature;
import scenes.BattleField;
import scenes.Grid;

import java.util.ArrayList;

public abstract class Formation {
    protected int width=9;
    protected int height=9;

    protected Grid start;
    protected ArrayList<Creature> creatures;

    public Formation(int width,int height) {
        this.width = width;
        this.height = height;
        this.creatures =  new ArrayList<Creature>();
        this.start = new Grid(0,0);
    }

    public Formation() {}

    public Grid getStart() {
        return start;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setStart(Grid start) {
        this.start = start;
    }

    public  abstract void reset();
}
