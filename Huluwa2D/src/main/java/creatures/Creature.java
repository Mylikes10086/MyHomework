package creatures;

import utility.GameUtil;
import utility.Thing2D;

import java.awt.Image;

public abstract class Creature extends Thing2D implements Moveable,Runnable{
    public static final int SPACE=50;
    protected double x,y;
    protected Image image;

    public Creature(double x,double y) {
        super(x, y);
    }

    public Creature(String imgPath,double x,double y) {
        super(x, y);
        this.image = GameUtil.getImage(imgPath);
    }

    public Creature() {}

    public double x() { return x;}

    public void setX(double x) { this.x = x; }

    public double y() { return y; }

    public void setY(double y) { this.y = y; }

    public Image getImage() { return image; }

    public void setImage(Image image) { this.image = image; }

    public void setImage(String imgPath) {
        this.image = GameUtil.getImage(imgPath);
    }


}
