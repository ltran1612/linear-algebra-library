/**
 * The interface for what a matrix should have
 */
public interface MatrixForm {
    public void rowReduce();
    
    // row operations
    public void swapRow(int row1, int row2);
    public void addMultipleRow(int srcRow, int desRow, MatrixEntry constant);
    public void timesRow(int row, double scalar);

    // matrix math operations
    public void multiply(MatrixForm other);
    public void multiply(double scalar);
    //public void multiply(Object constant);
    public void add(MatrixForm other);

    // matrix control functions
    public MatrixEntry getEntry(int row, int col);
    public void setEntry(int row, int col, MatrixEntry entry);

    // row and column
    public int getRow();
    public int getColumn();

    public boolean isEmpty();
    public String toString(); 
    public boolean equals(MatrixForm other);

    public boolean isType(Class _type);
    public Class getType();

} // end MatrixForm