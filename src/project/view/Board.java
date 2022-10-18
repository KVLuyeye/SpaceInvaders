package project.view;

import oop.lib.Display;
import project.model.enemy.Enemy;
import project.model.player.Player;
import project.model.shot.Shot;
import project.util.MyLinkedList;

import javax.swing.*;
import java.awt.*;

public class Board extends Display {

    private int height;
    private int width;

    private MyLinkedList<Enemy> enemies;
    private MyLinkedList<Shot> shots;
    private Player player;

    //////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////////////////////////////////////////
    public Board(int width, int height, Color backgroundColor, MyLinkedList<Enemy> enemies, MyLinkedList<Shot> shots, Player player) {
        super(width, height);
        this.width = width;
        this.height = height;
        this.enemies = enemies;
        this.shots = shots;
        this.player = player;

        setSize(width, height);
        setBackground(backgroundColor);
        setVisible(true);
    }

    //////////////////////////////////////////////////////////////////////
    // PUBLIC
    //////////////////////////////////////////////////////////////////////
    public void defeat() {
        String GAME_OVER_MESSAGE = "GAME OVER!!";
        JOptionPane.showMessageDialog(this, GAME_OVER_MESSAGE, GAME_OVER_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
        System.exit(ABORT);
    }

    public void victory() {
        String VICTORY_MESSAGE = "YOU WIN!";
        JOptionPane.showMessageDialog(this, VICTORY_MESSAGE, VICTORY_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
        System.exit(ABORT);
    }

    //////////////////////////////////////////////////////////////////////
    // OVERRIDE
    //////////////////////////////////////////////////////////////////////
    @Override
    public void paint(Display display) {

        // draw the horizontal finish line for the enemies
        setColor(Color.GRAY);
        drawStroke(new double[]{0, height * 0.75}, new double[]{width, height * 0.75}, 5);

        if (enemies != null) {
            for (Enemy enemy : enemies) {
                if (enemy != null) {
                    enemy.paint(this);
                }
            }
        }

        if (shots != null) {
            for (Shot shot : shots) {
                if (shot != null) {
                    shot.paint(this);
                }
            }
        }

        if (player != null) {
            player.paint(this);
        }
    }

}
