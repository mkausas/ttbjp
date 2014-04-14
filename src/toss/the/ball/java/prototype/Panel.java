package toss.the.ball.java.prototype;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Marty
 */
public class Panel extends JPanel {

    private ArrayList<Drawable> drawables;

    public Panel() {

    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        g.setColor(Color.red);
        g.drawLine((int) Main.WIDTH / 2, 0, (int) Main.WIDTH / 2, (int) Main.HEIGHT);

        drawables = Main.drawables;
        for (int i = 0; i < drawables.size(); i++) {
            ((Drawable) drawables.get(i)).draw(g);
        }
    }

}
