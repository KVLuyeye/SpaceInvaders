package project.util;

import oop.lib.Degrees;

/**
 * @author ingold
 */
public class Vector {

    private final double dx;
    private final double dy;

    public Vector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Vector(Point pt) {
        this(pt.x(), pt.y());
    }

    public Vector(Point from, Point to) {
        this(to.x() - from.x(), to.y() - from.y());
    }

    public double dx() {
        return dx;
    }

    public double dy() {
        return dy;
    }

    public Vector add(Vector v) {
        return new Vector(dx + v.dx, dy + v.dy);
    }

    public Vector scale(double v) {
        return new Vector(v * dx, v * dy);
    }

    public Vector rotate(double angle) {
        double newDx = Degrees.cos(angle) * dx - Degrees.sin(angle) * dy;
        double newDy = Degrees.sin(angle) * dx + Degrees.cos(angle) * dy;
        return new Vector(newDx, newDy);
    }

    @Override
    public String toString() {
        return "oop_Vector:[dx=" + dx + ",dy=" + dy + "]";
    }

}