package project.model;

import project.model.shot.Shot;

public interface Hittable {
    boolean gotHit(Shot shot);

    boolean isDead();
}
