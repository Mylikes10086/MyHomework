package scenes;

import utility.Thing2D;

import java.awt.*;

public class Grid {
    private int x,y;
    private  boolean occupied;
    private Thing2D holder;
    private BattleField field;

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

    public void setHolder(Thing2D holder) {
        if (holder!=null) {
        this.holder = holder;
        this.setOccupied();}
    }

    public void setNull(){
        this.holder=null;
        this.cleanOccupied();
    }

    public Thing2D getHolder() {
        return holder;
    }

    public void drawGrid(Graphics g) {
        g.drawRect(x*50,y*50,50,50);
        if (isOccupied()) {
            System.out.println("high");
            g.drawImage(holder.getImage(),x*50,y*50,50,50,field);
        }
    }

    public boolean isNearBy(Grid grid){
        if(Math.abs(this.x - grid.getX())+Math.abs(this.y - grid.getY()) == 1) return true;
        return false;
    }


}
