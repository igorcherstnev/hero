import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    public Monster(int x, int y){
        super(x,y);
    }
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#E30000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(),position.getY()), "W");
    }
    public Position move(){
        Random random= new Random();
        int aux= random.nextInt(4);
        switch(aux){
            case(0):{
                return new Position(position.getX(),position.getY()-1 ) ;
            }
            case(1):{
                return new Position(position.getX()+1, position.getY());
            }
            case(2):{
                return new Position(position.getX(),position.getY()+1 ) ;
            }
            case(3):{
                return new Position(position.getX()-1, position.getY());
            }
        }
        return null;
    }
    public void moveMonster(Position position){
        this.position=position;
    }

}
