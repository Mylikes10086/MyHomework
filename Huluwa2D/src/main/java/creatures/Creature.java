package creatures;

import scenes.BattleField;
import scenes.Grid;
import utility.GameUtil;
import utility.Thing2D;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Creature extends Thing2D implements Moveable,Runnable,Serializable {

    protected String name;
    protected Grid grid;
    protected BattleField field;
    protected transient Image image_alive;
    protected transient Image image_dead;

    protected int current_HP;
    protected int inti_HP;
    protected int damage;

    public Creature(BattleField field) {
        this.field = field;
        this.grid = new Grid(0,0);
        x = grid.getX()*50;
        y = grid.getY()*50;
    }

    public Creature() {
        grid = new Grid(0,0);
    }

    public double x() { return x;}

    public void setX(double x) { this.x = x; }

    public double y() { return y; }

    public void setY(double y) { this.y = y; }

    public boolean isDead() {
        if (current_HP>0)
            return false;
        return true;
    }

    public synchronized void wounded(int damage){
        if(damage >= this.current_HP){
            current_HP = 0;
        }
        else {
            current_HP -=damage;
        }
    }

    public abstract void attack(Creature creature);

    public Image getImage() {
        if (this.isDead()){return image_dead;}
        else{
            return this.image_alive;
        }
    }

    public void setImage(Image image) { this.image_alive = image; }

    public void setImage(String imgPath1,String imgPath2) {
        this.image_alive = GameUtil.getImage(imgPath1);
        this.image_dead = GameUtil.getImage(imgPath2);
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
        this.x = grid.getX()*50;
        this.y = grid.getY()*50;
        grid.setHolder(this);
    }

    public void setField(BattleField field) {
        this.field = field;
    }

    public void move(int x, int y) {
        int nx = this.grid.getX()+x;
        int ny = this.grid.getY()+y;
        if(nx<0||ny<0) {
            System.out.println("failed1");
            return;}
        if(nx>field.getBoardWidth()||ny>field.getBoardHeight()) {
            System.out.println("failed2");
            return;}
        synchronized (grid) {
            if (field.getGrids()[nx][ny].isOccupied()) {
                System.out.println("failed3");
                return;}
            synchronized (field.getGrids()[nx][ny]) {
                grid.setNull();
                this.setGrid(field.getGrids()[nx][ny]);
            }
        }
    }

    public void moveTo(Grid grid1){//向某个格子前进
        if (grid1.getX() > this.grid.getX()) {
            this.move(1, 0);
        } else if (grid1.getX() < this.grid.getX()) {
            this.move(-1, 0);
        } else {
            if (grid1.getY() > this.grid.getY()) {
                this.move(0, 1);
            } else if (grid1.getY() < this.grid.getY()) {
                this.move(0, -1);
            }
        }
    }

    public Creature getClosestEnemy() {//寻找附近最近的敌人
        if(this instanceof Huluwa || this instanceof Grandpa){
            Monster m = new Monster();
            m.setGrid(new Grid(100,100));
            int mx = m.getGrid().getX();
            int my=  m.getGrid().getY();
            ArrayList<Creature> others = field.getCreatures();
            for(Creature c : others){
                if(c instanceof Monster && !c.isDead()){
                    int cx = c.getGrid().getX();
                    int cy = c.getGrid().getY();
                    int nx = this.getGrid().getX();
                    int ny = this.getGrid().getY();
                    if( Math.abs(cx-nx)+Math.abs(cy - ny) < Math.abs(m.getGrid().getX()-nx)+ Math.abs(m.getGrid().getY()-ny)){

                        m = (Monster) c;
                    }
                }
            }
            if(m.getGrid().getX() == 100) return this;//没有活着的敌人时，返回自身
            return m;
        }
        else if (this instanceof Monster){
            Creature h = new Huluwa(7,field);
            h.setGrid(new Grid(100,100));
            int hx = h.getGrid().getX();
            int hy=  h.getGrid().getY();
            ArrayList<Creature> creatures = field.getCreatures();
            for(Creature c : creatures){
                int cx = c.getGrid().getX();
                int cy = c.getGrid().getY();
                int nx = this.getGrid().getX();
                int ny = this.getGrid().getY();
                if((c instanceof Huluwa|| c instanceof Grandpa) && !c.isDead()){
                    if(Math.abs(cx-nx)+Math.abs(cy - ny) <= Math.abs(hx-nx)+ Math.abs(hy-ny) ){
                        h = c;
                    }
                }
            }
            if(h.getGrid().getX() == 100) return this;
            return h;
        }

        else return this;
    }

    public String getName() {
        return name;
    }

    public Image getImage_alive() {
        return image_alive;
    }

    public void setImage_alive(Image image_alive) {
        this.image_alive = image_alive;
    }

    public Image getImage_dead() {
        return image_dead;
    }

    public void setImage_dead(Image image_dead) {
        this.image_dead = image_dead;
    }

    public int getCurrent_HP() {
        return current_HP;
    }

    public void setCurrent_HP(int current_HP) {
        this.current_HP = current_HP;
    }

    public int getInti_HP() {
        return inti_HP;
    }

    public void setInti_HP(int inti_HP) {
        this.inti_HP = inti_HP;
    }

    public void reborn() {
        current_HP = inti_HP;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}