package oop.lib;
import oop.lib.Degrees;


public class Cartesian {
    public Cartesian() {
    }

    public static double xCoord(double radius, double angle) {
        return radius * Degrees.cos(angle);
    }

    public static double yCoord(double radius, double angle) {
        return radius * Degrees.sin(angle);
    }
}
