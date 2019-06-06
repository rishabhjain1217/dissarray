public class ArrayListIndex extends Index {
    int index;

    public ArrayListIndex(int index)
    {
        this.index = index;
    }

    public int getIndex()
    {
        return index;
    }

    /**
     * Determines if a given Index is equal to this instance.
     * @param o The other index.
     * @return true if the indices match, otherwise false.
     */
    @Override
    boolean equals(Index o)
    {
        if (o instanceof ArrayListIndex) {
            if (this.getIndex() == ((ArrayListIndex) o).getIndex()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int compareTo(Index o)
    {
        /* Unsafe, but assume same type. */
        ArrayListIndex other = (ArrayListIndex) o;

        if (this.equals(other)) return 0;
        if (this.getIndex() < other.getIndex()) return -1;
        return 1;
    }

    public String toString(){
        return "x.get" + "(" + index + ")";
    }


}
