import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cell {
    // ATTRIBUTES
    private Integer row, col;
    private ArrayList<Integer> candidates = (ArrayList<Integer>) IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList());
    private boolean hasValue = false;

    // CONSTRUCTORS
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // METHODS
    public void setValue(int value) {
        candidates = new ArrayList<>(Arrays.asList(value));
        hasValue = true;
    }

    public boolean hasValue() {
        return hasValue;
    }

    public int getValue() {
        return hasValue ? candidates.get(0) : -1;
    }

    public void removeCandidate(int value) {
        if (! hasValue()) {
            candidates.remove(new Integer(value));
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public ArrayList<Integer> getCandidates() {
        return candidates;
    }

    @Override
    public String toString() {
//        return String.format("Cell(row = %d, col = %d) value = %s", row, col, hasValue() ? getValue() : "*");
        return String.format("Cell(row = %d, col = %d) value = %s, candidates = %s", row, col, hasValue() ? getValue() : "*", candidates.toString());
    }

    public String toVisualString() {
        if (hasValue()) {
            StringBuilder s = new StringBuilder();
            for (int i = 1; i <= 9; i++) {
                s.append(candidates.contains(i) ? "*" : " ");
                s.append((i % 3 == 0) ? "\n" : " ");
            }
            return s.toString();
        }
        return String.format("     \n  %d   \n     \n", getValue());
    }

}
