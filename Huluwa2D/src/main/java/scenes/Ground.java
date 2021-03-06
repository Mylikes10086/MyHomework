package scenes;

import creatures.*;
import utility.CraneFormation;
import utility.SnakeFormation;
import utility.TooCrowdedException;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Ground extends JFrame{

    private Huluwa[] boys;
    private Monster[] mt;
    private CraneFormation cf;
    private SnakeFormation sf;
    private Snake shej;
    private Xiezi xiezi;
    private Grandpa yeye;
    private BattleField field = new BattleField(15,10);

    public Ground() {
        loadBattle();

        InitUI();
    }

    public void InitUI() {
        field.setOpaque(true);
        field.addKeyListener(new MyAdapter());
        add(field);


        //addKeyListener(new MyAdapter());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,800);
        setLocationRelativeTo(null);
        setTitle("Ground");
    }

    void loadBattle() {
        field.cleanField();
        this.shej = new Snake();
        this.xiezi = new Xiezi();
        this.yeye = new Grandpa();
        this.boys = new Huluwa[7];
        for (int i = 0; i < boys.length; i++) {
            boys[i] = new Huluwa(i+1, field);
        }
        this.mt = new Monster[7];
        mt[0] = xiezi;
        for (int i=1;i< mt.length;i++) {
            mt[i] = new Monster();
            mt[i].setField(this.field);
        }

        sf = new SnakeFormation(boys);
        cf = new CraneFormation(mt);
        cf.reset();
        sf.reset();
        shej.reborn();
        xiezi.reborn();
        yeye.reborn();

        try {
            field.layoutFormation(sf,new Grid(4,2));
            field.layoutFormation(cf,new Grid(8,2));

            field.layoutCreature(yeye,new Grid(1,5));
            field.layoutCreature(shej,new Grid(13,5));
            //System.out.println(field.getCreatures().toString());
        } catch (TooCrowdedException e) {
            e.printStackTrace();
        }
        //formation.shexingzhen(boys,3,1);
        //formation.shexingzhen(mt,11,1);
    }
    public void reload() {
        //InitUI();
        loadBattle();
        
    }

    class MyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_SPACE) {
                //InitUI();
                ExecutorService exec = Executors.newCachedThreadPool();
                for (Creature creature : field.getCreatures()) {
                    exec.execute(creature);
                }

            } else if (key == KeyEvent.VK_R) {
                reload();
                repaint();
            }
        }
    }





}
