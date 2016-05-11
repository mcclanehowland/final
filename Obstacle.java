import java.awt.Graphics;
import java.awt.Color;
 
public class Obstacle {
    int x,y,size;
    public Obstacle(int x,int y) {
        this.x = x;
        this.y = y;
        size = 50; 
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x,y,size,size);
    }
}
