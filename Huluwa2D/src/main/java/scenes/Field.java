package scenes;

import creatures.*;
import utility.Formation;
import utility.GameUtil;
import utility.Thing2D;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;

public class Field extends JPanel {

    private boolean refreshing = false;

    //private ArrayList tiles = new ArrayList();
    private ArrayList boys = new ArrayList(7);
    private Huluwa huluwa;
    private Huluwa huluwa2 = (Huluwa) Huluwa.factory().getCreature();

    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    /*private String level =
            "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..@.......\n" +
                    "..........\n" +
                    "..........\n";*/

    public Field() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public boolean isRefreshing() {
        return refreshing;
    }

    public synchronized void doRefresh() throws InterruptedException{
        while (!refreshing)
            refreshing = true;
            notifyAll();
    }

    public synchronized void hasRefreshed() throws InterruptedException {
        //while (refreshing == true)
            refreshing = false;
            wait();
            notifyAll();
        //refreshing = false;
    }


    public final void initWorld() {

        int OFFSET = 30;
        int x = OFFSET;
        int y = OFFSET;

        try {
            for (int i=1;i<=7;i++) {
                Huluwa nboy = new Huluwa(i,this);
                nboy.setImage("huluboys/"+i+".png");
                System.out.println("woshi"+i);
                boys.add(nboy);
            }
            huluwa = new Huluwa(1,this);
            huluwa.setImage("huluboys/1.png");
            huluwa2.isWho(2);
            huluwa2.setImage("huluboys/2.png");
            boys.add(huluwa2);
            boys.add(huluwa);

            Formation formation = new Formation(this);
            formation.shexingzhen(boys,0,0);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void buildWorld(Graphics g) {

        g.drawImage(GameUtil.getImage("bg.jpg"),0,0,this);
        ArrayList world = new ArrayList();
        world.addAll(boys);


        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            //System.out.println(item.getClass().toString());
            /*if (item instanceof TestPlayer) {
                g.drawImage(item.getImage(), (int)item.x() + 2, (int)item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), (int)item.x(), (int)item.y(), this);
            }*/

            g.drawImage(item.getImage(), (int)item.x(), (int)item.y(), this);

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
                System.out.println("Completed");
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed) {
                return;
            }

            int key = e.getKeyCode();

            int SPACE = 20;
            if (key == KeyEvent.VK_LEFT) {
                huluwa.move(-SPACE, 0);

            } else if (key == KeyEvent.VK_RIGHT) {
                huluwa.move(SPACE, 0);

            } else if (key == KeyEvent.VK_UP) {
                huluwa.move(0, -SPACE);

            } else if (key == KeyEvent.VK_DOWN) {
                huluwa.move(0, SPACE);

            } else if (key == KeyEvent.VK_S) {
                ExecutorService exec = Executors.newCachedThreadPool();
                exec.execute(huluwa);
                exec.execute(huluwa2);
                exec.execute((Huluwa)boys.get(1));

            } else if (key == KeyEvent.VK_R) {
                restartLevel();
            }

            repaint();
        }
    }


    public void restartLevel() {

        //tiles.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }
}
