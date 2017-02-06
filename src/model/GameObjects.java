package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by garry on 05.06.16.
 */
public class GameObjects
{
    private Set<Wall> walls;
    private Set<Box> boxes;
    private Set<Home> homes;
    private Player player;

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player)
    {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public Set<Wall> getWalls()
    {
        return walls;
    }

    public Set<Box> getBoxes()
    {
        return boxes;
    }

    public Set<Home> getHomes()
    {
        return homes;
    }

    public Player getPlayer()
    {
        return player;
    }
    public Set<GameObject> getAll(){
        Set<GameObject> set= new HashSet<GameObject>();
        for(Box x:getBoxes()){Collections.addAll(set,x);}
        for(Wall x:getWalls()){Collections.addAll(set,x);}
        for(Home x:getHomes()){Collections.addAll(set,x);}
        set.add(getPlayer());
        return set;

    }
}
