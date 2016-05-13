import java.awt.Graphics;
import java.awt.Color;

public class Sword extends Item {
    public Sword(int x,int y) {
        super(x,y,50,"sword");
    }
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x,y,size,size);
    }
    public void draw(Graphics g,int x,int y) {
        g.setColor(Color.red);
        g.fillOval(x,y,size,size);
    }
}
