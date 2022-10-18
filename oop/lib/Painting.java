package oop.lib;

import java.awt.Color;
import java.awt.Image;
import oop.lib.Mathematics;
import oop.lib.Cartesian;
public interface Painting {
    void setColor(Color var1);

    void fillCircle(double[] var1, double var2);

    default void fillSquare(double[] center, double side, double angle) {
        double radius = side / Mathematics.SQRT2;
        double[][] vertices = new double[][]{{center[0] + Cartesian.xCoord(radius, angle - 135.0), center[1] + Cartesian.yCoord(radius, angle - 135.0)}, {center[0] + Cartesian.xCoord(radius, angle - 45.0), center[1] + Cartesian.yCoord(radius, angle - 45.0)}, {center[0] + Cartesian.xCoord(radius, angle + 45.0), center[1] + Cartesian.yCoord(radius, angle + 45.0)}, {center[0] + Cartesian.xCoord(radius, angle + 135.0), center[1] + Cartesian.yCoord(radius, angle + 135.0)}};
        this.fillPolygon(vertices);
    }

    void fillPolygon(double[][] var1);

    void drawStroke(double[] var1, double[] var2, float var3);

    void drawImage(Image var1, double[] var2);

    default void drawImage(Image image, int x, int y) {
        this.drawImage(image, new double[]{(double)x, (double)y});
    }
}
