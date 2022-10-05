import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Game {
    private Screen screen;
    private Progress progress=new Progress();
    private Arena arena = new Arena(40,20,progress);

    private Health health= new Health();
    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 21);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.screen.setCursorPosition(null); // we don't need a cursor
            this.screen.startScreen(); // screens must be started
            this.screen.doResizeIfNecessary(); // resize screen if necessary
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics(), progress, health);
        screen.refresh();
    }
    public void run() throws IOException, InterruptedException {
        while(true) {
            draw();
            gameCheck();
            KeyStroke key = screen.readInput();
            if (key.getKeyType()==KeyType.EOF){
                break;
            }
            processKey(key);
        }
    }
    private void processKey(com.googlecode.lanterna.input.KeyStroke key) throws java.io.IOException{
        if(key.getKeyType()==KeyType.Character && key.getCharacter()=='q')
            this.screen.close();
        arena.processKey(key);
    }
    private void gameCheck() throws java.io.IOException, InterruptedException {
        if(progress.win()){
            this.screen.close();
            System.out.println("CONGRATULATIONS! YOU WON! ");
            TimeUnit.SECONDS.sleep(3);
        }
        else if(health.lose()){
            this.screen.close();
            System.out.println("YOU RAN OUT OF HEALTH!");
            TimeUnit.SECONDS.sleep(3);
        }
    }

}
