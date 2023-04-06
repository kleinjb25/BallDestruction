import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

/**
 * a class that defines what the turret is in the game.
 * 
 * @author kleinjb
 *
 */
public class Turret extends JComponent {
    /**
     * 
     */
    private static final long serialVersionUID = -6909696243102668476L;
    /**
     * the base of the turret.
     */
    private Rectangle base;
    /**
     * the part of the turret that fires.
     */
    private Rectangle turret;

    /**
     * the color of the turret.
     */

    private Color turretColor;

    /**
     * turret constructor.
     */

    public Turret() {
        base = new Rectangle(290, 400, 100, 40);
        turret = new Rectangle(320, 360, 40, 40);
        turretColor = new Color(255, 0, 0);
    }

    /**
     * paints the turret a different color.
     * 
     * @param g the graphics component that will be painted over
     */

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(g.getColor());
        g.fillRect((int) base.getX(), (int) base.getY(), (int) base.getWidth(),
                (int) base.getHeight());
        g.fillRect((int) turret.getX(), (int) turret.getY(),
                (int) turret.getWidth(), (int) turret.getHeight());

    }
}
