package project.model.shot;

import project.model.Direction;
import project.util.Point;
import project.util.Vector;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Bomb extends Shot {

    //////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////////////////////////////////////////
    public Bomb(Point pos, Direction direction) throws IOException {
        super(ImageIO.read(Bomb.class.getResource("/project/images/fireball.png")),
                pos, direction, 1);
    }

    //////////////////////////////////////////////////////////////////////
    // PUBLIC
    //////////////////////////////////////////////////////////////////////
    @Override
    public void move() {
        int y_VELOCITY = 2;
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
