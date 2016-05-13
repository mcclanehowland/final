import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public abstract class Item {
    int x,y,size;
    private String type;
    boolean obtained;
    private BufferedImage image;
    public Item(int x,int y,int size,String type,String imageName) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.type = type;
        try {
            image = ImageIO.read(new File(imageName));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getType() {
        return type;
    }
    public void drawImage(Graphics g) {
        g.drawImage(image,x,y,null);
    }
    public abstract void draw(Graphics g);
    public abstract void draw(Graphics g,int x,int y);
}

