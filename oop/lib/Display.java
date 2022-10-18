package oop.lib;

import javax.swing.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.image.ImageObserver;
import oop.lib.Painting;
public abstract class Display extends JPanel implements Painting {
    private int xOrigin = 0;
    private int yOrigin = 0;
    private double xScale = 1.0;
    private double yScale = 1.0;
    private Graphics2D g2;
    private Color color;

    protected Display(int width, int height) {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width, height));
    }

    public abstract void paint(Display var1);

    public void paint(Graphics g) {
        super.paint(g);
        this.g2 = (Graphics2D) g;
        this.g2.translate(this.xOrigin, this.yOrigin);
        this.g2.scale(this.xScale, this.yScale);
        this.g2.setStroke(new BasicStroke());
        this.paint(this);
    }

    public void fillCircle(double[] center, double radius) {
        this.g2.setColor(this.color);
        this.g2.fill(new Ellipse2D.Double(center[0] - radius, center[1] - radius, 2.0 * radius, 2.0 * radius));
    }

    public void fillPolygon(double[][] vertices) {
        Path2D path = new Path2D.Double();
        path.moveTo(vertices[0][0], vertices[0][1]);

        for (int i = 1; i < vertices.length; ++i) {
            path.lineTo(vertices[i][0], vertices[i][1]);
        }

        path.closePath();
        this.g2.setColor(this.color);
        this.g2.fill(path);
    }

    public void drawStroke(double[] pt1, double[] pt2, float thickness) {
        Line2D line = new Line2D.Double(pt1[0], pt1[1], pt2[0], pt2[1]);
        this.g2.setStroke(new BasicStroke(thickness));
        this.g2.setColor(this.color);
        this.g2.draw(line);
    }

    public void drawImage(Image image, double[] point) {
        int x = Math.round((float) point[0]);
        int y = Math.round((float) point[1]);
        this.g2.drawImage(image, x, y, (ImageObserver) null);
    }

    public double xMin() {
        return (double) (-this.xOrigin) / this.xScale;
    }

    public double xMax() {
        return (double) (this.getWidth() - this.xOrigin) / this.xScale;
    }

    public double yMin() {
        return this.yScale < 0.0 ? (double) (this.getHeight() - this.yOrigin) / this.yScale : (double) (-this.yOrigin) / this.yScale;
    }

    public double yMax() {
        return this.yScale < 0.0 ? (double) (-this.yOrigin) / this.yScale : (double) (this.getHeight() - this.yOrigin) / this.yScale;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setOrigin(int x, int y) {
        this.xOrigin = x;
        this.yOrigin = y;
    }

    public void setScale(double scale, boolean upwardYAxis) {
        this.xScale = scale;
        this.yScale = scale;
        if (upwardYAxis) {
            this.yScale = -this.yScale;
        }

    }
}
