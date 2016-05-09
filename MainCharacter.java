import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;

public class MainCharacter extends Character {
    private BufferedImage obscure;
    public MainCharacter(int x,int y) {
        super(x,y);
        try {
            obscure = ImageIO.read(new File("obscure.png"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(obscure,super.x-900,super.y-800,null);
    }
}



