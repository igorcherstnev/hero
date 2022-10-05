import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Progress {
    private int amount;
    private int goal=5;

    public Progress(){
        amount=0;
    }

    public int getGoal() {
        return goal;
    }

    public void draw(TextGraphics graphics, int width, int height){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        String s= String.format("Coins:%d/",amount);
        String t= String.format("%d",goal);
        graphics.putString(new TerminalPosition(width-10,height),s+t );
    }
    public boolean win(){
        if(amount==goal) return true;
        return false;
    }
    public void gotCoin(){
        amount++;
    }


}
