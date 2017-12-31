package creatures;

import scenes.Field;
import scenes.Grid;
import utility.GameUtil;

import java.util.Random;

public class Huluwa extends Creature{
    private SENIORITY seniority;
    private COLOUR colour;
    private Field field;
    private Grid grid;
    private int speed = 80;

    public Huluwa(int nseniority,Field field) {
        this.isWho(nseniority);
        this.field = field;
        this.grid = null;
    }

    /*public Huluwa(SENIORITY seniority, COLOUR colour,Field field) {
        this.seniority = seniority;
        this.colour = colour;
        this.field = field;
        this.grid = null;
    }*/

    public Huluwa(double x,double y,int nseniority,Field field) {
        this(nseniority, field);
        this.setXY(x, y);
    }

    public Huluwa() {}

    public void setXY(double x,double y) {//设置坐标
        this.x = x;
        this.y = y;
    }

    public void run() {//线程运行
            try {
                while (!Thread.interrupted()) {//每次线程刷新都会运动
                    Random rand = new Random();
                    this.move(1,0);
                    System.out.println("Huluwa"+this.seniority.name()+"Moving");
                    Thread.sleep(rand.nextInt(1000) + 1000);
                    this.field.repaint();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public void move(int xstep, int ystep) {//葫芦娃移动
        Grid tgrid = this.grid;
        this.setGrid(new Grid(xstep,ystep,this.field));
        tgrid.setNull();
    }

    public void isWho(int nseniority) {
        switch (nseniority) {
            case 1:
                this.seniority = SENIORITY.ONE;
                this.colour = COLOUR.RED;
                break;
            case 2:
                this.seniority = SENIORITY.TWO;
                this.colour = COLOUR.ORANGE;
                break;
            case 3:
                this.seniority = SENIORITY.THREE;
                this.colour = COLOUR.YELLOW;
                break;
            case 4:
                this.seniority = SENIORITY.FOUR;
                this.colour = COLOUR.GREEN;
                break;
            case 5:
                this.seniority = SENIORITY.FIVE;
                this.colour = COLOUR.BLUE;
                break;
            case 6:
                this.seniority = SENIORITY.SIX;
                this.colour = COLOUR.INDIGO;
                break;
            case 7:
                this.seniority = SENIORITY.SEVEN;
                this.colour = COLOUR.VIOLET;
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


