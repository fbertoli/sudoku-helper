public abstract class Technique {
    // ATTRIBUTES
    protected boolean foundNewMove = false;
    protected Move nextMove = null;
    protected String name;

    // CONSTRUCTORS
    public Technique(String name) {
        this.name = name;
    }

    // METHODS
    public abstract void applyToSudoku(Sudoku sudoku);

    public boolean foundNewMove() {return foundNewMove;}

    public Move getNextMove() {return nextMove;}

    public void reset() {
        nextMove = null;
        foundNewMove = false;
    }

    public String getName() {
        return name;
    }
}
