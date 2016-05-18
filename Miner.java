import java.awt.Graphics;
import java.awt.Color;
public class Miner extends Character {
    int size = 50;
    public Miner(int x,int y,String text) {
        super(x,y,text,"miner","miner.png");
    }
    public void draw(Graphics g) {
        super.draw(g);
    }
    public void talk(Graphics g) {
        super.talk(g);
        g.setColor(Color.black);
    }

}
