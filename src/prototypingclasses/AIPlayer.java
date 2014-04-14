package prototypingclasses;

import java.awt.Graphics;
import java.awt.Rectangle;
import toss.the.ball.java.prototype.Actable;
import toss.the.ball.java.prototype.Drawable;
import toss.the.ball.java.prototype.Main;

/**
 *
 * @author Marty
 */
public class AIPlayer extends Rectangle implements Actable, Drawable {

    public static final int LEFT = 1, RIGHT = 2;
    private int SIZE = PrototypeBall.SIZE;

    private double
            x = (Main.WIDTH / 4) - (SIZE / 2),
            y = SIZE * 2;

    public AIPlayer(int player) {
        super();
        setLocation((int) x, (int) y);

        // set the player to the correct side
        if (player == RIGHT)
            x = ((Main.WIDTH / 4) * 3) - (SIZE / 2);
    }

    @Override
    public void act() {
        setLocation((int) x, (int) y);
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect((int) x, (int) y, SIZE, SIZE);
    }

}
