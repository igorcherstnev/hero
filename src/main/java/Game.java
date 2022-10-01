import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Hero hero= new Hero(10,10);
    public Game() {
        try {
            Terminal terminal = new
                    DefaultTerminalFactory().createTerminal();
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
        hero.draw(screen);
        screen.refresh();
    }
    public void run() throws IOException{
        while(true) {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType()==KeyType.EOF){
                break;
            }
            processKey(key);
        }
    }
    private void processKey(com.googlecode.lanterna.input.KeyStroke key) throws java.io.IOException{
        switch (key.getKeyType()){
            case Character: {
                if (key.getCharacter() == 'q'){
                    this.screen.close();
                }
            }
            case ArrowDown:{
                hero.moveDown();
                break;
            }
            case ArrowUp:{
                hero.moveUp();
                break;
            }
            case ArrowLeft :{
                hero.moveLeft();
                break;
            }
            case ArrowRight :{
                hero.moveRight();
            }
        }
    }
}
