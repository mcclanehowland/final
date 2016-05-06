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
    int difficulty;
    ArrayList<Obstacle> obstacles;
    int[][] path;
    public Level(int difficulty) {
        this.difficulty = difficulty;
        obstacles = new ArrayList<Obstacle>();
        path = new int[100][2];
        path[0] = new int[]{0,350}; //first path position
        int i = 1; //index variable
        int x = 0; //x position variable
        while(i < path.length) { 
            //if statements are only for randomness.
            /** I need to simplify this */
            double random = Math.random();
            if(random > 0.6 && path[i-1][1] > 50) {
                path[i] = new int[] {path[i-1][0],path[i-1][1]-50};
            }
            else if(random > 0.3 && path[i-1][1] < 550) {
                path[i] = new int[] {path[i-1][0],path[i-1][1]+50};
            }
            else {
                path[i] = new int[] {path[i-1][0]+50,path[i-1][1]}; 
            }



            /*if(Math.random() > 0.5) { 
                //add the next path block in front of the previous
                path[i] = new int[] {path[i-1][0]+50,path[i-1][1]}; 
            }
            else {
                if(Math.random() > 0.5 && path[i-1][1] < 500) {
                    //add the path block below the previous
                    path[i] = new int[] {path[i-1][0],path[i-1][1]+50};
                }
                else if(path[i-1][1] > 0){
                    //add the path block above the previous
                    path[i] = new int[] {path[i-1][0],path[i-1][1]-50};
                }
            }
            */
            x = path[i][0]; //update the position variable
            if(x > 800) { //if the path hits the edge of the screen, come back to the beginning.
                path[i][0] = path[i][0]%800;
                path[i][1] = (int)(Math.random()*500);
            }
            i++; //increment the index variable
        }
        //now we randomly add the obstacles if there is not a path block there
        while(obstacles.size() < obstacleNum) { 
            boolean toAdd = true;
            int tempX = (int)(Math.random()*750+50);
            int tempY = (int)(Math.random()*600);
            for(int j = 0;j < path.length;j++) {
                if(tempX+50 > path[j][0] && tempX < path[j][0]+50 && tempY+50 > path[j][1] && tempY < path[j][1]+60) {
                    toAdd = false;
                    break;
                }
            }
            if(toAdd) {
                obstacles.add(new Obstacle(tempX,tempY));
            }
        }
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
    }

}
