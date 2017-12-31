package creatures;

import scenes.Grid;
import utility.GameUtil;
import utility.Thing2D;

import java.awt.*;

public abstract class Creature extends Thing2D implements Moveable,Runnable{
    public static final int SPACE=80;
    protected double x,y;//绘制图片的坐标
    protected Image image;
    protected int width,height;
    protected Grid grid;

    public Creature(double x,double y) {
        super(x, y);
    }

    public Creature(String imgPath,double x,double y) {
        super(x, y);
        this.image = GameUtil.getImage(imgPath);
        this.height = image.getHeight(null);
        this.width = image.getWidth(null);
    }

    public Creature() {}

    public void setGrid(Grid grid) {
        this.grid = grid;
        grid.setHolder(this);
        this.x = grid.getX();
        this.y = grid.getY();
    }

    public Grid getGrid() {
        return grid;
    }

    public double x() { return x;}

    public void setX(double x) { this.x = x; }

    public double y() { return y; }

    public void setY(double y) { this.y = y; }

    public Image getImage() { return image; }

    public void setImage(Image image) { this.image = image; }

    public void setImage(String imgPath) {
        this.image = GameUtil.getImage(imgPath);
    }

    public Rectangle getRect() {
        return new Rectangle((int) x, (int) y, width, height);
    }


}
