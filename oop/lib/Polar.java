package oop.lib;

public class Polar {
    public Polar() {
    }

    public static double angle(double xCoord, double yCoord) {
        double a;
        if (xCoord > 0.0) {
            a = Degrees.atan(yCoord / xCoord);
        } else if (xCoord < 0.0) {
            if (yCoord > 0.0) {
                a = Degrees.atan(yCoord / xCoord) + 180.0;
            } else {
                a = Degrees.atan(yCoord / xCoord) - 180.0;
            }
        } else if (yCoord >= 0.0) {
            a = 90.0;
        } else {
            a = -90.0;
        }

        return a;
    }

    public static double radius(double xDelta, double yDelta) {
        return Math.sqrt(xDelta * xDelta + yDelta * yDelta);
    }
}
