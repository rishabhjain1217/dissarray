public class TwoDimIndex extends Index {

    private int rowIndex, colIndex;

    public TwoDimIndex(int rowIndex, int colIndex)
    {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public int getRowIndex()
    {
        return rowIndex;
    }

    public int getColIndex()
    {
        return colIndex;
    }

    /**
     * Determines if a given Index is equal to this instance.
     * @param o The other index.
     * @return true if the indices match, otherwise false.
     */
    @Override
    boolean equals(Index o)
    {

        if (o instanceof TwoDimIndex) {
            if (this.getRowIndex() == ((TwoDimIndex) o).getRowIndex()
                    && this.getColIndex() == ((TwoDimIndex) o).getColIndex()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int compareTo(Index o)
    {
        /* Unsafe, but assume same type. */
        TwoDimIndex other = (TwoDimIndex) o;

        if (this.equals(other)) return 0;
        if (this.getRowIndex() == other.getRowIndex()) {
            if (this.getColIndex() < other.getColIndex()) {
                return -1;
            }

            return 1;
        }
        if (this.getRowIndex() < other.getRowIndex()) {
            return -1;
        }

        return 1;
    }

    @Override
    public String toString() {
        return "[" + this.rowIndex + "][" + this.colIndex + "]";
    }
}
