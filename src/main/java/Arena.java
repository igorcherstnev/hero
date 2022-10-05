import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public class Arena {
    private int width;
    private int height;
    private Hero hero= new Hero(5,5);
    private List<Wall> walls;
    private List<Coin> coins;

    private List<Monster> monsters;

    public Arena(int width, int height, Progress progress) {
        this.width = width;
        this.height = height;
        this.walls=createWalls();
        this.coins=createCoins(progress.getGoal());
        this.monsters=createMonsters();
    }
    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowDown: {
                if(canElementMove(hero.moveDown())) {
                    hero.setPrevposition(hero.getPosition());
                    hero.moveHero(hero.moveDown());
                    for (Monster monster:monsters) {
                        Position aux=monster.move();
                        if (canElementMove(aux))
                            monster.moveMonster(aux);
                    }
                }
                break;
            }
            case ArrowUp: {
                if (canElementMove(hero.moveUp())){
                    hero.setPrevposition(hero.getPosition());
                    hero.moveHero(hero.moveUp());
                    for (Monster monster:monsters) {
                        Position aux=monster.move();
                        if (canElementMove(aux))
                            monster.moveMonster(aux);
                    }
                }
                break;
            }
            case ArrowLeft: {
                if(canElementMove(hero.moveLeft())) {
                    hero.setPrevposition(hero.getPosition());
                    hero.moveHero(hero.moveLeft());
                    for (Monster monster:monsters) {
                        Position aux=monster.move();
                        if (canElementMove(aux))
                            monster.moveMonster(aux);
                    }
                }
                break;
            }
            case ArrowRight: {
                if(canElementMove(hero.moveRight())) {
                    hero.setPrevposition(hero.getPosition());
                    hero.moveHero(hero.moveRight());
                    for (Monster monster:monsters) {
                        Position aux=monster.move();
                        if (canElementMove(aux))
                            monster.moveMonster(aux);
                    }
                }
            }
        }
    }
    public void draw(TextGraphics graphics, Progress progress, Health health){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for(Wall wall: walls)
            wall.draw(graphics);
        for(Coin coin: coins)
            coin.draw(graphics);
        for(Monster monster:monsters)
            monster.draw(graphics);
        hero.draw(graphics);
        retrieveCoins(progress);
        verifyMonsterCollision( health);
        health.draw(graphics,width,height);
        progress.draw(graphics,width,height);
    }
    public boolean canElementMove(Position position){
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
    private List<Coin> createCoins(int amount) {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < amount; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1,random.nextInt(height - 2) + 1));
        return coins;
    }
    public void retrieveCoins(Progress progress){
        for(int i=0;i<coins.size();i++){
            Coin coin=coins.get(i);
            if(coin.getPosition().equals(hero.getPosition())){
                coins.remove(i);
                progress.gotCoin();
                break;
            }
        }
    }
    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1,random.nextInt(height - 2) + 1));
        return monsters;
    }
    public void verifyMonsterCollision(Health health){
        for(int i=0;i<monsters.size();i++){
            Monster monster=monsters.get(i);
            if(monster.getPosition().equals(hero.getPosition()) || monster.getPosition().equals(hero.getPrevposition())){
                monsters.remove(i);
                health.touchedMonster();
                break;
            }
        }
    }


}


