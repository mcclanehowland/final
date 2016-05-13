import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class MainCharacter extends Character {
    private BufferedImage obscure;
    private ArrayList<Item> inventory;
    private boolean flashlight;
    public MainCharacter(int x,int y) {
        super(x,y,"");
        try {
            obscure = ImageIO.read(new File("obscure.png"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        inventory = new ArrayList<Item>();
    }
    public void draw(Graphics g) {
        super.draw(g);
        if(!flashlight && currentLevel.level == 1) {
            g.drawImage(obscure,super.x-900,super.y-800,null);
        }
        //inventory box
        g.setColor(Color.white);
        g.fillRect(0,550,800,50);
        g.setColor(Color.red);
        g.drawRoundRect(0,550,800,50,50,50);
        int x = 50;
        int y = 555;
        for(Item each : inventory) {
            each.draw(g,x,y);
            x += 60;
        }

    }
    public void addItem(Item item) {
        inventory.add(item);
        if(item.getType().equals("flashlight")) {
            flashlight = true;
        }
    }
}



