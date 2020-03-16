package org.academiadecodigo.mapeditor.cell;

import org.academiadecodigo.mapeditor.File;
import org.academiadecodigo.mapeditor.grid.WorkingGrid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.IOException;

public class CellPlayer extends Cell implements KeyboardHandler {

    private WorkingGrid grid;
    private Direction currentDirection;

    private Keyboard keyboard;

    public CellPlayer(WorkingGrid grid){

        super(0, 0, grid.getCellSize());
        this.grid = grid;
        setColor(Color.PINK);

        keyboard = new Keyboard(this);
    }

    public void addEvent(Keyboard keyboard, int key, KeyboardEventType type){

        KeyboardEvent e = new KeyboardEvent();
        e.setKey(key);
        e.setKeyboardEventType(type);
        keyboard.addEventListener(e);

    }

    public void start(){

        addEvent(keyboard, KeyboardEvent.KEY_LEFT, KeyboardEventType.KEY_PRESSED);
        addEvent(keyboard, KeyboardEvent.KEY_RIGHT, KeyboardEventType.KEY_PRESSED);
        addEvent(keyboard, KeyboardEvent.KEY_UP, KeyboardEventType.KEY_PRESSED);
        addEvent(keyboard, KeyboardEvent.KEY_DOWN, KeyboardEventType.KEY_PRESSED);
        addEvent(keyboard, KeyboardEvent.KEY_SPACE, KeyboardEventType.KEY_PRESSED);
        addEvent(keyboard, KeyboardEvent.KEY_C, KeyboardEventType.KEY_PRESSED);
        addEvent(keyboard, KeyboardEvent.KEY_S, KeyboardEventType.KEY_PRESSED);
        addEvent(keyboard, KeyboardEvent.KEY_L, KeyboardEventType.KEY_PRESSED);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent){

        Cell cell = null;

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_SPACE:
                cell = this.grid.getCell(this.col, this.row);

                if (!(cell.isColored())) {
                    cell.setColor(Color.BLACK);
                    return;
                }

                cell.clean();
                break;
            case KeyboardEvent.KEY_C:
                grid.cleanAll();
                break;
            case KeyboardEvent.KEY_S:
                File newFile = new File();

                String stringToLoad = grid.writeString();
                try {
                    newFile.save("Pattern.txt", stringToLoad);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case KeyboardEvent.KEY_L:

                File newFile2 = new File();
                try {
                    String stringRead = newFile2.load("Pattern.txt");
                    grid.linesToString(stringRead);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                currentDirection = Direction.LEFT;
                break;
            case KeyboardEvent.KEY_RIGHT:
                currentDirection = Direction.RIGHT;
                break;
            case KeyboardEvent.KEY_UP:
                currentDirection = Direction.UP;
                break;
            case KeyboardEvent.KEY_DOWN:
                currentDirection = Direction.DOWN;
                break;
        }

        move();
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void move() {

        this.moveInDirection(currentDirection, this.cellSize);
    }

    public void moveInDirection(Direction direction, int distance){

        switch (direction) {

            case UP:
                moveUp(distance);
                break;
            case DOWN:
                moveDown(distance);
                break;
            case LEFT:
                moveLeft(distance);
                break;
            case RIGHT:
                moveRight(distance);
                break;
        }
    }

    public void moveUp(int distance){

        if(this.getRow() == 0)
            return;

        this.rectangle.translate(0,- distance);
        this.row = this.getRow() -1;
    }

    public void moveDown(int distance){

        if(this.getRow() == grid.getRows() - 1)
            return;

        this.rectangle.translate(0,distance);
        this.row = this.getRow() + 1;
    }

    public void moveLeft(int distance){

        if(this.getCol() == 0)
            return;

        this.rectangle.translate(- distance,0);
        this.col = this.getCol() - 1;
    }

    public void moveRight(int distance){

        if(this.getCol() == grid.getCols() - 1)
            return;

        this.rectangle.translate(distance,0);
        this.col = this.getCol() + 1;
    }
}
