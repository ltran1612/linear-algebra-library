package linear_algebra.classes;
import linear_algebra.interfaces.*;
/**
 * This will contain other functionalities related to the matrix
 * Currently this contains:
 * + find the solution of a linear equation
 * + find a rank of a number matrix
 * + find the determinant of a number matrix
 */
public class OtherFunctionalities {
    /**
     * Find the solution of a system of linear equations using its matrix form.
     * @param _matrix The matrix correpsonding to the system of linear equations
     * @return An array of the result as strings, from the first entry to the final, it is the result of x0, x1, x2,... . In the result, the xi like x1 will be used to say that it is a free variable. 
     * If a value can be calculated it will be calculated, else it will be written in the result as the from something [+-/*] something. It will return null if there is not a solution.
     * @throws IllegalArgumentException When the matrix is null or when the matrix is empty
     */
    public static String[] findSolution(MatrixForm _matrix) {
        if (_matrix == null)
            throw new IllegalArgumentException("Matrix cannot be null");
        if (_matrix.isEmpty()) 
            throw new IllegalArgumentException("Matrix cannot be empty");    

        MatrixForm matrix = _matrix.clone();
        String[] free_result = new String[matrix.getColumn() - 1];
        MatrixEntry[] value_result = new MatrixEntry[matrix.getColumn() - 1];
        
        matrix.rowReduce();
        for (int i = matrix.getRow() - 1; i >= 0; --i) {
            int leadingEntryIndex; 
            for (leadingEntryIndex = 0; leadingEntryIndex < matrix.getColumn() && matrix.getEntry(i, leadingEntryIndex).isZero(); ++leadingEntryIndex);

            // if the index is the last index in the column, something of the form [0 2], cannot be found 
            if (leadingEntryIndex == matrix.getColumn() - 1)
                return null;
            
            // if the index is around the column of the matrix, something of the form [0 0], limitless abilities
            if (leadingEntryIndex == matrix.getColumn())
                continue;
            

            String stringValue = "";
            MatrixEntry calculatedValue = matrix.getEntry(i, matrix.getColumn() - 1);
            // normal cases something of the form [1 ... 2]
            for (int j = leadingEntryIndex + 1; j < matrix.getColumn() - 1; ++j) {
                if (free_result[j] == null && value_result[j] == null) {
                    free_result[j] = "x"+j;
                }
                // if this is a free varaible that has not been found
                if (free_result[j] != null) 
                    stringValue += "-" + matrix.getEntry(i, j) + "*" + free_result[j];

                if (value_result[j] != null) 
                    calculatedValue = calculatedValue.substract(value_result[j].multiply(matrix.getEntry(i, j)));
            }

            if (!stringValue.isEmpty()) {
                stringValue = "(" + stringValue + ")" + "/" + matrix.getEntry(i, leadingEntryIndex);
                free_result[leadingEntryIndex] = stringValue;
            } // end if
            
            calculatedValue = calculatedValue.divide(matrix.getEntry(i, leadingEntryIndex));
            value_result[leadingEntryIndex] = calculatedValue;                    
        }

        String[] finalResult = new String[matrix.getColumn() - 1];

        for (int i = 0; i < finalResult.length; ++i) {
            finalResult[i] = free_result[i];

            if (free_result[i] == null)
                finalResult[i] = "" + value_result[i];

            if (value_result[i] != null && free_result[i] != null)
                finalResult[i] += "+" + value_result[i];

        } // end for i
        
        return finalResult;
    } // end findSolution

    /**
     * Find the rank of a matrix. This is tested to work with double value matrices.
     * @param _matrix The matrix whose rank this function will find
     * @throws IllegalArgumentException When the matrix is null or when the matrix is empty
     * @return The rank of the matrix
     */
    public static int findRank(MatrixForm _matrix) {
        if (_matrix == null)
            throw new IllegalArgumentException("Matrix cannot be null");
        if (_matrix.isEmpty()) 
            throw new IllegalArgumentException("Matrix cannot be empty");

        MatrixForm matrix = _matrix.clone();
        matrix.rowReduce();
        int rank = 0;
        for (int i = 0; i < matrix.getRow(); ++i) {
            for (int j = 0; j < matrix.getColumn(); ++j) {
                if (!matrix.getEntry(i, j).isZero()) {
                    rank++;
                    break;
                }
            } // end for j
        } // end for i

        return rank;
    } // end findRank

    /**
     * Find the determinant of a square matrix
     * @param _matrix The matrix whose determinant this function will find
     * @return The determinant of the matrix
     * @throws IllegalArgumentException When the matrix is null or it is empty or it is not square
     */
    public static MatrixEntry findDeterminant(MatrixForm _matrix) {
        if (_matrix == null)
            throw new IllegalArgumentException("Matrix cannot be null");
        if (_matrix.isEmpty()) 
            throw new IllegalArgumentException("Matrix cannot be empty");
        if (_matrix.getColumn() != _matrix.getRow())
            throw new IllegalArgumentException("Matrix has to be square to find the determinant");

        MatrixForm matrix = _matrix.clone();
        int swapRowSign = 1;

        // go through each column, do partial pivot, then clear the entries below in that column to be zero
        int pivotRow = 0;
        for (int i = 0; i < matrix.getColumn() && pivotRow < matrix.getRow(); ++i) {
            if (matrix.getEntry(pivotRow, i).isZero()) {
                int nonZeroRow;
                for (nonZeroRow = pivotRow + 1; nonZeroRow < matrix.getRow() && matrix.getEntry(nonZeroRow, i).isZero(); ++nonZeroRow);

                // there are all zeroes, go look in another
                if (nonZeroRow == matrix.getRow())
                    continue;
                
                matrix.swapRow(nonZeroRow, pivotRow);
                swapRowSign = -swapRowSign;
                //System.out.println(matrix);
            } // end if
           
            // clearing zeroes
            for (int j = pivotRow + 1; j < matrix.getRow(); ++j) {
                matrix.addMultipleRow(pivotRow, j, matrix.getEntry(j, i).multiply(-1).divide(matrix.getEntry(pivotRow, i)));
            } // end for j

            pivotRow++;
        } // end for i
        
        MatrixEntry result = null;
        pivotRow = 0;
        for (int i = 0; i < matrix.getColumn() && pivotRow < matrix.getRow(); ++i) {
           
            if (i != matrix.getColumn() - 1 && matrix.getEntry(pivotRow, i).isZero()) 
                continue;

            if (result == null)
                result = matrix.getEntry(pivotRow, i);
            else
                result = result.multiply(matrix.getEntry(pivotRow, i));

            pivotRow++;
        } // end for i

        result = result.multiply(swapRowSign);
        return result;
    } // end findDeterminant
    
} // end OtherFunctionalities