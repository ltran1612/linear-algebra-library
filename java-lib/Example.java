import linear_algebra.classes.*;
import linear_algebra.interfaces.*;

public class Example {
	public static void main(String args[]) {
		// create a new matrix
		DoubleEntry[][] array = new DoubleEntry[2][2];
		array[0][0] = new DoubleEntry(1);
		array[0][1] = new DoubleEntry(2);
		array[1][0] = new DoubleEntry(3);
		array[1][1] = new DoubleEntry(4);
		MatrixBase matrix = new MatrixBase(array);

		// print out the matrix
		System.out.println(matrix);

		// reduce the matrix
		System.out.println("Row reduced to echelon form matrix");
		matrix.rowReduce();
		System.out.println(matrix);
	}

}
