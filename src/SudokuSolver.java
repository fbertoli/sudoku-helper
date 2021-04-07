import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class SudokuSolver {
    // ATTRIBUTES
    public Sudoku sudoku = new Sudoku();
    public ArrayList<Technique> techniques = new ArrayList<Technique>();
    public ArrayList<Move> moveSequence = new ArrayList<Move>();

    // CONSTRUCTORS
    public SudokuSolver(ArrayList<Technique> techniqueList, String file_path) throws IOException {
        techniques.addAll(techniqueList);

        // read file and add values
        String[] arr = Files.lines(Paths.get(file_path)).toArray(String[]::new);
        int row = 0;
        for (String s : arr) {
            int col = 0;
            for (Character c : s.toCharArray()) {
                try {
                    int value = Integer.parseInt(String.valueOf(c));
                    assert (value >= 0 & value <= 9) : String.format("%d not acceptable as value", value);
                    if (value > 0) {
                        sudoku.addValue(sudoku.getCell(row, col), value);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(String.format("%s not acceptable as value", c));
                    throw e;
                }
                col++;
            }
            row++;
        }
    }

    // METHODS
    public void solve() {
        while (! sudoku.isSolved()) {
            boolean foundValue = false;
            for (Technique tech : techniques) {
                tech.applyToSudoku(sudoku);
                if (tech.foundNewMove()) {
                    // get next move, add value, save move and break
                    Move move = tech.getNextMove();
                    sudoku.addValue(move.cell, move.value);
                    moveSequence.add(new Move(move));
                    tech.reset();
                    assert sudoku.isValid();
                    foundValue = true;
                    break;
                }
            }
            if (! foundValue) {
                System.out.println("Could not find a new value with available heuristics");
                return;
            }
        }
    }

    public String tracebackToString() {
        StringBuilder s = new StringBuilder();
        for (Move move : moveSequence) {
            s.append(move.toString());
            s.append("\n");
        }
        return s.toString();
    }

    // TESTS

    // MAIN
    public static void main(String[] args) throws IOException {
        ArrayList<Technique> techniques = new ArrayList<>();
        techniques.add(new TechniqueOpenSingle());
        techniques.add(new TechniqueHiddenSingle());
        SudokuSolver solver = new SudokuSolver(
                techniques,
                "/Users/fbertoli/Projects/sudoku-solver/data/easy"
        );
        System.out.println(solver.sudoku);
        solver.solve();
        System.out.println();
        System.out.println(solver.tracebackToString());
        System.out.println(String.format("solution is %svalid", solver.sudoku.isValid() ? "" : "not "));
        System.out.println(solver.sudoku);
    }
}
