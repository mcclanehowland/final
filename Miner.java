import java.awt.Graphics;
import java.awt.Color;
public class Miner extends Character {
    int size = 50;
    public Miner(int x,int y) {
        super(x,y);
    }
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x,y,size,size);
        g.setColor(Color.green);
        g.fillRect(x+size/10,y+size/10,4*size/5,4*size/5);
    }
}
