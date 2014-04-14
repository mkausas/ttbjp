package toss.the.ball.java.prototype;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import prototypingclasses.AIPlayer;
import prototypingclasses.HumanPlayer;
import prototypingclasses.PrototypeBall;

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
        // TODO: Set it to track based on distance from each center
        // for now this will do...
        if (player.intersects(ball)) {
            ((HumanPlayer) player).setHasBall(true);
        } else {
            ((HumanPlayer) player).setHasBall(false);
        }

        if (ai1.intersects(ball)) {
            int playerToThrowTo = ((AIPlayer) ai1).setHasBall(true);
            Point targetPoint = getCoords(playerToThrowTo);
            ((PrototypeBall) ball).setTarget((int) targetPoint.getX(), (int) targetPoint.getY());
        } else {
            ((AIPlayer) ai1).setHasBall(false);
        }

        if (ai2.intersects(ball)) {
            int playerToThrowTo = ((AIPlayer) ai2).setHasBall(true);
            Point targetPoint = getCoords(playerToThrowTo);
            ((PrototypeBall) ball).setTarget((int) targetPoint.getX(), (int) targetPoint.getY());
        } else {
            ((AIPlayer) ai2).setHasBall(false);
        }

    }


    public void setInteractionObjects(
            Rectangle player, Rectangle ai1, Rectangle ai2, Rectangle ball) {
        this.player = player;
        this.ai1    = ai1;
        this.ai2    = ai2;
        this.ball   = ball;
    }

    public Point getCoords(int playerCode) {
        switch (playerCode) {
            case AIPlayer.HUMAN:
                return new Point((int) player.getCenterX(), (int) player.getCenterY());
            case AIPlayer.LEFT:
                return new Point((int) ai1.getCenterX(), (int) ai1.getCenterY());
            case AIPlayer.RIGHT:
                return new Point((int) ai2.getCenterX(), (int) ai2.getCenterY());
        }

        return null;
    }

}
