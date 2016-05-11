import java.awt.Graphics;
import java.awt.Color;

public class Flashlight extends Item {
    public Flashlight(int x,int y) {
        super(x,y,50,"flashlight");
    }
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(x,y,size,size);
    }
    public void draw(Graphics g,int x,int y) {
        g.setColor(Color.yellow);
        g.fillOval(x,y,size,size);
    }
}
