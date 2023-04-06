import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * The top-level Frame class for the Game.
 * 
 * @author Justin Klein
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {
    /** Fixed width for the game. */
    private static final int WINDOW_WIDTH = 700;
    /** Fixed height for the game. */
    private static final int WINDOW_HEIGHT = 500;

    /**
     * Just a label added to the north of the game panel to display score.
     * 
     * @see score
     */
    private JLabel scoreLabel;

    /**
     * A label showing how much ammo the user has
     * 
     * @see shotsFired
     * @see numAllowed
     */
    private JLabel ammo;

    /**
     * The remaining number of calls to the gameStep method before a new enemy
     * is automatically added to the game.
     */
    private int enemyGenerationCounter;

    /**
     * The number of missile objects that have been created.
     */
    private static int shotsFired;

    /**
     * The number of missiles the user is allowed to fire
     */
    private static final int ALLOWED = 10;
    /**
     * A button that allows the user to fire a missile.
     */
    private JButton fireButton;

    /**
     * The game panel that logically encapsulates all the enemies, missiles, and
     * the turret.
     */
    private GamePanel panel;

    /**
     * Default constructor to control the game.
     */
    public GameFrame() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        panel = new GamePanel();
        scoreLabel = new JLabel("Score: " + panel.getTotalScore());
        ammo = new JLabel("Shots Fired: " + shotsFired / ALLOWED);
        fireButton = new JButton("Shoot the enemy!");
        fireButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.addMissile();
                shotsFired++;
            }
        });
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(scoreLabel);
        scoreLabel.setForeground(Color.WHITE);
        c.add(panel, BorderLayout.CENTER);
        c.add(fireButton, BorderLayout.SOUTH);
        panel.add(ammo);
        ammo.setForeground(Color.WHITE);
        ammo.setHorizontalAlignment(JLabel.CENTER);
        setTitle("Ball Destruction!");
        enemyGenerationCounter = 0;
        shotsFired = 0;
    }

    private void gameStep() {
        panel.detectCollision();
        int score = panel.getTotalScore();
        scoreLabel.setText("Score: " + Integer.toString(score));
        ammo.setText("Shots Fired: " + Integer.toString(shotsFired) + " / "
                + Integer.toString(ALLOWED));
        panel.move();
        panel.repaint();
        endOfGame(score);
        if (enemyGenerationCounter == 0) {
            panel.addEnemy();
            setEnemyGenerationCounter();
        }
        enemyGenerationCounter--;
    }

    public static void endOfGame(int score) {
        if (shotsFired / ALLOWED == 1) {
            if (score >= 1000) {
                JOptionPane.showMessageDialog(null,
                        "You Win!",
                        "Game Finished Message",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(null,
                        "You Lose! Try to get a score of at least 1000 next time!",
                        "Game Finished Message",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
    }

    public void start() {
        // Center the frame on the screen and show it.
        centerFrame(this);
        setVisible(true);
        // Create a game-step timer to step through the game.
        Timer gameStep = new Timer(30, e -> gameStep());
        gameStep.start();
    }

    /**
     * Method centers the frame in the middle of the screen. This is really just
     * a nicety and does not affect the operation of the game in any meaningful
     * way.
     * 
     * @param frame to center with respect to the users screen dimensions.
     */
    private void centerFrame(JFrame frame) {
        int width = frame.getWidth();
        int height = frame.getHeight();
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        // Figure out where the frame needs to be positions to center it.
        int xposition = center.x - width / 2;
        int yposition = center.y - height / 2;
        // Center the frame.
        frame.setBounds(xposition, yposition, width, height);
        frame.validate();
    }

    /**
     * Randomly assign a value to determine how soon a new Enemy should be
     * created.
     */
    private void setEnemyGenerationCounter() {
        // Set the number of times the gameStep method should be called
        // before the next enemy is added to the game.
        enemyGenerationCounter = (int) (Math.random() * 300);
    }

    /**
     * The main method to execute the game.
     * 
     * @param args Command-line arguments if any. This program does not use this
     *             argument.
     */
    public static void main(String[] args) {
        GameFrame main = new GameFrame();
        main.start();
    }
}
