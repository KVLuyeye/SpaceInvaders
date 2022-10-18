package project.model.shot;

import project.model.Direction;
import project.util.Point;
import project.util.Vector;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Bullet extends Shot {

    //////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////////////////////////////////////////
    public Bullet(Point pos, Direction direction) throws IOException {
        super(ImageIO.read(Bullet.class.getResource("/project/images/laserbeam.png")),
                pos , direction, 1);
    }

    //////////////////////////////////////////////////////////////////////
    // PUBLIC
    //////////////////////////////////////////////////////////////////////
    @Override
    public void move() {
        int y_VELOCITY = 5;
        switch (getDirection()) {
            case UP:
                getPos().translate(new Vector(0, -y_VELOCITY));
                break;
            case DOWN:
                getPos().translate(new Vector(0, y_VELOCITY));
                break;
        }
    }
}
