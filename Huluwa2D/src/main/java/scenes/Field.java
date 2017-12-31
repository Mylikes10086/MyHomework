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

import javax.swing.*;

public class Field extends JPanel {

    private int gridW = 14;
    private int gridH = 9;

    //在界面上放置15*10的格子，判断当前位置是否有物体
    private Grid[][] grids = new Grid[gridW][gridH];
    //private int[][] cells = new int[14][9];

    //private boolean refreshing = false;

    private ArrayList boys = new ArrayList(7);
    //private Huluwa huluwa;
    //private Huluwa huluwa2 = (Huluwa) Huluwa.factory().getCreature();

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


    /*public synchronized void doRefresh() throws InterruptedException{
        while (!refreshing)
            refreshing = true;
            notifyAll();
    }*/

    /*public synchronized void hasRefreshed() throws InterruptedException {
        //while (refreshing == true)
            refreshing = false;
            wait();
            notifyAll();
        //refreshing = false;
    }*/

    public Grid[][] getGrids() {
        return grids;
    }

    public Grid getGrid(int x,int y) {
        return grids[x][y];
    }


    public final void initWorld() {

        int offset = GameUtil.OFFEST;
        int scale = GameUtil.SCALE;

        for (int i=0;i<gridW;i++) {
            for (int j=0;j<gridH;j++) {
                grids[i][j] = new Grid(i,j,this);
            }
        }

        try {
            for (int i=1;i<=7;i++) {
                Huluwa nboy = new Huluwa(i,this);
                nboy.setImage("huluboys/"+i+".png");
                System.out.println("woshi"+i);
                boys.add(nboy);
            }

            Formation formation = new Formation(this);
            formation.shexingzhen(boys,1,1);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void buildWorld(Graphics g) {
        int SCALE = 80;


        /*for (int i=0;i<14;i++) {
            for (int j=0;j<9;j++) {
                g.drawRect(i*SCALE+OFFSET,j*SCALE+OFFSET,SCALE,SCALE);
            }
        }*/

        for (int i=0;i<gridW;i++) {
            for (int j=0;j<gridH;j++){
             grids[i][j].drawGrid(g);
            }
        }

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

            if(i==world.size()-1)
                completed = true;
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = GameUtil.getImage("bg.jpg");
        g.drawImage(img,0,0,getSize().width,getSize().height,this);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed) {
                return;
            }

            int key = e.getKeyCode();

            int SPACE = 20;
            /*if (key == KeyEvent.VK_LEFT) {
                huluwa.move(-SPACE, 0);

            } else if (key == KeyEvent.VK_RIGHT) {
                huluwa.move(SPACE, 0);

            } else if (key == KeyEvent.VK_UP) {
                huluwa.move(0, -SPACE);

            } else if (key == KeyEvent.VK_DOWN) {
                huluwa.move(0, SPACE);

            } else */
            if (key == KeyEvent.VK_S) {
                ExecutorService exec = Executors.newCachedThreadPool();
                for (int i = 0; i < boys.size(); i++) {
                    exec.execute((Huluwa)boys.get(i));
                }
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
