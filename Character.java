import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class Character {
    int x,y,size;
    String text;
    Level currentLevel;
    BufferedImage image;
    boolean talking;
    public Character(int x, int y,String text) {
        this.x = x;
        this.y = y;
        this.text = text;
        size = 50;
    }
    public void draw(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x,y,size,size);
        g.setColor(Color.green);
        g.fillRect(x+10,y+10,30,30);
        if(talking) {
            talk(g);
        }
    }
    public void drawImage(Graphics g, String imageName) {
        try {
            image = ImageIO.read(new File(imageName));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image,x,y,null);
    }
    public void drawText(String text, Font font, Graphics g, int width, int startX, int startY)
    {
        String[] words = text.split(" ");//String method that splits a String phrase into words, since " " is passed in
        int i = 0;//Count integer
        while (i < words.length)
        {
            String currentLine = words[i++];//String that holds the characters that will be printed on the current line
            while (( i < words.length ) && (g.getFontMetrics(font).stringWidth(currentLine + " " + words[i]) < width))//While loop that runs while the pixel width of the string is less than the width passed in
            {
            currentLine = currentLine + " " + words[i];//Adds as many words as fit onto the line
            i++;
            }
            g.drawString(currentLine, startX, startY);//Draws the line
            int lineHeight = g.getFontMetrics(font).getHeight();//Gets the height of a standard line of text in the passed in font
            startY = startY + lineHeight;//Increases the y variable to draw on the next line
        }
    }
    public void talk(Graphics g) {
        g.setColor(Color.white);
        g.fill3DRect(x+size/2,y-50,200,50,true);
        g.setColor(Color.red);
        g.drawRoundRect(x+size/2,y-50,200,50,20,20);
        g.setColor(Color.black);
        Font z = new Font("ZapfDingbats", Font.PLAIN,18);            
        drawText(text,z,g,300,x+size/2+10,y-30);
    }
    public void move(int dX,int dY) {
        //check the collisions 
        boolean move = true;
        for(Obstacle each : currentLevel.obstacles) {
            if(x+dX+size > each.x && x+dX < each.x+each.size && y+dY+size > each.y && y+dY < each.y + each.size) {
                move = false;
            }
        }
        if(move && x >= 0 && y+dY+size < 600 && y >= 0 && (x <= 750 || currentLevel.levelCondition())) {
            x += dX;
            y += dY;
        }
        if(x <= 0) {
            x = 0;
        }
        if(y <= 0) {
            y = 0;
        }
        if(y >= 500) {
            y = 500;
        }
        if(x >= 750 && !currentLevel.levelCondition()) {
            x = 750;
        }
    }
}
