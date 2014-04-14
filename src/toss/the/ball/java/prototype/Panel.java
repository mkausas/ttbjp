package toss.the.ball.java.prototype;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import prototypingclasses.AIPlayer;

/**
 *
 * @author Marty
 */
public class Panel extends JPanel {

    private ArrayList<Drawable> drawables;
    private int wallHeight = 0;
    private int wallSpeed = 3;
    private int sideTimer = 0;
    private boolean gameOver = false;

    public Panel() {

    }

    public void paint(Graphics g) {
        super.paint(g);
        if (!gameOver) {
            g.setColor(Color.gray);
            g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

            g.setColor(Color.blue);
            if (sideTimer != 0) {
                wallHeight += wallSpeed;
                if (sideTimer == AIPlayer.LEFT) {
                    g.fillRect(0, 0, Main.WIDTH / 2, wallHeight);
                } else if (sideTimer == AIPlayer.RIGHT) {
                    g.fillRect(Main.WIDTH / 2, 0, Main.WIDTH, wallHeight);
                }
            }

            g.setColor(Color.red);
            g.drawLine((int) Main.WIDTH / 2, 0, (int) Main.WIDTH / 2, (int) Main.HEIGHT);

            drawables = Main.drawables;
            for (int i = 0; i < drawables.size(); i++) {
                ((Drawable) drawables.get(i)).draw(g);
            }
        } else {
            g.setColor(Color.red);
            g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        }
    }

    public void setSideTimer(int side) {
        this.sideTimer = side;
        wallHeight = 0;
        if (side != 0)
            wallSpeed += 1;
    }

    public boolean timerHasRunOut() {
        return wallHeight >= Main.HEIGHT;
    }

    public void gameOver() {
        gameOver = true;
    }

}
