package oop.lib;

public class Degrees {
    public Degrees() {
    }

    public static double sin(double deg) {
        return Math.sin(deg * Math.PI / 180.0);
    }

    public static double cos(double deg) {
        return Math.cos(deg * Math.PI / 180.0);
    }

    public static double tan(double deg) {
        return Math.tan(deg * Math.PI / 180.0);
    }

    public static double asin(double sin) {
        return Math.asin(sin) * 180.0 / Math.PI;
    }

    public static double acos(double cos) {
        return Math.acos(cos) * 180.0 / Math.PI;
    }

    public static double atan(double tan) {
        return Math.atan(tan) * 180.0 / Math.PI;
    }

    public static double atan2(double dy, double dx) {
        return Math.atan2(dy, dx) * 180.0 / Math.PI;
    }

    public static double toRadians(double deg) {
        return Math.PI * deg / 180.0;
    }
}
