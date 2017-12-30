package utility;

import java.awt.Image;

public abstract class Thing2D {

    private final int SPACE = 20;

    protected double x;
    protected double y;
    protected Image image;

    public Thing2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Thing2D(){}

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


} 