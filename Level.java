import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Level {
    /* 
        In a level, there are two components: the obstacles and the path. 
        The positions of the obstacles are determined by the position of the 
        path. An obstacle will never be where the path is, with a buffer of 5 pixels
        because my collision detection is bad. This means that 
        the obstacles can fill randomly without interfering with the players 
        ability to get across the screen.
    */

    /* constants */
    final int obstacleNum = 400;

    //global variables
    static int level;
    private MainCharacter main;
    ArrayList<Obstacle> obstacles;
    ArrayList<Item> items;
    int[][] path;
    public Level(MainCharacter c) {
        level++;
        main = c;
        obstacles = new ArrayList<Obstacle>();
        items = new ArrayList<Item>();
        path = new int[100][2];
        path[0] = new int[]{0,350}; //first path position
        int i = 1; //index variable
        int x = 0; //x position variable
        boolean added = false;
        while(i < path.length) { 
            //if statements for randomness
            double random = Math.random(); //make it so there is always an option
            if(random > 0.6 && path[i-1][1] > 50) {
                path[i] = new int[] {path[i-1][0],path[i-1][1]-50};
            }
            else if(random > 0.3 && path[i-1][1] < 500) {
                path[i] = new int[] {path[i-1][0],path[i-1][1]+50};
            }
            else {
                path[i] = new int[] {path[i-1][0]+50,path[i-1][1]}; 
            }
            x = path[i][0]; //update the position variable that will determine whether the path repeats from the beginning
            if(x > 750) { //if the path hits the edge of the screen, come back to the beginning.
                path[i][0] = path[i][0]%800;
                path[i][1] = (int)(Math.random()*500);
            }
            // add the flashlight around x = 500
            if(level == 1 && !added && path[i][0] > 500) { 
                items.add(new Flashlight(path[i][0],path[i][1]));
                added = true;
            }
            if(level == 2 && !added && path[i][0] > 500) { 
                items.add(new Sword(path[i][0],path[i][1]));
                added = true;
            }
            i++; //increment the index variable
        }
        //now we randomly add the obstacles if there is not a path block there
        while(obstacles.size() < obstacleNum) { 
            boolean toAdd = true;
            int tempX = (int)(Math.random()*750+50);
            int tempY = (int)(Math.random()*600);
            for(int j = 0;j < path.length;j++) {
                // is the obstacle where the path is?
                if(tempX+50 > path[j][0] && tempX < path[j][0]+50 && tempY+50 > path[j][1] && tempY < path[j][1]+60) {
                    toAdd = false;
                    break;
                }
            }
            //add it if the obstacle is not in the way of the path
            if(toAdd) {
                obstacles.add(new Obstacle(tempX,tempY));
            }
        }
    }
    // returns boolean determining whether the level is done or not
    public boolean levelCondition() {
        if(level == 1) {
            for(Item each : items) {
                //for level 1 the player must collect the flashlight. If the flashlight is in the items list the player does not have it
                if(each.getType().equals("flashlight")) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public void draw(Graphics g) {
        //draw the obstacles
        for(Obstacle each : obstacles) {
            each.draw(g);
        }
        //draw the path outline for testing
        g.setColor(Color.blue);
        for(int i = 0;i < path.length;i++) {
            g.drawRect(path[i][0],path[i][1],50,50);
        }
        for(int i = 0;i < items.size();i++) {
            if(main.x+main.size > items.get(i).x && main.x < items.get(i).x+items.get(i).size && main.y+main.size > items.get(i).y && main.y < items.get(i).y+items.get(i).size) {
                main.addItem(items.remove(i));
                i--;
            }
        }
        for(Item each : items) {
            each.draw(g);
        }
    }

}
