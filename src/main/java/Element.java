import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    Position position;
    public Element(int x, int y) {
        this.position = new Position(x,y);
    }
    abstract void draw(TextGraphics graphics);
    Position getPosition(){
        return position;
    }
}
