/**
 * This class represents a column vector which extends the matrix
 */
public class VectorBase extends MatrixBase implements VectorOperation {

    /**
     * Create a new Vector with a certain amount of entries and with a certain type
     */ 
    public VectorBase(int amount, Class _type) {
        super(amount, 1, _type);
    }

    /**
     * Create a new VectorBase that matches the array
     */
    public VectorBase(MatrixEntry[] array) {
       super(VectorBase.vectorToMatrix(array));
    }

    /**
     * Convert the 1D array to a 2D array that matches a column vector. Return null if teh array is null. 
     */
    private static MatrixEntry[][] vectorToMatrix(MatrixEntry[] array) {
        if (array == null)
            return null;

        MatrixEntry[][] newMatrix =  new MatrixEntry[array.length][1];
        for (int i = 0; i < array.length; ++i) 
            newMatrix[i][0] = array[i];
        return newMatrix;
    }

    /**
     * Transform the vector with a certain transformation matrix
     * @param The transformation matrix
     * @throws IllegalArgumentException When the transformatio matrix is null or it does not have the same type as the vector
     */
    public void transform(MatrixForm transformMatrix) {
        if (transformMatrix == null)
            throw new IllegalArgumentException("The matrix to be transformed with cannot be null");
        if (!transformMatrix.isType(getType()))
            throw new IllegalArgumentException("Multiplication must be of the same type");

        MatrixBase temp = (MatrixBase) transformMatrix.clone();
        temp.multiply(this);
        for (int i = 0; i < temp.getRow(); ++i) 
            setEntry(i, temp.getEntry(i, 0));
    } // end transform

    /**
     * Get the idxth entry of the matrix
     * The error will correspond to the error defined in MatrixBase
     * @param idx The position of the entry 
     * @return The MatrixEntry at that location
     */
    public MatrixEntry getEntry(int idx) {
        return super.getEntry(idx, 0);
    } // end getEntry

    /**
     * Set the idxth entry of the matrix
     * The error will correspond to the error defined in MatrixBase
     * @param idx The position of the entry
     * @param entry The entry to be set
     */
    public void setEntry(int idx, MatrixEntry entry) {
        super.setEntry(idx, 0, entry);
    } // end getEntry
}