package project.model.enemy;

import project.model.Direction;
import project.model.shot.Bomb;
import project.model.shot.Shot;
import project.util.MyLinkedList;
import project.util.Point;

import javax.imageio.ImageIO;
import java.io.IOException;

public class AlienShip extends Enemy {
    /**
     * Initial coordinates for the alien
     */
    public final static Point ALIEN_INIT_POS = new Point(150, 5);
    private final static double FIRE_CHANCE = 0.005;

    //////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////////////////////////////////////////
    public AlienShip(Point pos) throws IOException {
        super(ImageIO.read(AlienShip.class.getResource("/project/images/SpaceInvader.png")),
                pos,
                15,
                1);
    }

    //////////////////////////////////////////////////////////////////////
    // ATTACKER
    //////////////////////////////////////////////////////////////////////
    @Override
    public MyLinkedList<Shot> attack() {
        MyLinkedList<Shot> shots = new MyLinkedList<>();
        // If triggered, shoot
        if (random.nextDouble() < FIRE_CHANCE) {
            try {
                shots.add(new Bomb(new Point(getPos().x() + getWidth() / 2., getPos().y() + getHeight()), Direction.DOWN));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return shots;
    }
}
