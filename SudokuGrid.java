import java.awt.Point;
public class SudokuGrid {
    private int[][] grid;
    private int rEmpty, cEmpty;

    public SudokuGrid() {
        rEmpty = 0;
        cEmpty = 0;
        this.grid = new int[11][11];
    }
    public int getrEmpty(){
        return rEmpty;
    }
    public int getcEmpty(){
        return cEmpty;
    }
    public void setEmpty(int rEmpty,int cEmpty){
        this.rEmpty=rEmpty;
        this.cEmpty=cEmpty;
        
    }
    public int getDigit(int r,int c){
        return grid[r][c];
    }
    public Point findEmptyCell() {
        for (int row = 1; row <= 9; row++) {
            for (int column = 1; column <= 9; column++) {
                if (grid[row][column] == 0) {
                    rEmpty = row;
                    cEmpty = column;
                    return new Point(row, column);
                }
            }
        }

        return null;
    }


    public void fillCell(int r, int c, int d) {
        grid[r][c] = d;
    }

    public boolean givesCon(int r, int c, int d) {
        if (!rowCon(r, d) && !colCon(c, d) && !boxCon(r, c, d)) {
            return false;
        }
        return true;
    }

    private boolean rowCon(int r, int d) {
        for (int i = 1; i <= 9; i++) {
            if (grid[r][i] == d) {
                return true;
            }
        }
        return false;
    }

    private boolean colCon(int c, int d) {
        for (int i = 1; i <= 9; i++) {
            if (grid[i][c] == d) {
                return true;
            }
        }
        return false;
    }

    private boolean boxCon(int r, int c, int d) {
        int r2 = r - 1;
        int c2 = c - 1;
        r2 /= 3;
        c2 /= 3;
        r2 *= 3;
        c2 *= 3;
        r2++;
        c2++;
        for (int row = r2; row <= r2 + 2; row++) {
            for (int column = c2; column <= c2 + 2; column++) {
                if (grid[row][column] == d) {
                    return true;
                }
            }
        }
        return false;
    }
}
