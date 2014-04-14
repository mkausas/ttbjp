package toss.the.ball.java.prototype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import prototypingclasses.AIPlayer;
import prototypingclasses.HumanPlayer;
import prototypingclasses.Ball;

/**
 *
 * @author Marty
 */
public class Main extends JFrame implements Runnable, KeyListener {

    // Custom classes
    private Panel panel = new Panel();
    private InteractionManager manager = new InteractionManager();

    public static ArrayList<Drawable> drawables = new ArrayList<Drawable>();
    public static ArrayList<Actable> actables = new ArrayList<Actable>();

    // Objects that interact
    private Ball ball = new Ball();
    private HumanPlayer player = new HumanPlayer();
    private AIPlayer ai1 = new AIPlayer(AIPlayer.LEFT);
    private AIPlayer ai2 = new AIPlayer(AIPlayer.RIGHT);

    // constants
    public static int
            WIDTH = 800,
            HEIGHT = 600;

    public Main() {
        super("Throw the ball?");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        manager.setInteractionObjects(player, ai1, ai2, ball);
        manager.setPanel(panel);

        addKeyListener(this);

        // actables
        actables.add(ball);
        actables.add(player);
        actables.add(ai1);
        actables.add(ai2);

        // drawables
        drawables.add(ball);
        drawables.add(player);
        drawables.add(ai1);
        drawables.add(ai2);

        // add all objects to the frame
        add(panel);

        setVisible(true);
    }

    //main
    public static void main(String[] args) {
        new Main().run();
    }

    @Override
    public void run() {
        while (true) {
            try {
                // run manager
                manager.run();

                // repaint
                panel.repaint();

                Thread.sleep(3);
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }





    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (player.intersects(ball)) {
            if (e.getKeyCode() == e.VK_LEFT) {
                manager.turnOffWallTimer();
                ball.setTarget((int) ai1.getCenterX(), (int) ai1.getCenterY());
            } else if (e.getKeyCode() == e.VK_RIGHT) {
                manager.turnOffWallTimer();
                ball.setTarget((int) ai2.getCenterX(), (int) ai2.getCenterY());
            }
        }
    }

}
