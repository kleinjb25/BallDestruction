import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Contains the turret, list of enemies and missiles, and necessary methods for
 * the game.
 * 
 * @author Justin Klein
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
    /**
     * The list of enemies in the game. Objects are added in the addEnemy method
     * and removed in the detectCollison method.
     */
    private ArrayList<Enemy> enemyList;

    /**
     * The list of missiles in the game. Objects are added in the addMissile
     * method and removed in the detectCollison method.
     */
    private ArrayList<Missile> missileList;

    /**
     * The current score in the game. This value is updated in the
     * detectCollision method.
     */
    private int totalScore;

    /**
     * determine what enemy to generate next.
     */
    private boolean isNextEnemyBig;

    /**
     * a single turret in the game.
     */
    private Turret turret;

    /**
     * GamePanel constructor.
     */
    public GamePanel() {
        enemyList = new ArrayList<Enemy>();
        this.missileList = new ArrayList<Missile>();
        this.totalScore = 0;
        this.isNextEnemyBig = false;
        this.turret = new Turret();
        enemyList.add(new SmallEnemy((int) Math.random() * 700,
                (int) Math.random() * 500));
        enemyList.add(new BigEnemy((int) Math.random() * 700,
                (int) Math.random() * 500));
    }

    /**
     * Paint every single object.
     */
    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).paintComponent(g);
        }
        for (int i = 0; i < missileList.size(); i++) {
            missileList.get(i).paintComponent(g);
        }
        turret.paintComponent(g);
    }

    /**
     * moves each enemy and missile.
     */
    public void move() {
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).move(700, 500);
        }
        for (int i = 0; i < missileList.size(); i++) {
            missileList.get(i).move(700, 500, missileList, i);
        }
    }

    /**
     * adds an enemy to the game.
     */
    public void addEnemy() {
        if (isNextEnemyBig) {
            BigEnemy b = new BigEnemy(0, 0);
            enemyList.add(b);
        } else {
            SmallEnemy s = new SmallEnemy(0, 0);
            enemyList.add(s);
        }
    }

    /**
     * adds a missile to the game.
     */
    public void addMissile() {
        missileList.add(new Missile(330, 360));
    }

    /**
     * get the players total score.
     * 
     * @return the total score
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Method detects the collision of the missile and all the enemies. This is
     * done by drawing invisible rectangles around the enemies and missiles, if
     * they intersect, then they collide.
     */
    public void detectCollision() {
        try {
            // Uses bounds for enemies and missiles to detect intersection.
            for (int i = 0; i < enemyList.size(); i++) {
                Rectangle enemyRec = enemyList.get(i).getBounds();
                for (int j = 0; j < missileList.size(); j++) {
                    Rectangle missileRec = missileList.get(j).getBounds();
                    if (missileRec.intersects(enemyRec)) {
                        // Missile has hit an enemy!
                        enemyList.get(i).processCollision(enemyList, i);
                        missileList.remove(j);
                        if (enemyList.get(i) instanceof BigEnemy) {
                            totalScore += 50;
                        } else {
                            totalScore += 100;
                        }
                    }
                }
            }
        } catch (Exception e) {
            GameFrame.endOfGame(getTotalScore());
        }

    }
}
