package scenes;

import creatures.Creature;
import utility.GameUtil;

import java.awt.*;

public class Grid<T extends Creature> {
    private int SCALE = GameUtil.SCALE;
    private int OFFSET = GameUtil.OFFEST;
    private int x=0,y=0;//格子的相对位置
    private boolean occupied = false;
    private T holder = null;
    private Field field;

    public Grid(int x, int y,Field field) {
        this.x = x;
        this.y = y;
        this.field = field;
    }

    public Grid(int x, int y, T holder,Field field) {
        this(x,y,field);
        this.holder = holder;
        if (holder==null) {
            this.occupied = false;
        } else {
            this.occupied = true;
        }
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

    public T getHolder() {
        return holder;
    }

    public void setHolder(T holder) {
        if (holder!=null) {
            this.holder = holder;
            this.setOccupied();}
    }

    public void setNull() {
        this.holder = null;
        this.cleanOccupied();
    }

    public void setXY(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public void drawGrid(Graphics g) {
        g.drawRect(x*SCALE+OFFSET,y*SCALE+OFFSET,SCALE,SCALE);
        if (holder != null) {
            g.drawImage(holder.getImage(),x*SCALE+OFFSET,y*SCALE+OFFSET, field);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
