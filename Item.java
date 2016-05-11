import java.awt.Graphics;

public abstract class Item {
    int x,y,size;
    private String type;
    boolean obtained;
    public Item(int x,int y,int size,String type) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public abstract void draw(Graphics g);
    public abstract void draw(Graphics g,int x,int y);
}

