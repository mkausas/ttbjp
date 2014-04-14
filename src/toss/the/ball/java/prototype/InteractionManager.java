package toss.the.ball.java.prototype;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import prototypingclasses.AIPlayer;
import prototypingclasses.HumanPlayer;
import prototypingclasses.Ball;

/**
 *
 * @author Marty
 */
public class InteractionManager implements Runnable {

    private ArrayList<Actable> actables;
    private Rectangle
            player,
            ai1,
            ai2,
            ball;

    private Panel panel;

    private boolean ballPossesionHasChanged = false;
    private int ballTarget = AIPlayer.HUMAN;
    private int currentBallHolder = AIPlayer.HUMAN;
    private boolean gameOver = false;

    @Override
    public void run() {
        try {
            actables = Main.actables;

            for (int i = 0; i < actables.size(); i++) {
                ((Actable) actables.get(i)).act();
            }

            interact();

            if (gameOver)
                panel.gameOver();

            Thread.sleep(10);
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void interact() {
        // TODO: Set it to track based on distance from each center
        // for now this will do...

        // ball possesion has changed is true when
        // previous balls target character == current ball holder
        ballPossesionHasChanged = ballTarget == currentBallHolder;


        if (player.intersects(ball)) {
            currentBallHolder = AIPlayer.HUMAN;
            if (ballPossesionHasChanged) {
                int playerToThrowTo = ((HumanPlayer) player).setHasBall(true);
                ballTarget = playerToThrowTo;
                System.out.println("ball possesion just changed human");
                panel.setSideTimer(playerToThrowTo);
            }
        } else {
            ((HumanPlayer) player).setHasBall(false);
        }

        if (ai1.intersects(ball)) {
            if (AIPlayer.LEFT != ballTarget && currentBallHolder == AIPlayer.HUMAN)
                gameOver = true;

            currentBallHolder = AIPlayer.LEFT;
            if (ballPossesionHasChanged) {
                int playerToThrowTo = ((AIPlayer) ai1).setHasBall(true);
                ballTarget = playerToThrowTo;
                System.out.println("ball possesion just changed ai1");
                Point targetPoint = getCharacterCoords(playerToThrowTo);
                ((Ball) ball).setTarget((int) targetPoint.getX(), (int) targetPoint.getY());
            }
        } else {
            ((AIPlayer) ai1).setHasBall(false);
        }

        if (ai2.intersects(ball)) {
            if (AIPlayer.RIGHT != ballTarget && currentBallHolder == AIPlayer.HUMAN)
                gameOver = true;

            currentBallHolder = AIPlayer.RIGHT;
            if (ballPossesionHasChanged) {
                int playerToThrowTo = ((AIPlayer) ai2).setHasBall(true);
                ballTarget = playerToThrowTo;
                System.out.println("ball possesion just changed ai2");
                Point targetPoint = getCharacterCoords(playerToThrowTo);
                ((Ball) ball).setTarget((int) targetPoint.getX(), (int) targetPoint.getY());
            }
        } else {
            ((AIPlayer) ai2).setHasBall(false);
        }

        if (panel.timerHasRunOut())
            gameOver = true;

        System.out.println("game over = " + gameOver);

    }

    public void turnOffWallTimer() {
        panel.setSideTimer(0);
    }


    public void setInteractionObjects(
            Rectangle player, Rectangle ai1, Rectangle ai2, Rectangle ball) {
        this.player = player;
        this.ai1    = ai1;
        this.ai2    = ai2;
        this.ball   = ball;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public Point getCharacterCoords(int playerCode) {
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
