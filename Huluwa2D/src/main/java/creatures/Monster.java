package creatures;

public class Monster extends Creature{

    public Monster() {
        super();
        this.name = "蛤蟆精";
        this.inti_HP = 12;
        this.current_HP = 12;
        this.damage = 2;
        setImage("huluboys/louluo_a.png","huluboys/louluo_d.png");
    }

    public Monster(String name,int hp,int da) {
        this.name = name;
        this.inti_HP = hp;
        this.current_HP = hp;
        this.damage = da;
        }


    public void attack(Creature c) {
        c.wounded(this.damage);
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {//每次线程刷新都会运动
                if (this.isDead()) {//
                    this.grid.setNull();
                    return;
                }
                //战斗已经结束
                if(this.field.battleFinished()) return;
                //寻找敌人
                Creature enemy = this.getClosestEnemy();
                if (enemy == this) return;

                if(this.grid.isNearBy( enemy.getGrid() ) ) {
                    synchronized (this) {
                        if (enemy.isDead()) continue;//该位置敌人已死
                        synchronized (enemy) {
                            this.field.battleStart(this, enemy);
                            if(enemy.isDead()){
                                System.out.println(this.name+" killed "+enemy.getName());
                            }
                            if (this.isDead()) {
                                this.grid.setNull();
                                System.out.println(this.name+"is killed by"+enemy.getName());
                                return;
                            } else {
                                enemy = this.getClosestEnemy();
                            }
                        }
                    }
                }
                this.moveTo(enemy.getGrid());
                System.out.println(this.getClass().getSimpleName()+"Moving");
                Thread.sleep(1000);
                this.field.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
