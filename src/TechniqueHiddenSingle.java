import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class TechniqueHiddenSingle extends Technique {
    // CONSTRUCTORS
    public TechniqueHiddenSingle() {
        super("open single");
    }

    // METHODS
    @Override
    public void applyToSudoku(Sudoku sudoku) {
        for (int value = 1; value <= 9; value++) {
            // row
            for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
                ArrayList<Boolean> row = sudoku.valueGrids.get(value).get(rowIndex);
                if ((int) row.stream().filter(x -> x).count() == 1) {
                    nextMove = new Move(new Cell(rowIndex, row.indexOf(true)), value, this.name, "row: " + rowIndex);
                    foundNewMove = true;
                }
            }

            // col
            for (int colIndex = 0; colIndex < 9; colIndex++) {
                int count = 0;
                for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
                    count += sudoku.valueGrids.get(value).get(rowIndex).get(colIndex) ? 1 : 0;
                    if (count == 1) {
                        nextMove = new Move(new Cell(rowIndex, colIndex), value, this.name, "col: " + colIndex);
                    }
                }
                if (count == 1) {
                    foundNewMove = true;
                }
            }

            // square
            for (int squareIndex = 0; squareIndex < 9; squareIndex++) {
                int count = 0;
                for (int positionIndex = 0; positionIndex < 9; positionIndex++) {
                    count += sudoku.valueGrids.get(value).get(positionIndex).get(squareIndex) ? 1 : 0;
                    if (count == 1) {
                        this.nextMove = new Move(new Cell(Sudoku.getRowFromSquareIndexes(squareIndex, positionIndex), Sudoku.getColFromSquareIndexes(squareIndex, positionIndex)), value, this.name, "square: " + squareIndex);
                    }
                }
                if (count == 1) {
                    foundNewMove = true;
                }
            }
        }
    }
}
