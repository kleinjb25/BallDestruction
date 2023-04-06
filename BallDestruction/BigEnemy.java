import java.awt.Color;
import java.util.ArrayList;

/**
 * A class that describes the big enemies in the game
 * 
 * @author kleinjb
 *
 */
public class BigEnemy extends Enemy {

    /**
     * BigEnemy constructor.
     * 
     * @param panelWidth  the width of the panel
     * @param panelHeight the height of the panel
     */
    public BigEnemy(int panelWidth, int panelHeight) {
        super((int) Math.random() * 700,
                (int) Math.random() * 500, 56, 56, 4);
        float r = (float) Math.random();
        float g = (float) Math.random();
        float b = (float) Math.random();
        Color c = new Color(r, g, b);
        this.setEnemyColor(c);
    }

    /**
     * sets the enemy's color to a random color.
     */
    @Override
    public void setColor() {
        float r = (float) Math.random();
        float g = (float) Math.random();
        float b = (float) Math.random();
        Color c = new Color(r, g, b);
        this.setEnemyColor(c);

    }

    /**
     * process any collisions that occur with an enemy.
     * 
     * @param e the list of enemies
     * @param i the index of the enemy
     */
    @Override
    public void processCollision(ArrayList<Enemy> list, int bigEnemy) {
        Enemy b = list.get(bigEnemy);
        b.setBounds(b.getX(), b.getY(), b.getWidth() - 20, b.getHeight() - 20);
        if (b.getWidth() < 0 || b.getHeight() < 0) {
            list.remove(bigEnemy);
        }
    }

    /**
     * moves the enemy in the x direction.
     * 
     * @param w the width of the enemy.
     * @param h the height of the enemy.
     */
    @Override
    public void move(int w, int h) {
        double newX = this.getX() + this.getEnemySpeed();
        if (newX > w || newX < 0) {
            this.setEnemySpeed(this.getEnemySpeed() * -1);
        } else {
            this.setBounds((int) (newX), this.getY(), this.getWidth(),
                    this.getHeight());
        }
    }
}
