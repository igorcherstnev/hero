import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int x=10;
    private int y=10;
    public Hero(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void moveUp(){
        this.y--;
    }
    public void moveDown(){
        this.y++;
    }
    public void moveLeft(){
        this.x--;
    }
    public void moveRight(){
        this.x++;
    }
    public void draw(Screen screen){
        screen.setCharacter(this.x, this.y, TextCharacter.fromCharacter('X') [0]);
    }

}