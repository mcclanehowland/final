import java.awt.Graphics;
import java.awt.Color;

public class Character {
    int x,y,size;
    Level currentLevel;
    public Character(int x, int y) {
        this.x = x;
        this.y = y;
        size = 50;
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x,y,size,size);
        g.setColor(Color.green);
        g.fillRect(x+10,y+10,30,30);
    }
    public void move(int dX,int dY) {
        //check the collisions first
        boolean move = true;
        for(Obstacle each : currentLevel.obstacles) {
            if(x+dX+size > each.x && x+dX < each.x+each.size && y+dY+size > each.y && y+dY < each.y + each.size) {
                move = false;
            }
        }
        if(move && x >= 0 && y+dY+size <= 600 && y >= 0) {
            x += dX;
            y += dY;
        }
        if(x <= 0) {
            x = 0;
        }
        if(y <= 0) {
            y = 0;
        }
        if(y >= 600) {
            y = 600;
        }
    }
}
