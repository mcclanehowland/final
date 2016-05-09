import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Screen extends JPanel implements MouseListener, KeyListener {

	private BufferedImage bufferedImage;
    private boolean moveUp,moveDown,moveRight,moveLeft; //movement booleans
    private Character main; //main character
    Level currentLevel; //level that is loaded on the screen

	public Screen() {
        //key and mouse listener things 
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
        //instantiate levels and character
        currentLevel = new Level(100);
        main = new Character(0,300);
        main.currentLevel = currentLevel;
	}
    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		if(bufferedImage==null) //useless
        bufferedImage = (BufferedImage)(createImage(getWidth(),getHeight())); 
		Graphics gBuff = bufferedImage.createGraphics(); 
		gBuff.setColor(Color.WHITE);
		gBuff.fillRect(0, 0, 800, 600);
		
        if(main.x > 800) { //if the player reached the end of the screen, level up
            levelUp();
        }
        currentLevel.draw(gBuff);
        main.draw(gBuff);

		gBuff.setColor(Color.green);
		g.drawImage(bufferedImage, 0, 0, null);
	}
    public void levelUp() {
        //update the current level
        currentLevel = new Level(currentLevel.difficulty+10);
        //reset the main character
        main.x = 0;
        main.y = 350;
        //update the character's stored current level
        main.currentLevel = currentLevel;
    }
    public void animate() {
        //sleep, then go through the movement logic
        while(true) {
            sleep(60);
            if(moveLeft) {
                main.move(-10,0);
            }
            else if(moveRight) {
                main.move(10,0);
            }
            if(moveUp) {
                main.move(0,-10);
            }
            else if(moveDown) {
                main.move(0,10);
            }
            repaint();
        }
    }
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} 
		catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
    //movement booleans, and the cheat key
	public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case 38: //up arrow
                moveUp = true;
                break;
            case 39: //right arrow
                moveRight = true;
                break;
            case 40: //down arrow
                moveDown = true;
                break;
            case 37:
                moveLeft = true;
                break;
            case 80:
                levelUp();
                break;
        }

	}
	public void keyReleased(KeyEvent evt) { 
         switch(evt.getKeyCode()) {
            case 38: //up arrow
                moveUp = false;
                break;
            case 39: //right arrow
                moveRight = false;
                break;
            case 40: //down arrow
                moveDown = false;
                break;
            case 37:
                moveLeft = false;
                break;
        }       
    }
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void keyTyped(KeyEvent evt){}
}
