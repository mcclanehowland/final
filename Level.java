import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Level {
    int difficulty;
    ArrayList<Obstacle> obstacles;
    int[][] path;
    public Level(int difficulty) {
        this.difficulty = difficulty;
        obstacles = new ArrayList<Obstacle>();
        path = new int[150][2];
        path[0] = new int[]{0,350};
        int i = 1;
        int x = 0;
        while(i < path.length) {
            if(Math.random() > 0.5) {
                path[i] = new int[] {path[i-1][0]+50,path[i-1][1]};
            }
            else {
                if(Math.random() > 0.5 && path[i-1][1] < 550) {
                    path[i] = new int[] {path[i-1][0],path[i-1][1]+50};
                }
                else if(path[i-1][1] > 0){
                    path[i] = new int[] {path[i-1][0],path[i-1][1]-50};
                }
            }
            x = path[i][0];
            if(x > 800) {
                path[i][0] = path[i][0]%600+50;
            }
            i++;
        }
        while(obstacles.size() < difficulty) {
            boolean toAdd = true;
            int tempX = (int)(Math.random()*650+100);
            int tempY = (int)(Math.random()*600);
            for(int j = 0;j < path.length;j++) {
                if(tempX+50 > path[j][0] && tempX < path[j][0]+60 && tempY+50 > path[j][1] && tempY < path[j][1]+60) {
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
        for(Obstacle each : obstacles) {
            each.draw(g);
        }
        g.setColor(Color.blue);
        for(int i = 0;i < path.length;i++) {
            g.drawRect(path[i][0],path[i][1],50,50);
        }
    }

}

