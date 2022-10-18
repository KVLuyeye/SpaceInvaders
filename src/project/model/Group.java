package project.model;

import oop.lib.Paintable;
import oop.lib.Painting;
import project.model.enemy.Enemy;
import project.model.shot.Shot;
import project.util.MyLinkedList;

import java.util.Collections;

public class Group<T extends Enemy> extends MyLinkedList<T> implements Moveable, Attacker, Hittable, Paintable {

    //////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////////////////////////////////////////

    public Group() {
        super();
    }

    //////////////////////////////////////////////////////////////////////
    // OVERRIDE
    //////////////////////////////////////////////////////////////////////

    @Override
    public MyLinkedList<Shot> attack() {
        MyLinkedList<Shot> shots = new MyLinkedList<>();
        for (T value : this) {
            shots.addAll(value.attack());
        }

        return shots;
    }

    @Override
    public void move(Direction direction) {
        for (T value : this) {
            value.move(direction);
        }
    }

    @Override
    public boolean gotHit(Shot shot) {
        for (T value : this) {
            if (value.gotHit(shot)) {
                remove(value);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isDead() {
        return isEmpty();
    }

    @Override
    public void paint(Painting painting) {
        for (T value : this) {
            value.paint(painting);
        }
    }

    //////////////////////////////////////////////////////////////////////
    // PUBLIC
    //////////////////////////////////////////////////////////////////////

    public int getMax() {
        MyLinkedList<Integer> xCoordinates = new MyLinkedList<>();
        for (T value : this) {
            xCoordinates.add((int) value.getPos().x());

        }

        return Collections.max(xCoordinates);
    }

    public int getMin() {
        MyLinkedList<Integer> xCoordinates = new MyLinkedList<>();
        for (T value : this) {
            xCoordinates.add((int) value.getPos().x());
        }

        return Collections.max(xCoordinates);
    }

    public void detectAndRemoveDeadEnemies() {
        MyLinkedList<Enemy> enemiesToRemove = new MyLinkedList<>();

        for (T value : this) {
            if (value.isDead()) {
                enemiesToRemove.add(value);
            }
        }

        // remove dead enemies
        removeAll(enemiesToRemove);
    }

    public int getWidth() {
        if (isEmpty()) {
            return 0;
        }
        return get(0).getWidth();
    }

    public int getHeight() {
        if (isEmpty()) {
            return 0;
        }
        return get(0).getHeight();
    }

}
