package prototypingclasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import toss.the.ball.java.prototype.Actable;
import toss.the.ball.java.prototype.Drawable;
import toss.the.ball.java.prototype.Main;

/**
 *
 * @author Marty
 */
public class HumanPlayer extends Rectangle implements Actable, Drawable {

    public static int SIZE = Ball.SIZE;
    private double
            x = (Main.WIDTH / 2) - (SIZE / 2),
            y = (Main.HEIGHT - (SIZE * 2));

    private boolean hasBall = true;

    private Random randomGenerator;

    public HumanPlayer() {
//        super(SIZE, SIZE);
        setBounds((int) x, (int) y, SIZE, SIZE);

        setLocation((int) x, (int) y);

        randomGenerator = new Random();
    }

    @Override
    public void act() {
        setLocation((int) x, (int) y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(hasBall ? Color.green : Color.red);
        g.drawRect((int) x, (int) y, SIZE, SIZE);
    }

    public int setHasBall(boolean hasBall) {
        this.hasBall = hasBall;

        if (randomGenerator.nextDouble() >= 0.5) {
            return AIPlayer.LEFT;
        } else {
            return AIPlayer.RIGHT;
        }
    }
}
