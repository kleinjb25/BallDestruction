import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * A class to represent the enemies in the game.
 * @author kleinjb
 */
@SuppressWarnings("serial")
public abstract class Enemy extends JComponent {
    
    /**
     * speed of the enemy as a double.
     */
    
    private double enemySpeed;
    
    /**
     * color of the enemy as a string.
     */
    
    private Color enemycolor;
    
    /**
     * Enemy Constructor.
     * @param x the x coordinate of the enemy
     * @param y the y coordinate of the enemy
     * @param height the height of the enemy
     * @param width the width of the enemy 
     * @param enemySpeed the speed of the enemy
     */
    
    public Enemy(int x, int y, int height, int width, double enemySpeed) {
        setBounds(x, y, height, width);
        this.enemySpeed = enemySpeed;
    }
    
    /**
     * process any collisions that occur with an enemy.
     * @param e the list of enemies
     * @param i the index of the enemy
     */
    
    public abstract void processCollision(ArrayList<Enemy> e, int i);
    
    /**
     * sets the enemy's color to a random color.
     */
    
    public abstract void setColor();
    
    /**
     * moves the enemy in the x direction.
     * @param w the width of the enemy.
     * @param h the height of the enemy.
     */
    
    public abstract void move(int w, int h);
    
    /**
     * method that paints a graphics object with the enemy.
     * @param g the graphics object to be painted on.
     */
    
    public void paintComponent(Graphics g) {
        g.setColor(getEnemyColor());
        g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    
    /**
     * gets the enemy's speed.
     * @return a double representing the enemy's speed
     */
    
    public double getEnemySpeed() {
        return enemySpeed;
    }
    
    /**
     * sets the enemy's speed to the parameter s.
     * @param s the speed for which the enemy should be
     */
    public void setEnemySpeed(double s) {
        enemySpeed = s;
    }
    
    /**
     * gets the enemy's color.
     * @return a color object representing the enemy's color
     */
    public Color getEnemyColor() {
        return enemycolor;
    }
    
    /**
     * sets the enemy's color to the parameter c.
     * @param c the color for which the enemy should be
     */
    public void setEnemyColor(Color c) {
        enemycolor = c;
    }
        
}
