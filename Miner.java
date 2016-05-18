import java.awt.Graphics;
import java.awt.Color;
public class Miner extends Character {
    int size = 50;
    public Miner(int x,int y,String text) {
        super(x,y,text,"miner");
    }
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.blue);
        g.fillRect(x,y,size,size);
        g.setColor(Color.green);
        g.fillRect(x+size/10,y+size/10,4*size/5,4*size/5);
    }
    public void talk(Graphics g) {
        super.talk(g);
        g.setColor(Color.black);
    }

}
