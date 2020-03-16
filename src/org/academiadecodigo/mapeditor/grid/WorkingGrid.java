package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.mapeditor.cell.Cell;
import org.academiadecodigo.simplegraphics.graphics.Color;


public class WorkingGrid{

    public static final int PADDING = 10;

    private int cellSize = 20;
    private int cols;
    private int rows;
    private Cell cell;

    private Cell[][] cells;


    public WorkingGrid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;

        init();
    }

    public void init(){

        createCells();
    }

    public int getCellSize() {

        return this.cellSize;
    }

    public int getCols() {

        return this.cols;
    }

    public int getRows() {

        return this.rows;
    }


    public void createCells(){

        this.cells= new Cell[this.getCols()][this.getRows()];

        for (int i = 0; i < this.getCols(); i++) {
            for (int j = 0; j < this.getRows(); j++) {

                this.cells[j][i]= new Cell(j, i, this.cellSize);

            }
        }
    }

    public Cell getCell(int col, int row){

        return this.cells[col][row];
    }

    public void cleanAll(){

        for (int i = 0; i < this.getCols(); i++) {
            for (int j = 0; j < this.getRows(); j++) {

                cells[j][i].clean();

            }
        }
    }

    public String writeString() {

        String newString = "";
        for (int i = 0; i < this.getCols(); i++) {
            for (int j = 0; j < this.getRows(); j++) {

                if (cells[j][i].isColored()) {

                    newString += "1";
                    continue;
                }
                newString += "0";
            }

            newString += "\n";
        }
        return newString;
    }

    public void linesToString(String file){

        String[] newString = file.split("\n");

        for (int i = 0; i < this.getCols(); i++) {
            for (int j = 0; j < this.getRows(); j++) {

                if(newString[j].charAt(i) == '1'){
                    cells[i][j].setColor(Color.BLACK);
                }
            }
        }
    }
}
