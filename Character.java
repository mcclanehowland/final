import java.awt.Graphics;
import java.awt.Color;

public class Character {
    int x,y;
    public Character(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x,y,50,50);
        g.setColor(Color.green);
        g.fillRect(x+10,y+10,30,30);
    }
    public void move(int dX,int dY) {
        x += dX;
        y += dY;
    }
}
