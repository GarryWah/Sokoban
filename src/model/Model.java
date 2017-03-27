package model;

import controller.EventListener;

import java.nio.file.Paths;

/**
 * Created by garry on 05.06.16.
 */
public class Model {
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("/home/garry/IdeaProjects/JavaRushHomeWork/src/com/javarush/test/level34/lesson15/big01/res/levels.txt"));
    public static final int FIELD_SELL_SIZE = 20;

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {

        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) return;
        if (checkBoxCollision(direction)) return;
        switch (direction) {
            case UP:
                player.move(0, -FIELD_SELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_SELL_SIZE);
                break;
            case LEFT:
                player.move(-FIELD_SELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_SELL_SIZE, 0);
                break;
        }
        checkCompletion();

    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        boolean result = false;
        for (Wall x : gameObjects.getWalls()) {
            if (gameObject.isCollision(x, direction)) result = true;

        }
        return result;
    }

    public boolean checkBoxCollision(Direction direction) {

        Player player = gameObjects.getPlayer();
        GameObject go = null;
        for (GameObject gameObject : gameObjects.getAll()) {
            if (!(gameObject instanceof Player) && !(gameObject instanceof Home) && player.isCollision(gameObject, direction)) {
                go = gameObject;
            }
        }
        if (go == null) {
            return false;
        }
        if (go instanceof Box) {
            Box x = (Box) go;
            if (checkWallCollision(x, direction)) {
                return true;
            }

            for (Box y : gameObjects.getBoxes()) {

                if (x.isCollision(y, direction)) {
                    return true;
                }


            }

            switch (direction) {
                case UP:
                    x.move(0, -FIELD_SELL_SIZE);
                    break;
                case DOWN:
                    x.move(0, FIELD_SELL_SIZE);
                    break;
                case LEFT:
                    x.move(-FIELD_SELL_SIZE, 0);
                    break;
                case RIGHT:
                    x.move(FIELD_SELL_SIZE, 0);
                    break;
            }

        }
        return false;

    }

    public void checkCompletion() {
        boolean result = true;
        for (Home x : gameObjects.getHomes()) {
            boolean tmp = false;
            for (Box y : gameObjects.getBoxes()) {
                if (y.getX() == x.getX() && y.getY() == x.getY()) tmp = true;
            }
            if (!tmp) result = false;
        }
        if (result) {
            eventListener.levelCompleted(currentLevel);
        }
    }
}
