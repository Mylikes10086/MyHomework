package creatures;

import scenes.Field;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestPlayer extends Creature /*implements Runnable*/{
    private Field field;

    public TestPlayer(int x, int y, Field field) {
        this.x = x;
        this.y = y;
        this.field = field;

        /*URL loc = this.getClass().getClassLoader().getResource("player.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);*/
        this.setImage("player.png");
    }

    public void move(int x, int y) {
        double nx = this.x() + x;
        double ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    public void run() {

            while (!Thread.interrupted()) {//每次线程刷新都会运动
                try {
                    Random rand = new Random();
                    this.move(rand.nextInt(20), rand.nextInt(20));
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000) +500);
                    //Thread.sleep(rand.nextInt(1000) + 1000);
                    this.field.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }
}
