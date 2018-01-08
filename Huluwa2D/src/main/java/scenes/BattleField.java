package scenes;

import creatures.*;
import utility.Formation;
import utility.GameUtil;
import utility.TooCrowdedException;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;


public class BattleField extends JPanel {//战场类，提供给葫芦娃大战蛇精的场地，实现战场上的各种操作

    //在界面上放置15*10的格子，判断当前位置是否有物体
    private Grid[][] grids;
    private ArrayList<Creature> creatures;
    private ArrayList<Formation> formations;
    //private Monster louluo = new Monster();

    private int w;
    private int h;
    private boolean completed = false;

    public BattleField(int width,int height) {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initField(width,height);
    }

    public final void initField(int width,int height) {
        this.w = width;
        this.h = height;

        formations = new ArrayList<Formation>();
        creatures = new ArrayList<Creature>();
        grids = new Grid[width][height];
        for (int i=0;i<width;i++) {
            for(int j=0;j<height;j++) {
                grids[i][j] = new Grid(i,j);
                grids[i][j].setField(this);
            }
        }

    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public synchronized void battleStart(Creature C1, Creature C2){//双方交战
        C1.attack(C2);
        C2.attack(C1);
    }

    public synchronized boolean battleFinished(){
        int B_count = 0;
        int M_count = 0;
        for(Creature creature : creatures){
            if((creature instanceof Huluwa || creature instanceof Grandpa) && !creature.isDead()) {
                B_count++;
            }
            else if(creature instanceof Monster && !creature.isDead()) {
                M_count++;
            }
        }
        System.out.println("B_count"+B_count);
        System.out.println("M_count"+M_count);
        if(B_count == 0 || M_count == 0) return true;
        return false;
    }


    public Grid[][] getGrids() {
        return grids;
    }

    public void cleanField() {
        formations.clear();
        creatures.clear();
        for(int i=0; i<this.w; i++){
            for(int j=0; j<this.h; j++){
                this.grids[i][j].setNull();
            }
        }
    }

    public void drawField(Graphics g) {

        g.drawImage(GameUtil.getImage("bg.jpg"),0, 0,this.getWidth(),this.getHeight(),this);
        for (int i=0;i<w;i++) {
            for(int j=0;j<h;j++) {
                grids[i][j].drawGrid(g);
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawField(g);
    }


    public void reload() {
        //initField(15,10);
        for (Creature c:creatures) {
            c.reborn();
        }
        if (completed) {
            completed = false;
        }
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Formation> getFormations() {
        return formations;
    }

    public boolean layoutCreature(Creature creature,Grid grid) throws TooCrowdedException{
        if(this.alreadyLaid(creature)) {
            throw new TooCrowdedException("Already laid");
        }
        if(grid.getX() >= this.w || grid.getY() >= this.h){
            throw new TooCrowdedException("Out of Bounds");
        }

        if(conflicts(grid)){
            throw  new TooCrowdedException("Positions Conflict");
        }
        creature.getGrid().setNull();
        creature.setGrid(grids[grid.getX()][grid.getY()]);
        creature.setField(this);
        this.creatures.add(creature);
        return true;
    }

    public boolean layoutFormation(Formation formation,Grid grid) throws TooCrowdedException {
        if(this.alreadyLaid(formation)){

            throw new TooCrowdedException("Creatures or Formation Already Laid");
        }
        if(grid.getY() + formation.getHeight() > this.h ||
                grid.getX() + formation.getWidth() > this.w) {
            throw new TooCrowdedException("Out of bounds");
        }

        Grid grid1 = formation.getStart();
        formation.setStart(grid);

        if(conflicts(formation)){
            formation.setStart(grid1);
            throw new TooCrowdedException("Positions Conflict");
        }

        for(Creature creature : formation.getCreatures()){
            int nx= creature.getGrid().getX();
            int ny = creature.getGrid().getY();
            this.layoutCreature(creature, new Grid(nx+grid.getX(),ny+grid.getY()) );
        }
        this.formations.add(formation);
        return true;
    }



    public boolean conflicts(Grid grid){
        if(grids[grid.getX()][grid.getY()].isOccupied())
            return true;
        return false;
    }

    public boolean conflicts(Formation formation) {
        for (int i = 0; i < formation.getWidth(); i++) {
            for (int j = 0; j < formation.getHeight(); j++) {
                if (conflicts(grids[formation.getStart().getX() + i][formation.getStart().getY() + j])) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean alreadyLaid(Creature creature){
        if(this.creatures.contains(creature))
            return true;
        return false;
    }

    public boolean alreadyLaid(Formation formation){
        if(this.formations.contains(formation))
            return true;
        for(Creature creature : formation.getCreatures()){
            for(Formation f : this.formations) {
                if(f.getCreatures().contains(creature))
                    return true;
            }
        }
        return false;
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed) {
                return;
            }

            int key = e.getKeyCode();
            if (key == KeyEvent.VK_SPACE) {
                //InitUI();
                ExecutorService exec = Executors.newCachedThreadPool();
                for (Creature creature : creatures) {
                    exec.execute(creature);
                }
                /*java.util.Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        repaint();
                    }

                }, 0, 500);*/




            } else if (key == KeyEvent.VK_R) {
                reload();
            } else if(key == KeyEvent.VK_P){

            }

            repaint();
        }
    }


}
