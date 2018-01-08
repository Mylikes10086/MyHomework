package creatures;

import scenes.Grid;

public class Grandpa extends Creature {

    public void attack(Creature c) {
        c.wounded(damage);
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {//每次线程刷新都会运动
                //move(-1,0);
                if (this.isDead()) {
                    this.grid.setNull();
                    return;
                }
                //战斗已经结束
                if(this.field.battleFinished()) return;
                //寻找敌人
                Creature creature = this.getClosestEnemy();
                if (creature == this) return;

                if(this.grid.isNearBy( creature.getGrid() ) ) {
                    synchronized (this) {
                        if (creature.isDead()) continue;//该位置敌人已死
                        synchronized (creature) {
                            this.field.battleStart(this, creature);
                            if(creature.isDead()){
                                System.out.println("yeye killed someone");
                            }
                            if (this.isDead()) {
                                this.grid.setNull();
                                System.out.println("yeye is killed"+ creature.current_HP);
                                return;
                            } else {
                                creature = this.getClosestEnemy();
                            }
                        }
                    }
                }
                this.moveTo(creature.getGrid());
                //System.out.println(this.getClass().toString()+"Moving");
                Thread.sleep(1000);
                this.field.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Grid findAliveBoys() {
        for(Creature creature:field.getCreatures()){
            if(creature instanceof Huluwa && !creature.isDead()){
                return creature.getGrid();
            }
        }
        return field.getGrids()[0][0];
    }

    public Grandpa() {
        super();
        inti_HP = 5;
        current_HP = 5;
        damage = 1;
        setImage("huluboys/grandpa_a.png","huluboys/grandpa_d.png");
    }
}
