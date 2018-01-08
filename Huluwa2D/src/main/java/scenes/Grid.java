package scenes;

import creatures.Creature;

import java.awt.*;

public class Grid {
    private int x,y;
    private  boolean occupied;
    private Creature holder;
    private BattleField field;
    private int offsetx = 100;
    private int offsety = 150;
    private int scale = 50;//

    public Grid() {
        this.x =0;
        this.y =0;
        this.occupied=false;
        this.holder= null;

    }
    public Grid(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied() {
        this.occupied = true;
    }

    public void cleanOccupied() {
        this.occupied = false;
    }

    public int getX() {return x;}

    public int getY() { return y; }

    public void setHolder(Creature holder) {
        if (holder!=null) {
        this.holder = holder;
        this.setOccupied();}
    }

    public void setNull(){
        this.holder=null;
        this.cleanOccupied();
    }

    public Creature getHolder() {
        return holder;
    }

    public void drawGrid(Graphics g) {
        g.setColor(new Color(255,255,255,125));
        g.fillRect(x* scale + offsetx,y* scale + offsety, scale,scale);
        if (isOccupied()) {
            g.drawImage(holder.getImage(),x* scale + offsetx,y* scale + offsety, scale,scale,field);
        }
    }

    public void setField(BattleField field) {
        this.field = field;
    }

    public boolean isNearBy(Grid grid){
        if(Math.abs(this.x - grid.getX())+Math.abs(this.y - grid.getY()) == 1) return true;
        return false;
    }


}
