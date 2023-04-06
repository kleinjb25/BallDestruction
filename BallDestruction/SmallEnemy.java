import java.awt.Color;
import java.util.ArrayList;

/**
 * A class to represent the small enemies in the game.
 * 
 * @author kleinjb
 *
 */
public class SmallEnemy extends Enemy {

    /**
     * SmallEnemy constructor.
     * 
     * @param panelWidth  the width of the panel
     * @param panelHeight the height of the panel
     */
    public SmallEnemy(int panelWidth, int panelHeight) {
        super((int) Math.random() * 700,
                (int) Math.random() * 500, 30, 30, 3);
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
    public void processCollision(ArrayList<Enemy> list, int smallEnemy) {
        Enemy s = list.get(smallEnemy);
        s.setBounds(s.getX(), s.getY(), s.getWidth() - 10, s.getHeight() - 10);
        if (s.getWidth() <= 0 || s.getHeight() <= 0) {
            list.remove(smallEnemy);
        }

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
        }
        this.setBounds((int) (newX), this.getY(), this.getWidth(),
                this.getHeight());
        if (this.getEnemySpeed() > 0) {
            this.setEnemySpeed(this.getEnemySpeed() + 0.05);
        } else {
            this.setEnemySpeed(this.getEnemySpeed() - 0.05);
        }

    }
}
