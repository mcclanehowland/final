import java.awt.Graphics;
import java.awt.Color;

public class Obstacle {
    int x,y;
    public Obstacle(int x,int y) {
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x,y,50,50);
    }
}

