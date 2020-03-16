package org.academiadecodigo.mapeditor;

import org.academiadecodigo.mapeditor.cell.CellPlayer;
import org.academiadecodigo.mapeditor.grid.WorkingGrid;

public class MapEditor {

    private WorkingGrid grid;
    private CellPlayer cellplayer;

    public MapEditor(int col, int row) {

        grid = new WorkingGrid(col, row);
        cellplayer = new CellPlayer(grid);
    }

    public void init() {
        cellplayer.start();
    }
}
