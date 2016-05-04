import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
 
public class Obstacle {
    int x,y,size;
    private BufferedImage tree;
    public Obstacle(int x,int y) {
        this.x = x;
        this.y = y;
        size = 50; 
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x,y,size,size);
        //g.drawImage(tree,x,y,null);
    }
}

