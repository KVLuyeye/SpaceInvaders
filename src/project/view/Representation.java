package project.view;

import oop.lib.Paintable;

import java.awt.*;

interface Representation extends Paintable {

    /**
     * Computes and returns the bounding box of the element
     *
     * @return a rectangle representing the bounding box of the element
     */
    Rectangle getBoundingBox();
}
