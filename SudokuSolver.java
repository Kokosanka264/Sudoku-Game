import java.awt.Point;
public class SudokuSolver {

    public SudokuGrid grid;

    public SudokuSolver(SudokuGrid grid) {
        this.grid = grid;
    }

    public boolean solve() {
        Point point = grid.findEmptyCell();
        if (point == null) {
            return true;
        }
        int x = point.x;
        int y = point.y;
        for (int i = 1; i <= 9; i++) {
            if (!grid.givesCon(x, y, i)) {
                grid.fillCell(x, y, i);
                if (solve()) {
                    return true;
                }
                grid.fillCell(x, y, 0);
               
            }
        }

        return false;
    }


    
}
