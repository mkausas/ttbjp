package toss.the.ball.java.prototype;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Marty
 */
public class BossManager implements Runnable {

    private ArrayList<Actable> actables;
    private Rectangle
            player,
            ai1,
            ai2,
            ball;

    @Override
    public void run() {
        try {
            actables = Main.actables;

            for (int i = 0; i < actables.size(); i++) {
                ((Actable) actables.get(i)).act();
            }

            interact();

            Thread.sleep(10);
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void interact() {
        if (ball.intersects(player))
            System.out.println("intersection!");
    }

    public void setInteractionObjects(
            Rectangle player, Rectangle ai1, Rectangle ai2, Rectangle ball) {
        this.player = player;
        this.ai1    = ai1;
        this.ai2    = ai2;
        this.ball   = ball;
    }

}
