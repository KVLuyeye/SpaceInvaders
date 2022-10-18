package project.view;

import oop.lib.Painting;
import project.util.Point;

import java.awt.*;

public abstract class Sprite implements Representation {

    /**
     * x and y-coordinate of the whole sprite
     */
    private Point pos;

    /**
     * Image representing the sprite
     */
    private final Image image;

    //////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////////////////////////////////////////
    public Sprite(Image img, Point pos) {
        this.pos = pos;
        this.image = img;
    }

    //////////////////////////////////////////////////////////////////////
    // PUBLIC
    //////////////////////////////////////////////////////////////////////
    @Override
    public void paint(Painting painting) {
        painting.drawImage(image, pos.asArray());
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle((int) pos.x(), (int) pos.y(), image.getWidth(null), image.getHeight(null));
    }

    public boolean checkCollision(Sprite sprite) {
        return sprite.getBoundingBox().intersects(this.getBoundingBox());
    }

    //////////////////////////////////////////////////////////////////////
    // GETTERS & SETTERS
    //////////////////////////////////////////////////////////////////////

    public int getWidth() {
        return image.getWidth(null);
    }

    public int getHeight() {
        return image.getHeight(null);
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }
}
