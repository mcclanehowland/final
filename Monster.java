import java.awt.Graphics;
import java.awt.Color;

public class Monster extends Character {
    public Monster(int x,int y) {
        super(x,y);
    }
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x,y,size,size);
    }
}
