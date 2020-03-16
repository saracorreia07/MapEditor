package org.academiadecodigo.mapeditor.cell;

import org.academiadecodigo.mapeditor.grid.WorkingGrid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


public class Cell {

    private boolean colored = false;
    int col;
    int row;
    int cellSize;
    Rectangle rectangle;

    public Cell(int col, int row, int cellSize){

        this.col = col;
        this.row = row;
        this.cellSize = cellSize;

        init();
    }

    public void init(){

        this.rectangle = new Rectangle(this.col * this.cellSize + WorkingGrid.PADDING, row * this.cellSize + WorkingGrid.PADDING, this.cellSize, this.cellSize);
        this.rectangle.draw();

    }

    public int getCol(){

        return this.col;
    }

    public int getRow(){

        return this.row;
    }

    public Rectangle getRectangle(){

        return this.rectangle;
    }

    public boolean isColored() {

        return this.colored;
    }

    public void setColor(Color color){

        this.rectangle.setColor(color);
        this.rectangle.fill();
        this.colored = true;
    }

    public void clean(){

        this.rectangle.draw();
        this.colored = false;
    }
}
