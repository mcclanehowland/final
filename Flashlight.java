import java.awt.Graphics;
import java.awt.Color;

public class Flashlight extends Item {
    public Flashlight(int x,int y) {
        super(x,y,"flashlight");
    }
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(super.getX(),super.getY(),50,50);
    }
}
