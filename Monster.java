import java.awt.Graphics;
import java.awt.Color;

public class Monster extends Character {
    public Monster(int x,int y) {
        super(x,y);
    }
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.blue);
        g.fillRect(x,y,size,size);
        if(talking) {
            talk(g);
        }
    }
    public void talk(Graphics g) {
        super.talk(g);
        g.drawString("I'm a monster,",x+size/2+10,y-30);
        g.drawString("kill me to move on!",x+size/2+10,y-20);
    }

}
