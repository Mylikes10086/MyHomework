package creatures;

public class Monster extends Creature{
    protected Name name;

    public Monster() {
        super();
        this.name = Name.louluo;
        this.inti_HP = 10;
        this.current_HP = 10;
        this.damage = 2;
        setImage("huluboys/louluo_a.png","huluboys/louluo_d.png");
    }

    public Monster(Name name,int hp,int da) {
        this.name = name;
        this.inti_HP = hp;
        this.current_HP = hp;
        this.damage = da;
        setImage("huluboys/"+name+"_a.png","huluboys/"+name+"_d.png");
    }


    public int attack() {
        return damage;
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
                Creature creature = this.getCloestEnemy();
                if (creature == this) return;

                if(this.grid.isNearBy( creature.getGrid() ) ) {
                    synchronized (this) {
                        if (creature.isDead()) continue;//该位置敌人已死
                        synchronized (creature) {
                            this.field.battleStart(this, creature);
                            if(creature.isDead()){
                                System.out.println(this.name.toString()+"killed");
                            }
                            if (this.isDead()) {
                                this.grid.setNull();
                                System.out.println(this.name.toString()+"is killed"+ creature.current_HP);
                                return;
                            } else {
                                creature = this.getCloestEnemy();
                            }
                        }
                    }
                }
                this.moveTo(creature.getGrid());
                System.out.println(this.getClass().getSimpleName()+"Moving");
                Thread.sleep(1000);
                this.field.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

enum Name {
        snake,xiezi,louluo;
        }
