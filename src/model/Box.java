package model;

import java.awt.*;

/**
 * Created by garry on 05.06.16.
 */
public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        setX((getX() + x));
        setY((getY() + y));
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());

    }
}
