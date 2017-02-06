package model;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by garry on 05.06.16.
 */
public class LevelLoader
{
    private Path levels;

    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }

    public GameObjects getLevel(int level){
        if(level>60){level=level%60;}
        int x0=Model.FIELD_SELL_SIZE/2;
        int y0=Model.FIELD_SELL_SIZE/2;
        Set<Wall> walls=new HashSet<>();
        Set<Home> homes=new HashSet<>();
        Set<Box> boxes=new HashSet<>();
        Player player=null;

        try
        {

            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(levels.toFile())));


                String str=null;

            while(true){
                str=br.readLine();
                if(str.equals(String.format("Maze: %s", level)))break;
            }
            br.readLine();
            int width=Integer.parseInt(br.readLine().split(" ")[2]);
            int height=Integer.parseInt(br.readLine().split(" ")[2]);
            br.readLine();
            br.readLine();
            br.readLine();

                for(int i=0;i<height;i++){
                    String in=br.readLine();
                    char[] ar=in.toCharArray();
                    for(int j=0;j<width;j++){
                        switch(ar[j]){
                            case 'X':walls.add(new Wall(j*Model.FIELD_SELL_SIZE+x0,i*Model.FIELD_SELL_SIZE+y0));break;
                            case '*':boxes.add(new Box(j*Model.FIELD_SELL_SIZE+x0,i*Model.FIELD_SELL_SIZE+y0));break;
                            case '.':homes.add(new Home(j*Model.FIELD_SELL_SIZE+x0,i*Model.FIELD_SELL_SIZE+y0));break;
                            case '&':boxes.add(new Box(j*Model.FIELD_SELL_SIZE+x0,i*Model.FIELD_SELL_SIZE+y0));
                                     homes.add(new Home(j*Model.FIELD_SELL_SIZE+x0,i*Model.FIELD_SELL_SIZE+y0));
                                     break;
                            case '@':player=new Player(j*Model.FIELD_SELL_SIZE+x0,i*Model.FIELD_SELL_SIZE+y0);break;
                            case ' ':break;
                        }

                    }


                }
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        GameObjects gameObjects=new GameObjects(walls,boxes,homes,player);
        return gameObjects;
    }
}
