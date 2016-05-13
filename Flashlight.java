import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Flashlight extends Item {
    public Flashlight(int x,int y) {
        super(x,y,50,"flashlight","Flashlight.png");
    }
    public void draw(Graphics g) {
        drawImage(g);
    }
    public void draw(Graphics g,int x,int y) {
        this.x = x;
        this.y = y;
        drawImage(g);
    }
}
