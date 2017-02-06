package model;

import java.awt.*;

/**
 * Created by garry on 05.06.16.
 */
public class Wall extends CollisionObject
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(getX(),getY(),getWidth(),getWidth());

    }
}
