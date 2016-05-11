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
        if(talking) {
            talk(g);
        }
    }
    public void talk(Graphics g) {
        super.talk(g);
        g.setColor(Color.black);
        g.drawString("Search the cave and",x+size+10,y-40);
        g.drawString("find the flashlight",x+size+10,y-30);
    }

}
