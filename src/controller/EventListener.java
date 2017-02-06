package controller;

import model.*;

/**
 * Created by garry on 05.06.16.
 */
public interface EventListener
{
    void move(Direction direction);
    void restart();
    void startNextLevel();
    void levelCompleted(int level);

}
