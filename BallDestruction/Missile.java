import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * This class contains the paintable objects such as the enemies, turret, and
 * missile. It also keeps track of the total score and collisions.
 * 
 * @author Justin Klein
 */
@SuppressWarnings("serial")
public class Missile extends JComponent {
    /**
     * Speed of the missile as a integer.
     */
    private int missileSpeed;
    /**
     * Color of the missile as a color object.
     */
    private Color missileColor;

    /**
     * missile constructor.
     * 
     * @param x the x coordinate of the missile.
     * @param y the x coordinate of the missile.
     */
    public Missile(int x, int y) {
        missileSpeed = 10;
        float r = (float) Math.random();
        float g = (float) Math.random();
        float b = (float) Math.random();
        Color c = new Color(r, g, b);
        missileColor = c;
        super.setBounds(x, y, 15, 15);
    }

    /**
     * sets the missile color to a random color.
     */

    public void setMissileColor() {
        float r = (float) Math.random();
        float g = (float) Math.random();
        float b = (float) Math.random();
        Color c = new Color(r, g, b);
        missileColor = c;
    }

    /**
     * method that paints a graphics object with the missile.
     * 
     * @param g the graphics object to be painted on.
     */

    public void paintComponent(Graphics g) {
        g.setColor(missileColor);
        g.fillOval(super.getX(), super.getY(), 15, 15);
    }

    /**
     * moves the missile in the y direction.
     * 
     * @param width   the width of the missile
     * @param height  the height of the missile
     * @param list    the list of missiles
     * @param missile the index for the desired missile
     */

    public void move(int width, int height, ArrayList<Missile> list,
            int missile) {
        double newY = this.getY() - this.getMissileSpeed();
        if (newY < 0 || newY > height) {
            list.remove(missile);
        } else {
            this.setBounds(this.getX(), (int) newY, width, height);
        }
    }

    /**
     * gets the missile's speed.
     * 
     * @return the missile's speed
     */

    public int getMissileSpeed() {
        return missileSpeed;
    }

    /**
     * sets the missile's speed to the parameter.
     * 
     * @param speed the desired speed to set the missile to
     */

    public void setMissileSpeed(int speed) {
        missileSpeed = speed;
    }
}
