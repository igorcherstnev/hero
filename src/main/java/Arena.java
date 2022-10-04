import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private int width;
    private int height;
    private Hero hero= new Hero(5,5);
    private List<Wall> walls;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls=createWalls();
    }
    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowDown: {
                if(canHeroMove(hero.moveDown())) {
                    hero.moveHero(hero.moveDown());
                }
                break;
            }
            case ArrowUp: {
                if (canHeroMove(hero.moveUp())){
                    hero.moveHero(hero.moveUp());
                }
                break;
            }
            case ArrowLeft: {
                if(canHeroMove(hero.moveLeft())) {
                    hero.moveHero(hero.moveLeft());
                }
                break;
            }
            case ArrowRight: {
                if(canHeroMove(hero.moveRight()))
                    hero.moveHero(hero.moveRight());
            }
        }
    }
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for(Wall wall: walls)
            wall.draw(graphics);
    }
    public boolean canHeroMove(Position position){
        for (Wall wall: walls)
            if(wall.getPosition().equals(position))
                return false;
        return true;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < this.width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, this.height - 1));
        }
        for (int r = 1; r < this.height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(this.width - 1, r));
        }
        return walls;
    }

}


