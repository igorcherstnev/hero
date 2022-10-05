import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Health {
    private int amount;
    public Health(){
        amount=3;
    }
    public int getAmount() {
        return amount;
    }
    public void draw(TextGraphics graphics,int width, int height){
        graphics.setForegroundColor(TextColor.Factory.fromString("#E30000"));
        String s= String.format("HEALTH:%d",amount);
        graphics.putString(new TerminalPosition(0,height),s );
    }
    public boolean lose(){
        if (amount==0) return true;
        return false;
    }
    public void touchedMonster(){
        amount--;
    }
}
