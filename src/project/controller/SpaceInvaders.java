package project.controller;

import project.model.Direction;
import project.model.Group;
import project.model.enemy.Enemy;
import project.model.player.Player;
import project.model.shot.Shot;
import project.util.MyLinkedList;
import project.view.Board;
import project.view.MyAnimation;
import project.model.enemy.AlienShip;
import project.util.Point;
import project.util.Vector;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class SpaceInvaders extends MyAnimation {

    private final int BOARD_WIDTH = 1200;
    private final int BOARD_HEIGHT = 800;

    /**
     * GAME VARIABLES
     */
    private Group<Enemy> enemies;
    private MyLinkedList<Shot> shots;
    private Player player;

    /**
     * CONTROLLER STATUS
     */
    private Board board;
    private Direction direction = Direction.RIGHT;

    //////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////////////////////////////////////////
    private SpaceInvaders() {
    }

    //////////////////////////////////////////////////////////////////////
    // MAIN
    //////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        new SpaceInvaders().launch(true);
    }

    //////////////////////////////////////////////////////////////////////
    // OVERRIDE
    //////////////////////////////////////////////////////////////////////
    @Override
    protected void init() {
        // Setup the level
        enemies = new Group<>();
        shots = new MyLinkedList<>();
        try {
            player = new Player(new Point(BOARD_WIDTH / 2., (int) (BOARD_HEIGHT * 0.95) - 50));

            int cols = 1;
            int rows = 2;
            Enemy enemyType = new AlienShip(new Point(0, 0));
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                    Point pos = new Point(AlienShip.ALIEN_INIT_POS);
                    pos.translate(new Vector(enemyType.getWidth() * i, enemyType.getHeight() * j));
                    enemies.add(new AlienShip(pos));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Setup the board
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT, Color.WHITE, enemies, shots, player);
        board.addKeyListener(new KeyAdapter() {
            // Set of currently pressed keys
            private final Set<Integer> pressed = new HashSet<>();

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(1);
                }
                pressed.add(e.getKeyCode());
                // More than one key is currently pressed. Iterate over pressed to get the keys.
                for (int c : pressed) {
                    switch (c) {
                        case KeyEvent.VK_LEFT:
                            if (player.getPos().x() > 0) {
                                player.move(Direction.LEFT);
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (player.getPos().x() + player.getWidth() < BOARD_WIDTH) {
                                player.move(Direction.RIGHT);
                            }
                            break;
                        case KeyEvent.VK_SPACE:
                            MyLinkedList<Shot> attacks = player.attack();
                            for (Shot attack : attacks) {
                                if (attack != null) {
                                    shots.add(attack);
                                }
                            }
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pressed.remove(e.getKeyCode());
            }
        });
        setDisplay(board);
    }

    @Override
    public void step() {
        // Shots step
        moveEnemies();
        resolveShotsCollisions();

        moveShots();

        // Check for victory
        checkVictoryConditions();
    }

    //////////////////////////////////////////////////////////////////////
    // PRIVATE
    //////////////////////////////////////////////////////////////////////

    private void moveEnemies() {
        // Step for the enemies
        enemies.move(direction);

        // If the border is met, everybody goes down and direction is changed
        if (enemies.getMax() >= BOARD_WIDTH - enemies.getWidth()) {
            direction = Direction.LEFT;
            enemies.move(Direction.DOWN);
        } else if (enemies.getMin() <= 0) {
            direction = Direction.RIGHT;
            enemies.move(Direction.DOWN);
        }
    }

    private void moveShots() {
        for (Shot shot : shots) {
            if (shot != null)
                shot.move();
        }
    }

    private void resolveShotsCollisions() {
        MyLinkedList<Shot> shotsToRemove = new MyLinkedList<>();
        // Check for damage
        for (Shot shot : shots) {
            // Check if enemies are hit
            if (shot == null) {
                continue;
            }
            if (shot.getDirection() == Direction.UP) {
                if (enemies.gotHit(shot)) {
                    shotsToRemove.add(shot);
                }
            }

            // Out of screen shots will be removed
            if (shot.getPos().y() < 0 || shot.getPos().y() > BOARD_HEIGHT) {
                shotsToRemove.add(shot);
            }
        }

        //remove all shots
        shots.removeAll(shotsToRemove);

        // Check for dead enemies and remove them
        enemies.detectAndRemoveDeadEnemies();
    }

    private void checkVictoryConditions() {
        // Victory
        if (enemies.isEmpty()) {
            board.victory();
        }
    }
}
