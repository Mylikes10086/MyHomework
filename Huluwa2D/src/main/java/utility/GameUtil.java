package utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * 游戏开发中会用到的工具类
 */
public class GameUtil {
    private GameUtil() {} //工具类通常将构造方法私有

    public static Image getImage(String path) {

        BufferedImage bi = null;

        try {
            URL u = GameUtil.class.getClassLoader().getResource(path);
            //System.out.println(u);
            bi = javax.imageio.ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bi;
    }

    public static final int SCALE = 80;
    public static final int OFFEST = 40;

}
