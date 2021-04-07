public class Move {

    // ATTRIBUTES
    public final Cell cell;
    public final int value;
    public final String heuristicName;
    public final String info;
    // TODO add detailed explanation instead of heuristicName

    // CONSTRUCTORS
    public Move(Cell cell, int value, String heuristicName, String info) {
        assert value >= 0 & value <= 8;

        this.cell = cell;
        this.value = value;
        this.heuristicName = heuristicName;
        this.info = info;
    }

    public Move(Cell cell, int value, String heuristicName) {
        this(cell, value, heuristicName, "");
    }

    public Move(Move move) {
        this(move.cell, move.value, move.heuristicName);
    }


    // METHODS
    @Override
    public String toString() {
        return String.format("%s. value = %d at (%d, %d)%s.",heuristicName, value, cell.getRow()+1, cell.getCol()+1, info.length() > 0 ? (" " + info): "");
    }

}