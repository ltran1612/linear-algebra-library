import java.lang.reflect.Constructor;
import java.lang.NoSuchMethodException;

public class MatrixBase implements MatrixForm{ 
    private Object[][] matrix;
    private int row;
    private int column; 
    private Class type;

    /**
     * Create a zero matrix with the specified zero and column and type of entry
     * @param _row The row of the matrix
     * @param _col The column of the matrix
     * @param _type The type of the entries in the matrix
     * @throws NoSuchMethodException When the default constructor for the entry is not defined to create a zero entry.
     */
    public MatrixBase(int _row, int _col, Class _type) {
        if (_row < 0 || _col < 0)
            throw new IllegalArgumentException("Either row or column cannot be negative");
        if (_type == null)
            throw new IllegalArgumentException("Type is null");        
        row = _row;
        column = _col;
        type = _type;
        matrix = new Object[_row][_col];

        // initialize with zero vectors
        try {
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < column; ++j) {
                    Constructor<?> cst = type.getConstructor();
                    matrix[i][j] = cst.newInstance();
                } // end for j
            } // end for i
        } catch(NoSuchMethodException e) {
            new NoSuchMethodException("The default constructor to create a zero entry does not exist").printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } // end catch
    } // end MatrixBase

    /**
     * Create a matrix corresponding to an array of MatrixEntry. 
     * @param _matrix the array of MatrixEntry
     * @throws IllegalArgumentException When the parameter array is not rectangular and when the array containing different types of entry from each other. 
     */
    public MatrixBase(MatrixEntry[][] _matrix) {
        // check if the matrix is empty
        if (checkEmpty(_matrix)) {
            row = 0;
            column = 0;
            matrix = new Object[0][0];
            return;
        } // end if

        row = _matrix.length;
        column = _matrix[0].length;
        type = _matrix[0][0].getClass();

        // check if the array is rectangular
        for (int i = 1; i < row; ++i) {
            if (_matrix[i].length != column)
                throw new IllegalArgumentException("The MatrixEntry array has to be rectangular");
        } // end for i

        matrix = new Object[row][column];

        // copy values
        for (int i = 0; i < row; ++i)
            for (int j = 0; j < column; ++j)
                setEntry(i, j, _matrix[i][j]);
    } // end MatrixBase

    /**
     * Return the entry at a specific location in the matrix
     * @param row The row of the entry
     * @param col The column of the entry
     * @return The MatrixEntry at the specified row and column
     * @throws IllegalArgumentException When either of the row or column is negative
     */
    public MatrixEntry getEntry(int row, int col) {
        if (row < 0 || col < 0)
            throw new IllegalArgumentException("Either row or column cannot be negative");
        return (MatrixEntry) matrix[row][col];
    } // end getEntry

    /**
     * Set an entry of a matrix to a certain entry
     * @param row The row of the entry
     * @param col The column of the entry 
     * @param entry The entry set at the specified row and column
     * @throws IllegalArgumentException When the either the row or column is negaitve or the parameter entry is null
     */
    public void setEntry(int row, int col, MatrixEntry entry) {
        if (entry == null)
            throw new IllegalArgumentException("Entry cannot be null");
        if (row < 0 || col < 0)
            throw new IllegalArgumentException("Either row or column cannot be negative");
        if (!entry.getClass().getName().equals(type.getName()))
            throw new IllegalArgumentException("The entry does not have the same type as other: " + 
                                entry.getClass().getName() + ". Matrix type is: " + type.getName());

        matrix[row][col] = entry.clone();
    } // end setEntry

    /**
     * Check if an array is empty
     */
    protected boolean checkEmpty(Object[][] _matrix) {
        if (_matrix == null || _matrix.length == 0 || _matrix[0].length == 0) {
            return true;
        } // end if

        return false;
    } // end checkRectangular

    /**
     * Return the row of the matrix
     * @return The row of the matrix
     */
    public int getRow() {
        return row;
    } // end getRow

    /**
     * Return the column of a matrix
     * @return The column of the matrix
     */
    public int getColumn() {
        return column;
    } // end getColumn

    public void rowReduce() {
        for (int i = 0; i < )

    } // end rowReduce 
    // row operations
    /**
     * Switch two rows in the matrix 
     * @param row1 The first row
     * @param row2 The second row
     * @throws IllegalArgumentException When either one of the two rows are negative
     */
    public void switchRow(int row1, int row2) {
        if (row1 < 0 || row2 < 0) 
            throw new IllegalArgumentException("Either one of the rows switched cannot be negative");

        // switch the rows
        for (int i = 0; i < column; ++i) {
            Object temp = matrix[row1][i];
            matrix[row1][i] = matrix[row2][i];
            matrix[row2][i] = temp;
        } // end for i
    } // end switchRow

    /**
     * Add to one row the multiple of another
     * @param srcRow The row to be added to another row (source row)
     * @param desRow The row to have another row added (destination row)
     * @param scalar The multiple of the source row
     */
    public void addMultipleRow(int srcRow, int desRow, double scalar) {
        if (srcRow < 0)
            throw new IllegalArgumentException("The source row cannot be negative");

        if (desRow < 0)
            throw new IllegalArgumentException("The destination row cannot be negative");

        for (int i = 0; i < column; ++i) {
            matrix[desRow][i] = ((MatrixEntry)matrix[desRow][i]).add( ((MatrixEntry) matrix[srcRow][i]).multiply(scalar));
        } // end for i
    } // end addMultipleRow

    public void timesRow(int row, double scalar) {
    }

    // matrix math operations
    /**
     * Multiply this matrix with another matrix and this object will become the new matrix
     * @param other The matrix to be multiply with this matrix
     * @throws IllegalArgumentexception When the matrix to be multiplied is null or has a different type from this matrix
     */
    public void multiply(MatrixForm other) {
        if (other == null)
            throw new IllegalArgumentException("Cannot multiply with a null matrix object");
        if (!other.isType(type))
            throw new IllegalArgumentException("Cannot multiply with a matrix of a different type");
        
        // check rows, and columns
        if (column != other.getRow())
            throw new IllegalArgumentException("Cannot multiply a matrix whose row is different from this matrix column");

        int newColumn = other.getColumn();
        Object[][] newMatrix = new Object[row][newColumn];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < newColumn; ++j) {
                MatrixEntry newEntry = ((MatrixEntry)matrix[i][0]).multiply(other.getEntry(0, j));
                for (int z = 1; z < column; ++z) {
                    newEntry = newEntry.add(((MatrixEntry)matrix[i][z]).multiply(other.getEntry(z, j)));
                } // end for z
                newMatrix[i][j] = newEntry;
            } // end for j
        } // end for i

        matrix = newMatrix;
        column = newColumn;
    } // end multiply

    /**
     * Multiply the matrix with the scalar
     * @param scalar The constant ths matrix will be multiplied with
     */
    public void multiply(double scalar) {
        for (int i = 0; i < row; ++i)
            for (int j = 0; j < column; ++j)
                setEntry(i, j, getEntry(i, j ).multiply(scalar));
    } // end multiply

    /**
     * Add to this matrix another matrix with the same size
     * @param other The matrix to be added to this matrix
     * @throws IllegalArgumentException When the matrix to be added is either null or is different in size
     */
    public void add(MatrixForm other) {
        if (other == null)
            throw new IllegalArgumentException("Cannot add a null matrix object");
        if (!other.isType(type))
            throw new IllegalArgumentException("Cannot add a matrix of a different type");
        
        // check rows, and columns
        if (row != other.getRow() || column != other.getColumn())
            throw new IllegalArgumentException("Cannot add a matrix with either different row or column");
        
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                setEntry(i, j, getEntry(i, j).add(other.getEntry(i, j)));
            } // end for j
        } // end for i
    } // end add

    public String toString() {
        String result = "";
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j)
                result += matrix[i][j] + " ";
            result += "\n";
        } // end for i

        return result;
    } // end toString
    
    /**
     * Check if a matrix has entry of this type
     * @throws IllegalArgumentException When the Class parameter is null
     * @return true if this matrix has the type specified in the parameter
     */
    public boolean isType(Class _type) {
        if (_type == null)
            throw new IllegalArgumentException("Class type cannot be null");
        return type.equals(_type);
    } // end isType

    /**
     * Check if a matrix is empty
     * @return true if the matrix is empty
     */
    public boolean isEmpty() {
        return row == 0 || column == 0;
    } // end isEmpty

    /**
     * Check if a matrix is similar to this matrix. Two matrices are equal if their elements are the same. 
     * @param other The matrix to be checked
     * @return true if the matrix is equal to the matrix
     */
    public boolean equals(MatrixForm other) {
        if (other == null)
            throw new IllegalArgumentException("other matrix cannot be null");
        return true;
    } // end equals
} // end MatrixBase