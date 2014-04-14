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
public class PrototypeBall extends Rectangle implements Drawable, Actable {

    private double
            x = (Main.WIDTH / 2) - (SIZE / 2),
            y = (Main.HEIGHT - (SIZE * 2)),

            // velocity x and y
            vx = 0,
            vy = 0;

    private int
            setpointX = (int) x,
            setpointY = (int) y;

    public static int SIZE = 50;

    public PrototypeBall() {
        super(SIZE, SIZE);

        setLocation((int) x, (int) y);
    }

    @Override
    public void draw(Graphics g) {
        g.fillOval((int) x, (int) y, SIZE, SIZE);
    }

    @Override
    public void act() {
//        setLocation((int) x - (SIZE / 2) + 1, (int) y - (SIZE / 2) + 1);

        // vectors to target
        vx = setpointX - x;
        vy = setpointY - y;

        // move ball to target
        x += vx * 0.1;
        y += vy * 0.1;
    }

    public void setTarget(int setpointX, int setpointY) {
        this.setpointX = setpointX;
        this.setpointY = setpointY;
        System.out.println("New setpoints are " + setpointX + ", " + setpointY);
    }

}
