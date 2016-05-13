import java.awt.Graphics;
import java.awt.Color;

public class Sword extends Item {
    public Sword(int x,int y) {
        super(x,y,50,"sword","sword.png");
    }
    public void draw(Graphics g) {
        drawImage(g);
    }
    public void draw(Graphics g,int x,int y) {
        this.x = x;
        this.y = y;
        drawImage(g);
    }
}
