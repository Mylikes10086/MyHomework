package creatures;

import utility.GameUtil;
import utility.Thing2D;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Tile extends Thing2D{

    public Tile(double x, double y) {
        this.x = x;
        this.y = y;

            /*URL loc = this.getClass().getClassLoader().getResource("tile.png");
            ImageIcon iia = new ImageIcon(loc);
            Image image_alive = iia.getImage();
            this.setImage(image_alive);*/
        this.setImage("tile.png");
    }


    public void setImage(String imgPath) {
        this.image = GameUtil.getImage(imgPath);
    }

}
