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
    private boolean moveUp,moveDown,moveRight,moveLeft;
    private boolean mainMenu = true;
    private MainCharacter main;
    private Character otherCharacter;
    private int level = 1;
    private boolean levelOver = false;
    ArrayList<Character> characters;
    Level currentLevel;

	public Screen() {
        //key and mouse listener things 
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
        //instantiate levels and character
        main = new MainCharacter(0,300);
        currentLevel = new Level(main);
        main.currentLevel = currentLevel;
        characters = new ArrayList<Character>();
        characters.add(new Miner(0,100));
	}
    public Dimension getPreferredSize() {
        return new Dimension(800,700);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		if(bufferedImage==null) 
            bufferedImage = (BufferedImage)(createImage(getWidth(),getHeight())); 
		Graphics gBuff = bufferedImage.createGraphics(); 
		gBuff.setColor(Color.WHITE);
		gBuff.fillRect(0, 0, 800, 600);
        
        //draw the current level
        currentLevel.draw(gBuff);
        
        //draw the characters & check if they should be talking to the character
        for(Character each : characters) {
            each.draw(gBuff);
            if(each.x+each.size >= main.x && each.x <= main.x+main.size && each.y <= main.y+main.size && each.y+each.size >= main.y) {
                each.talking = true;
            }
            else {
                each.talking = false;
            }
        }
        main.draw(gBuff);

        if(mainMenu) { //draw the start screen
            gBuff.setColor(Color.gray);
            gBuff.fillRect(0,0,800,600);
            gBuff.setColor(Color.black);
            gBuff.drawString("Cave Explorer",350,300);
            gBuff.drawString("Press Space to Begin",350,350);
        }
        if(main.x > 800) { //if the player reached the end of the screen, level up
            levelUp();
        }
        if(levelOver) { //the level cutscenes
            gBuff.setColor(Color.gray);
            gBuff.fillRect(0,0,800,600);
            gBuff.setColor(Color.black);
            gBuff.drawString("Cave Explorer: Level "+level,350,300);
            gBuff.drawString("Press Space to Continue",350,350);
        }
        
		g.drawImage(bufferedImage, 0, 0, null);
	}
    public void levelUp() {
        //update the current level
        level++;
        levelOver = true;
        currentLevel = new Level(main);
        //get rid of all the other characters and add new characters
        characters.clear();
        //level character code
        if(level >= 2) {
            for(int r = 0;r < currentLevel.path.length;r++) {
                if(currentLevel.path[r][0] >= 500) {
                    characters.add(new Monster(currentLevel.path[r][0],currentLevel.path[r][1]));
                    break;
                }
            }
        }
        //reset the main character
        main.x = 0;
        main.y = 350;
        //update the character's stored current level
        main.currentLevel = currentLevel;
    }
    public void animate() {
        //sleep, then go through the movement logic
        int counter = 0;
        while(true) {
            counter++;
            sleep(50);
            if(!mainMenu && !levelOver) { //don't move if it is in a cutscene or the game hasn't started
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
                if(counter % 20 == 0) { //move the monsters and characters
                    for(Character each : characters) {
                        each.currentLevel = currentLevel;
                        int dx = (int)(Math.random()*5+5);
                        int dy = (int)(Math.random()*5+5);
                        if(Math.random() > 0.5) 
                            dx *=  -1;
                        if(Math.random() > 0.5) 
                            dy *= -1;
                        each.move(dx,dy);
                    }
                }

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
        //System.out.println(e.getKeyCode());
        switch(e.getKeyCode()) {
            case 32: 
                if(mainMenu) {
                    levelOver = true;
                }
                else {
                    levelOver = false;
                }
                mainMenu = false;
                break;
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
                if(!levelOver) {
                    levelUp();
                }
                break;
        }
        repaint();

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
