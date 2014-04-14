package toss.the.ball.java.prototype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import prototypingclasses.AIPlayer;
import prototypingclasses.HumanPlayer;
import prototypingclasses.PrototypeBall;

/**
 *
 * @author Marty
 */
public class Main extends JFrame implements Runnable, KeyListener {

    // Custom classes
    private Panel panel = new Panel();
    private BossManager manager = new BossManager();

    public static ArrayList<Drawable> drawables = new ArrayList<Drawable>();
    public static ArrayList<Actable> actables = new ArrayList<Actable>();

    // Objects that interact
    private PrototypeBall protoBall = new PrototypeBall();
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

        manager.setInteractionObjects(player, ai1, ai2, protoBall);
//        System.out.println("ball x and y initially = " + protoBall.getCenterX() + ", " + protoBall.getCenterY());
//        System.out.println("player position = " + player.getCenterX() + ", " + player.getCenterY());

        addKeyListener(this);

        // actables
        actables.add(protoBall);
        actables.add(player);
        actables.add(ai1);
        actables.add(ai2);

        // drawables
        drawables.add(protoBall);
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

                Thread.sleep(10);
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
        if (e.getKeyCode() == e.VK_LEFT) {
            protoBall.setTarget((int) ai1.getCenterX(), (int) ai1.getCenterY());

        } else if (e.getKeyCode() == e.VK_RIGHT) {
            protoBall.setTarget((int) ai2.getCenterX(), (int) ai2.getCenterY());

        } else if (e.getKeyCode() == e.VK_DOWN) {
            protoBall.setTarget((int) player.getCenterX()
                    - (PrototypeBall.SIZE / 2) + 1,
                    (int) player.getCenterY() - (PrototypeBall.SIZE / 2) + 1);
        }
    }

}
