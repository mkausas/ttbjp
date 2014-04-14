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
public class AIPlayer extends Rectangle implements Actable, Drawable {

    public static final int LEFT = 1, RIGHT = 2, HUMAN = 3;
    private int SIZE = PrototypeBall.SIZE;

    private boolean hasBall = false;

    private double
            x = (Main.WIDTH / 4) - (SIZE / 2),
            y = SIZE * 2;

    private static Random randomSelector;
    private int THIS_PLAYER = 0;


    public AIPlayer(int player) {
        super();
        setBounds((int) x, (int) y, SIZE, SIZE);
        THIS_PLAYER = player;

        // set the player to the correct side
        if (player == RIGHT)
            x = ((Main.WIDTH / 4) * 3) - (SIZE / 2);

        randomSelector = new Random();
    }

    @Override
    public void act() {
        setLocation((int) x, (int) y);

        if (hasBall) {
            double randomDouble = randomSelector.nextDouble();

            if (THIS_PLAYER == LEFT) {
                if (randomDouble >= 0.5) {
                    // toss right

                } else {
                    // toss to human
                }

            } else if (THIS_PLAYER == RIGHT) {
                if (randomDouble >= 0.5) {
                    // toss left
                } else {
                    // toss to human
                }
            }
            hasBall = false;
        }

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(hasBall ? Color.green : Color.red);
        g.drawRect((int) x, (int) y, SIZE, SIZE);
    }

    public int setHasBall(boolean hasBall) {
        this.hasBall = hasBall;
        double randomDouble = randomSelector.nextDouble();

        if (THIS_PLAYER == LEFT) {
            if (randomDouble >= 0.5) {
                // toss right
                return RIGHT;
            } else {
                // toss to human
                return HUMAN;
            }

        } else if (THIS_PLAYER == RIGHT) {
            if (randomDouble >= 0.5) {
                // toss left
                return LEFT;
            } else {
                // toss to human
                return HUMAN;
            }
        }

        // if you get here something messed up...
        return -1;
    }
}
