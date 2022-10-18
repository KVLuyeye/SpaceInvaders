package project.model;

import project.model.shot.Shot;
import project.util.MyLinkedList;

public interface Attacker {
    MyLinkedList<Shot> attack();
}

