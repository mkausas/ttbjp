package prototypingclasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import toss.the.ball.java.prototype.Actable;
import toss.the.ball.java.prototype.Drawable;
import toss.the.ball.java.prototype.Main;

/**
 *
 * @author Marty
 */
public class HumanPlayer extends Rectangle implements Actable, Drawable {

    public static int SIZE = PrototypeBall.SIZE;
    private double
            x = (Main.WIDTH / 2) - (SIZE / 2),
            y = (Main.HEIGHT - (SIZE * 2));


    public HumanPlayer() {
        super(SIZE, SIZE);

        setLocation((int) x, (int) y);
        System.out.println("set player location to " + x + ", " + y);
    }

    @Override
    public void act() {
        setLocation((int) x, (int) y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawRect((int) x, (int) y, SIZE, SIZE);
    }

}
