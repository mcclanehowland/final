import java.awt.Graphics;
import java.awt.Color;

public class Monster extends Character {
    public Monster(int x,int y,String text,String type) {
        super(x,y,text,type,"monster.png");
    }
    public void draw(Graphics g) {
        super.draw(g);
        if(talking) {
            talk(g);
        }
    }
}
