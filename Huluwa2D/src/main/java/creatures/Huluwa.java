package creatures;

import scenes.Field;

import java.awt.*;
import java.util.Random;

public class Huluwa extends Creature{
    private SENIORITY seniority;
    private COLOUR colour;
    private Field field;
    private int speed = 30;

    public Huluwa(int nseniority,Field field) {
        this.isWho(nseniority);
        this.field = field;
    }

    public Huluwa(SENIORITY seniority, COLOUR colour,Field field) {
        this.seniority = seniority;
        this.colour = colour;
        this.field = field;
    }

    public Huluwa(double x,double y,SENIORITY seniority, COLOUR colour,Field field) {
        this.setXY(x, y);
        this.seniority = seniority;
        this.colour = colour;
        this.field = field;
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
                    this.move(rand.nextInt(10), rand.nextInt(10));
                    System.out.println("HuluwaMoving");
                    Thread.sleep(rand.nextInt(1000) + 1000);
                    this.field.doRefresh();
                    this.field.repaint();
                    this.field.hasRefreshed();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public void move(double x, double y) {//葫芦娃移动
        double nx = this.x() + x;
        double ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    public void isWho(int nseniority) {
        switch (nseniority) {
            case 1:
                this.seniority = SENIORITY.大;
                this.colour = COLOUR.RED;
                break;
            case 2:
                this.seniority = SENIORITY.二;
                this.colour = COLOUR.ORANGE;
                break;
            case 3:
                this.seniority = SENIORITY.三;
                this.colour = COLOUR.YELLOW;
                break;
            case 4:
                this.seniority = SENIORITY.四;
                this.colour = COLOUR.GREEN;
                break;
            case 5:
                this.seniority = SENIORITY.五;
                this.colour = COLOUR.BLUE;
                break;
            case 6:
                this.seniority = SENIORITY.六;
                this.colour = COLOUR.INDIGO;
                break;
            case 7:
                this.seniority = SENIORITY.七;
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
        大(1),二(2),三(3),四(4),五(5),六(6),七(7);

        private int sNum=0;

        private SENIORITY(int _sNum) {
            this.sNum=_sNum;
        }

        public int toNumber() {
            return this.sNum;
        }

    }

    public enum COLOUR {
        RED(1),ORANGE(2),YELLOW(3),GREEN(4),BLUE(5),INDIGO(6),VIOLET(7);

        private int cNum=0;

        private COLOUR(int _cNum) {
            this.cNum=_cNum;
        }

        public int toNumber() {
            return this.cNum;
        }

    }


}


