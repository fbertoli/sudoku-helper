import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sudoku {
    // ATTRIBUTES
    public ArrayList<ArrayList<Cell>> rows = new ArrayList<>();
    public ArrayList<ArrayList<Cell>> cols = new ArrayList<>();
    public ArrayList<ArrayList<Cell>> squares = new ArrayList<>();
    public ArrayList<ArrayList<ArrayList<Boolean>>> valueGrids = new ArrayList(); // [value][row][col] = value is candidate for position, [0][r][c] = position has value

    // CONSTRUCTORS
    public Sudoku() {
        // initialize cells in rows
        for (int row = 0; row < 9; row++) {
            ArrayList<Cell> newRow = new ArrayList<>();
            for (int col = 0; col < 9; col++) {
                newRow.add(new Cell(row, col));
            }
            rows.add(newRow);
        }

        // copy cells in columns
        for (int col = 0; col < 9; col++) {
            ArrayList<Cell> newCol = new ArrayList<>();
            for (int row = 0; row < 9; row++) {
                newCol.add(rows.get(row).get(col));
            }
            cols.add(newCol);
        }

        // copy cells in squares
        for (int row = 0; row < 9; row++) {
            ArrayList<Cell> newSquare = new ArrayList<>();
            for (int col = 0; col < 9; col++) {
                newSquare.add(rows.get(getSquareIndex(row, col)).get(getPositionWithinSquare(row, col)));
            }
            squares.add(newSquare);
        }

        // initialize valueGrids
        for (int value = 0; value <= 9; value++) {
            ArrayList<ArrayList<Boolean>> newGrid = new ArrayList<>();
            for (int row = 0; row < 9; row++) {
                if (value == 0) {
                    newGrid.add((ArrayList<Boolean>) IntStream.rangeClosed(1, 9).mapToObj(i -> false).collect(Collectors.toList()));
                }
                else {
                    newGrid.add((ArrayList<Boolean>) IntStream.rangeClosed(1, 9).mapToObj(i -> true).collect(Collectors.toList()));
                }

            }
            valueGrids.add(newGrid);
        }
    }

    // METHODS
    public Cell getCell(int row, int col) {return rows.get(row).get(col);}

    public static int getSquareIndex(int row, int col) {
        return 3 * (row / 3) + col / 3;
    }

    public static int getPositionWithinSquare(int row, int col) {
        return 3 * (row % 3) + col % 3;
    }

    public static int getRowFromSquareIndexes(int square_index, int square_position) {
        return 3 * (square_index / 3) + (square_position / 3);
    }

    public static int getColFromSquareIndexes(int square_index, int square_position) {
        return 3 * (square_index % 3) + (square_position % 3);
    }

    public void addValue(Cell cell, int value) {
        cell.setValue(value);
        valueGrids.get(0).get(cell.getRow()).set(cell.getCol(), true);
        for (Cell other : rows.get(cell.getRow())) {
            other.removeCandidate(value);
            valueGrids.get(value).get(other.getRow()).set(other.getCol(), false);
        }
        for (Cell other : cols.get(cell.getCol())) {
            other.removeCandidate(value);
            valueGrids.get(value).get(other.getRow()).set(other.getCol(), false);
        }
        for (Cell other : squares.get(getSquareIndex(cell.getRow(), cell.getCol()))) {
            other.removeCandidate(value);
            valueGrids.get(value).get(other.getRow()).set(other.getCol(), false);
        }
    }


    public void removeCandidate(Cell cell, int value) {

    }

    public void removeCandidate(ArrayList<Cell> set, int value) {

    }

    public boolean isSolved() {
        for (ArrayList<Cell> row : this.rows) {
            if (! row.stream().allMatch(Cell::hasValue)) {
                return false;
            }
        }
        return true;
    }

    public static boolean distinctValues(ArrayList<Integer> values){
        Set<Integer> foundNumbers = new HashSet<>();
        for (int num : values) {
            if(foundNumbers.contains(num)){
                return false;
            }
            foundNumbers.add(num);
        }
        return true;
    }

    public boolean isValid() {
        int i = 0;
        for (ArrayList<Cell> row : rows) {
            i ++;
            ArrayList<Integer> values = new ArrayList<>();
            for (Cell cell: row) {
                if (cell.hasValue()) {
                    values.add(cell.getValue());
                }
            }
            if (! distinctValues(values)) {
                System.out.println(String.format("Repetition in row %d", i));
                return false;
            }
        }

        i = 0;
        for (ArrayList<Cell> col : cols) {
            ArrayList<Integer> values = new ArrayList<>();
            for (Cell cell: col) {
                if (cell.hasValue()) {
                    values.add(cell.getValue());
                }
            }
            if (! distinctValues(values)) {
                System.out.println(String.format("Repetition in col %d", i));
                return false;
            }
        }

        i = 0;
        for (ArrayList<Cell> square : squares) {
            ArrayList<Integer> values = new ArrayList<>();
            for (Cell cell: square) {
                if (cell.hasValue()) {
                    values.add(cell.getValue());
                }
            }
            if (! distinctValues(values)) {
                System.out.println(String.format("Repetition in square %d", i));
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            s.append("| ");
            for (int col = 0; col < 9; col++) {
                Cell cell = rows.get(row).get(col);
                s.append(cell.hasValue() ? cell.getValue() : " ").append(" ");
                if ((col+1) % 3 == 0) {
                    s.append("| ");
                }
            }
            s.append("\n");
            if ((row+1) % 3 == 0) {
                s.append(" ----------------------- \n");
            }
        }
        return s.toString();
    }

    // TESTS

    // MAIN
    public static void main(String[] args) {
//        for (int row = 0; row < 9; row++) {
//            for (int col = 0; col < 9; col++) {
//                System.out.println(String.format("%d %d -> %d %d", row, col, 3 * (row / 3) + col / 3, 3 * (row % 3) + col % 3));
//            }
//        }
        ArrayList<Cell> row = new ArrayList<>();
        for (int col = 0; col < 9; col++) {
            row.add(new Cell(0, col));
        }

//        System.out.println(row.toString());
        Cell cell = new Cell(0,0);
//        cell.setValue(0);
        cell.removeCandidate(1);
//        row.set(0, cell);
        System.out.println(cell);
    }
}
