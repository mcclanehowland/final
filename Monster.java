import java.awt.Graphics;
import java.awt.Color;

public class Monster extends Character {
    public Monster(int x,int y,String text,String type) {
        super(x,y,text,type);
    }
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.blue);
        g.fillRect(x,y,size,size);
        if(talking) {
            talk(g);
        }
    }
}
