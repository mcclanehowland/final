import java.awt.Graphics;

public abstract class Item {
    int x,y;
    private String type;
    public Item(int x,int y,String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public abstract void draw(Graphics g);
}

