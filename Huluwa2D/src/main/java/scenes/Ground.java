package scenes;

import javax.swing.*;

public final class Ground extends JFrame{
    private final int OFFSET = 30;


    public Ground() {
        InitUI();
    }

    public void InitUI() {
        Field field = new Field();
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,800);
        setLocationRelativeTo(null);
        setTitle("Ground");
    }


    public static void main(String[] args) {
        Ground ground = new Ground();
        ground.setVisible(true);
    }
}
