import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MainCharacter extends Character {
    URL swordSound;
    private BufferedImage obscure;
    ArrayList<Item> inventory;
    boolean flashlight;
    boolean sword;
    public MainCharacter(int x,int y) {
        super(x,y,"","main","maincharacter.png");
        try {
            obscure = ImageIO.read(new File("obscure.png"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        inventory = new ArrayList<Item>();
    }
    public void playSound(String filename) {
        try
        {
            URL url = this.getClass().getClassLoader().getResource(filename);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        }
        catch (Exception exc)
        {
            //exc.printStackTrace(System.out);
        }
    }
    public void draw(Graphics g) {
        super.draw(g);
        if(!flashlight && currentLevel.level == 1) {
            g.drawImage(obscure,super.x-900,super.y-800,null);
        }
        //inventory box
        g.setColor(Color.gray);
        g.fillRect(0,550,800,50);
        g.setColor(Color.red);
        g.drawRect(0,550,799,49);
        int x = 50;
        int y = 555;
        for(Item each : inventory) {
            each.draw(g,x,y);
            if(currentLevel.level >= 2 && each.getType().equals("sword")) {
                each.draw(g,super.x+25,super.y);
                if(each.getType() == "sword") {
                    sword = true;
                }
            }
                
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



