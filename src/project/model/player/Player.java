package project.model.player;

import project.model.Attacker;
import project.model.Direction;
import project.model.Hittable;
import project.model.Moveable;
import project.model.shot.Bullet;
import project.model.shot.Shot;
import project.util.MyLinkedList;
import project.view.Sprite;
import project.util.Point;
import project.util.Vector;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Player extends Sprite implements Hittable, Moveable, Attacker {

    private int healthPoints = 5;
    private long lastShot;

    //////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////////////////////////////////////////
    public Player(Point pos) throws IOException {
        super(ImageIO.read(Player.class.getResource("/project/images/battleship.png")), pos);
    }

    //////////////////////////////////////////////////////////////////////
    // HITTABLE
    //////////////////////////////////////////////////////////////////////
    @Override
    public boolean gotHit(Shot shot) {
        if (healthPoints < 0) {
            throw new RuntimeException("A dead player can not be hit!");
        }

        if (checkCollision(shot)) {
            healthPoints -= shot.damage;
            return true;
        }

        return false;
    }

    @Override
    public boolean isDead() {
        return healthPoints <= 0;
    }

    //////////////////////////////////////////////////////////////////////
    // MOVABLE
    //////////////////////////////////////////////////////////////////////
    @Override
    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                getPos().translate(new Vector(-10, 0));
                break;
            case RIGHT:
                getPos().translate(new Vector(10, 0));
                break;
            default:
                throw new RuntimeException("Invalid direction for moving received: " + direction.toString());
        }
    }

    //////////////////////////////////////////////////////////////////////
    // ATTACKER
    //////////////////////////////////////////////////////////////////////
    @Override
    public MyLinkedList<Shot> attack() {
        MyLinkedList<Shot> shots = new MyLinkedList<>();
        int cooldown = 500;
        if (System.currentTimeMillis() - lastShot > cooldown) {
            try {
                shots.add(new Bullet(new Point(getPos().x() + getWidth() / 2., getPos().y()), Direction.UP));
            } catch (IOException e) {
                e.printStackTrace();
            }
            lastShot = System.currentTimeMillis();
        }
        return shots;
    }
}