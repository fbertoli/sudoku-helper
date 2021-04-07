import java.util.ArrayList;

public class TechniqueOpenSingle extends Technique {
    // CONSTRUCTORS
    public TechniqueOpenSingle() {
        super("open single");
    }

    // METHODS
    @Override
    public void applyToSudoku(Sudoku sudoku) {
        for (ArrayList<Cell> row : sudoku.rows) {
            for (Cell cell : row) {
                ArrayList<Integer> candidates = cell.getCandidates();
                if ((! cell.hasValue()) & candidates.size() == 1) {
                    nextMove = new Move(cell, candidates.get(0), this.name);
                    foundNewMove = true;
                }
            }
        }
    }
}
