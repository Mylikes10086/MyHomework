package creatures;

import scenes.BattleField;

import java.util.Random;

public class Huluwa extends Creature{
    private SENIORITY seniority;
    private COLOUR colour;
    //private BattleField field;
    //private int speed = 80;

    public Huluwa(int nseniority, BattleField field) {
        super(field);
        this.isWho(nseniority);
        this.inti_HP = 5;
        this.current_HP = 5;
        this.damage = 5;
        setImage("huluboys/"+nseniority+"_a.png","huluboys/"+nseniority+"_d.png");

    }

    public Huluwa() {super();}

    @Override
    public void attack(Creature c) {
        Random random = new Random();
         c.wounded(damage + random.nextInt(5));
    }

    public void setXY(double x,double y) {//设置坐标
        this.x = x;
        this.y = y;
    }

    public void run() {//线程运行
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
                Creature enemy = this.getClosestEnemy();
                if (enemy == this) return;

                if(this.grid.isNearBy( enemy.getGrid() ) ) {
                    synchronized (this) {
                        if (enemy.isDead()) continue;//该位置敌人已死
                        synchronized (enemy) {
                            this.field.battleStart(this, enemy);
                            if(enemy.isDead()){
                                System.out.println(this.name+"killed"+enemy.getName());
                            }
                            if (this.isDead()) {
                                this.grid.setNull();
                                System.out.println(this.name+" is killed by");
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


    public void isWho(int nseniority) {
        switch (nseniority) {
            case 1:
                this.seniority = SENIORITY.ONE;
                this.colour = COLOUR.RED;
                this.name = "大娃";
                break;
            case 2:
                this.seniority = SENIORITY.TWO;
                this.colour = COLOUR.ORANGE;
                this.name = "二娃";
                break;
            case 3:
                this.seniority = SENIORITY.THREE;
                this.colour = COLOUR.YELLOW;
                this.name = "三娃";
                break;
            case 4:
                this.seniority = SENIORITY.FOUR;
                this.colour = COLOUR.GREEN;
                this.name = "四娃";
                break;
            case 5:
                this.seniority = SENIORITY.FIVE;
                this.colour = COLOUR.BLUE;
                this.name = "五娃";
                break;
            case 6:
                this.seniority = SENIORITY.SIX;
                this.colour = COLOUR.INDIGO;
                this.name = "六娃";
                break;
            case 7:
                this.seniority = SENIORITY.SEVEN;
                this.colour = COLOUR.VIOLET;
                this.name = "七娃";
                break;
            default :
                break;
        }
    }

    public static CreatureFactory factory() {
        return new CreatureFactory() {
            public Creature getCreature() {
                return new Huluwa();
            }
        };
    }

    public enum SENIORITY {
        ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7);

        private int sNum=0;

        SENIORITY(int _sNum) {
            this.sNum=_sNum;
        }

        //public int toNumber() {return this.sNum;}

    }

    public enum COLOUR {
        RED(1),ORANGE(2),YELLOW(3),GREEN(4),BLUE(5),INDIGO(6),VIOLET(7);

        private int cNum=0;

        COLOUR(int _cNum) {
            this.cNum=_cNum;
        }

        //public int toNumber() {return this.cNum;}

    }


}


