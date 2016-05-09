import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;

public class Character {
    int x,y,size;
    Level currentLevel;
    BufferedImage obscure;
    public Character(int x, int y) {
        this.x = x;
        this.y = y;
        size = 50;
        try {
            obscure = ImageIO.read(new File("obscure.png"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x,y,size,size);
        g.setColor(Color.green);
        g.fillRect(x+10,y+10,30,30);
        //g.drawImage(obscure,x-900,y-800,null);
    }
    public void move(int dX,int dY) {
        //check the collisions 
        boolean move = true;
        for(Obstacle each : currentLevel.obstacles) {
            if(x+dX+size > each.x && x+dX < each.x+each.size && y+dY+size > each.y && y+dY < each.y + each.size) {
                move = false;
            }
        }
        if(move && x >= 0 && y+dY+size <= 600 && y >= 0) {
            x += dX;
            y += dY;
        }
        if(x <= 0) {
            x = 0;
        }
        if(y <= 0) {
            y = 0;
        }
        if(y >= 600) {
            y = 600;
        }
    }
}
