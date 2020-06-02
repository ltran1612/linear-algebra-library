import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 * Test for MatrixBaseTest
 * There were not 100% answer to check if row reduce works, so the check is done by printing out the content and see the content myself. 
 */
public class MatrixBaseTest {
    // Data you need for each test case
    private MatrixBase matrix;

   
    // 
    // Stuff you want to do before each test case
    //
    @BeforeEach
    public void setup() {
        System.out.println("\nTest starting...");
        matrix = new MatrixBase(2, 2, DoubleEntry.class);
    }

    // Stuff you want to do after each test case
    //
    @AfterEach
    public void teardown() {
        System.out.println("\nTest finished.");
    }

    // test cases
// constructor
    @Test
    public void testArrayConstructorNormal() {
        System.out.println("Testing testArrayConstructor");
        DoubleEntry[][] entries = new DoubleEntry[2][2];
        entries[0][0] = new DoubleEntry(1);
        entries[0][1] = new DoubleEntry(2);
        entries[1][0] = new DoubleEntry(3);
        entries[1][1] = new DoubleEntry(4);

        MatrixBase other = new MatrixBase(entries);
        int amount = 1;
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                MatrixEntry entry = other.getEntry(i, j);
                if (!entry.equals(new DoubleEntry(amount++)))
                    assertTrue(false);
            } // end for j
        } // end for i

        assertTrue(true);
    } // end testArrayConstructor

    @Test
    public void testArrayConstructorDifferentEntryType() {
        System.out.println("Testing testArrayConstructorDifferentEntryType");
        MatrixEntry[][] entries = new MatrixEntry[2][2];
        entries[0][0] = (MatrixEntry) new DoubleEntry(1);
        entries[0][1] = (MatrixEntry) new DoubleEntry(2);
        entries[1][0] = (MatrixEntry) new PolynomialEntry();
        entries[1][1] = (MatrixEntry) new PolynomialEntry();
        
        assertThrows(IllegalArgumentException.class, () -> new MatrixBase(entries));
    } // end testArrayConstructorDifferentEntryType

    @Test
    public void testConstructorTypeNotMatrixEntry() {
        System.out.println("Testing testConstructorTypeNotMatrixEntry");
        
        assertThrows(IllegalArgumentException.class, () -> new MatrixBase(1, 2, Double.class));
    } // end testArrayConstructorDifferentEntryType

// getEntry
    @Test 
    public void testGetEntryNormal() {
        System.out.println("Testing testGetEntryNormal");
        assertTrue(matrix.getEntry(0, 0).equals(new DoubleEntry(0)));
    } // end testGetEntryNormal

    @Test 
    public void testGetEntryNegativeRow() {
        System.out.println("Testing testGetEntryNegativeRow");
        assertThrows(IllegalArgumentException.class, () -> matrix.getEntry(-1, 0));
    } // end testGetEntryNegativeRow

    @Test 
    public void testGetEntryNegativeCol() {
        System.out.println("Testing testGetEntryNegativeCol");
        assertThrows(IllegalArgumentException.class, () -> matrix.getEntry(0, -1));
    } // end testGetEntryNegativeCol

// setEntry
    @Test 
    public void testSetEntryNormal() {
        System.out.println("Testing testSetEntryNormal");
        matrix.setEntry(0, 0, new DoubleEntry(1));
        assertTrue(matrix.getEntry(0, 0).equals(new DoubleEntry(1)));
    } // end testSetEntryNormal

    @Test 
    public void testSetEntryNegativeRow() {
        System.out.println("Testing testSetEntryNegativeRow");
        assertThrows(IllegalArgumentException.class, () -> matrix.setEntry(-1, 0, new DoubleEntry()));
    } // end testSetEntryNegativeRow

    @Test 
    public void testSetEntryNegativeCol() {
        System.out.println("Testing testSetEntryNegativeCol");
        assertThrows(IllegalArgumentException.class, () -> matrix.setEntry(0, -1, new DoubleEntry()));
    } // end testSetEntryNegativeCol

    @Test
    public void testSetEntryDifferentType() {
        System.out.println("Testing testSetEntryDifferentType");
        assertThrows(IllegalArgumentException.class, () -> matrix.setEntry(0, 0, new PolynomialEntry()));
    } // end testSetEntryDifferentType

// add
    @Test
    public void testAddNull() {
        System.out.println("Testing testAddNull");
        assertThrows(IllegalArgumentException.class, () -> matrix.add(null));
    } // end testAddNull

    @Test
    public void testAddDifferentType() {
        System.out.println("Testing testAddDifferentType");
        MatrixBase other = new MatrixBase(2, 2, PolynomialEntry.class);
        assertThrows(IllegalArgumentException.class, () -> matrix.add(other));
    } // end testAddNull

    @Test
    public void testAddDifferentSize() {
        System.out.println("Testing testAddDifferentSize");
        DoubleEntry[][] array = new DoubleEntry[3][2];
        array[0][0] = new DoubleEntry(1);
        array[0][1] = new DoubleEntry(2);
        array[1][0] = new DoubleEntry(3);
        array[1][1] = new DoubleEntry(3);
        array[2][0] = new DoubleEntry(4);
        array[2][1] = new DoubleEntry(4);

        MatrixBase old = (MatrixBase) matrix.clone();
        matrix.add(new MatrixBase(array));

        for (int i = 0; i < matrix.getRow(); ++i) {
            for (int j = 0; j < matrix.getColumn(); ++j) {
                if (!matrix.getEntry(i, j).equals(old.getEntry(i, j)))
                    assertTrue(false);
            } // end for j
        } // end for i
        assertTrue(true);
    } // end testAddNull

    @Test
    public void testAddNormal() {
        System.out.println("Testing testAddNormal");
        DoubleEntry[][] array = new DoubleEntry[2][2];
        array[0][0] = new DoubleEntry(1);
        array[0][1] = new DoubleEntry(2);
        array[1][0] = new DoubleEntry(3);
        array[1][1] = new DoubleEntry(4);

        MatrixBase other = new MatrixBase(array);
        matrix.add(other);

        System.out.println(matrix);

        for (int i = 0; i < matrix.getRow(); ++i) {
            for (int j = 0; j < matrix.getColumn(); ++j) {
                if (!matrix.getEntry(i, j).equals(other.getEntry(i, j)))
                    assertTrue(false);
            } // end for j
        } // end for i
        assertTrue(true);
    } // end testAddNull

// multiply
    @Test 
    public void testMultiplyNormal() {
        System.out.println("Testing testMultiplyNormal");
        DoubleEntry[][] array = new DoubleEntry[2][2];
        array[0][0] = new DoubleEntry(1);
        array[0][1] = new DoubleEntry(2);
        array[1][0] = new DoubleEntry(3);
        array[1][1] = new DoubleEntry(4);

        MatrixBase other = new MatrixBase(array);
        matrix.add(other);

        array = new DoubleEntry[2][3];
        array[0][0] = new DoubleEntry(1);
        array[0][1] = new DoubleEntry(2);
        array[0][2] = new DoubleEntry(3);
        array[1][0] = new DoubleEntry(3);
        array[1][1] = new DoubleEntry(4);
        array[1][2] = new DoubleEntry(4);

        other = new MatrixBase(array);
        matrix.multiply(other);

        DoubleEntry[][] result = new DoubleEntry[2][3];
        result[0][0] = new DoubleEntry(7);
        result[0][1] = new DoubleEntry(10);
        result[0][2] = new DoubleEntry(11);
        result[1][0] = new DoubleEntry(15);
        result[1][1] = new DoubleEntry(22);
        result[1][2] = new DoubleEntry(25);

        System.out.println(matrix);

        for (int i = 0; i < matrix.getRow(); ++i) {
            for (int j = 0; j < matrix.getColumn(); ++j) {
                if (!matrix.getEntry(i, j).equals(result[i][j])) {
                    System.out.println(matrix);
                    assertTrue(false);
                }
            } // end for j
        } // end for i
        assertTrue(true);
    } // end testMultiplyNormal

    @Test
    public void testMultiplyNull() {
        System.out.println("Testing testMultiplyNull");
        assertThrows(IllegalArgumentException.class, () -> matrix.multiply(null));
    } // end testMultiplypNull

    @Test
    public void testMultiplyDifferentType() {
        System.out.println("Testing testMultiplyDifferentType");
        MatrixBase other = new MatrixBase(2, 2, PolynomialEntry.class);
        assertThrows(IllegalArgumentException.class, () -> matrix.multiply(other));
    } // end testAddNull

    @Test
    public void testMultiplyDifferentSize() {
        System.out.println("Testing testMultiplyDifferentSize");
        DoubleEntry[][] array = new DoubleEntry[3][2];
        array[0][0] = new DoubleEntry(1);
        array[0][1] = new DoubleEntry(2);
        array[1][0] = new DoubleEntry(3);
        array[1][1] = new DoubleEntry(3);
        array[2][0] = new DoubleEntry(4);
        array[2][1] = new DoubleEntry(4);

        MatrixBase old = (MatrixBase) matrix.clone();
        matrix.multiply(new MatrixBase(array));

        for (int i = 0; i < matrix.getRow(); ++i) {
            for (int j = 0; j < matrix.getColumn(); ++j) {
                if (!matrix.getEntry(i, j).equals(old.getEntry(i, j)))
                    assertTrue(false);
            } // end for j
        } // end for i
        assertTrue(true);
    } // end testAddNull

    @Test
    public void testMultiplyScalar() {
        System.out.println("Testing testMultiplyScalar");

        matrix.multiply(3);

        for (int i = 0; i < matrix.getRow(); ++i) {
            for (int j = 0; j < matrix.getColumn(); ++j) {
                if (!matrix.getEntry(i, j).equals(matrix.getEntry(i, j).multiply(3)))
                    assertTrue(false);
            } // end for j
        } // end for i
        assertTrue(true);
    } // end testAddNull
    // test multiple constants and other stuffs of matrix multiplication

// swapRow 
    @Test
    public void testSwitchRowNegative1() {
        System.out.println("Testing testSwitchRowNegative1");
        assertThrows(IllegalArgumentException.class, () -> matrix.swapRow(-1, 0));
    } // end testSwithRowNegative1

    @Test
    public void testSwitchRowNegative2() {
        System.out.println("Testing testSwitchRowNegative2");
        assertThrows(IllegalArgumentException.class, () -> matrix.swapRow(0, -1));
    } // end testSwitchRowNegative2

    @Test
    public void testSwitchRowNormal() {
        System.out.println("Testing testSwitchRowNormal");
        DoubleEntry[][] array = new DoubleEntry[2][2];
        array[0][0] = new DoubleEntry(1);
        array[0][1] = new DoubleEntry(2);
        array[1][0] = new DoubleEntry(3);
        array[1][1] = new DoubleEntry(4);

        MatrixBase other = new MatrixBase(array);
        matrix.add(other);
        matrix.swapRow(0, 1);

        System.out.println(matrix);

        boolean result = matrix.getEntry(0, 0).equals(array[1][0]) && matrix.getEntry(0, 1).equals(array[1][1])
        && matrix.getEntry(1, 0).equals(array[0][0]) && matrix.getEntry(1, 1).equals(array[0][1]);
        
        assertTrue(result);
    } // end testSwitchRowNormal

// addMultipleRow
    @Test
    public void testAddMultipleRowNegativeSourceRow() {
        System.out.println("Testing testAddMultipleRowNegativeSourceRow");
        assertThrows(IllegalArgumentException.class, () -> matrix.addMultipleRow(-1, 1, new DoubleEntry(5)));
        
    } // end testAddMultipleRowNegativeSourceRow

    @Test
    public void testAddMultipleRowNegativeDestinationRow() {
        System.out.println("Testing testAddMultipleRowNegativeDestinationRow");
        assertThrows(IllegalArgumentException.class, () -> matrix.addMultipleRow(1, -1, new DoubleEntry(5)));
    } // end testAddMultipleRowNegativeSourceRow

    @Test
    public void testAddMultipleRowNormal() {
        System.out.println("Testing testAddMultipleRowNormal");
        DoubleEntry[][] array = new DoubleEntry[2][2];
        array[0][0] = new DoubleEntry(1);
        array[0][1] = new DoubleEntry(2);
        array[1][0] = new DoubleEntry(3);
        array[1][1] = new DoubleEntry(4);

        MatrixBase other = new MatrixBase(array);
        matrix.add(other);
        matrix.addMultipleRow(0, 1, new DoubleEntry(5));

        System.out.println(matrix);

        assertTrue(matrix.getEntry(0, 0).equals(array[0][0]) && matrix.getEntry(0, 1).equals(array[0][1])
            && matrix.getEntry(1, 0).equals(array[0][0].multiply(5).add(array[1][0])) 
            && matrix.getEntry(1, 1).equals(array[0][1].multiply(5).add(array[1][1])));
    } // end testAddMultipleRowNegativeSourceRow

// row reduce
    @Test
    public void testRowReduceNormalSquare() {
        System.out.println("Testing testRowReduceNormal");
        DoubleEntry[][] array = new DoubleEntry[2][2];
        array[0][0] = new DoubleEntry(1);
        array[0][1] = new DoubleEntry(2);
        array[1][0] = new DoubleEntry(3);
        array[1][1] = new DoubleEntry(4);

        MatrixBase other = new MatrixBase(array);
        matrix.add(other);

        matrix.rowReduce();
        System.out.println(matrix);
        assertTrue(false);
    } // end testAddMultipleRowNegativeSourceRow

    @Test
    public void testRowReduceNormalColGreaterThanRow() {
        System.out.println("Testing testRowReduceNormalColGreaterThanRow");
        DoubleEntry[][] array = new DoubleEntry[3][];
        array[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(3), new DoubleEntry(1), new DoubleEntry(9)};
        array[1] = new DoubleEntry[]{new DoubleEntry(1), new DoubleEntry(1), new DoubleEntry(-1), new DoubleEntry(1)};
        array[2] = new DoubleEntry[]{new DoubleEntry(3), new DoubleEntry(11), new DoubleEntry(5), new DoubleEntry(35)};

        MatrixBase other = new MatrixBase(array);
        other.rowReduce();
        System.out.println(other);
        assertTrue(false);
    } // end testAddMultipleRowNegativeSourceRow

    @Test
    public void testRowReduceNormalRowGreaterThanCol() {
        System.out.println("Testing testRowReduceNormalRowGreaterThanCol");
        DoubleEntry[][] array = new DoubleEntry[4][];
        array[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(2)};
        array[1] = new DoubleEntry[]{new DoubleEntry(-4), new DoubleEntry(0)};
        array[2] = new DoubleEntry[]{new DoubleEntry(8), new DoubleEntry(5)};
        array[3] = new DoubleEntry[]{new DoubleEntry(9), new DoubleEntry(-1)};

        MatrixBase other = new MatrixBase(array);
        other.rowReduce();
        System.out.println(other);
        assertTrue(false);
    } // end testAddMultipleRowNegativeSourceRow

    @Test
    public void testRowReduceNormalEchelonFormNotSquare() {
        System.out.println("Testing testRowReduceNormalEchelonFormNotSquare");
        DoubleEntry[][] array = new DoubleEntry[3][];
        array[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(3), new DoubleEntry(1), new DoubleEntry(9)};
        array[1] = new DoubleEntry[]{new DoubleEntry(0), new DoubleEntry(0), new DoubleEntry(-1), new DoubleEntry(1)};
        array[2] = new DoubleEntry[]{new DoubleEntry(0), new DoubleEntry(0), new DoubleEntry(5), new DoubleEntry(35)};

        MatrixBase other = new MatrixBase(array);   
        other.rowReduce();
        System.out.println(other);
        assertTrue(false);
    } // end testAddMultipleRowNegativeSourceRow    

    @Test
    public void testRowReduceSovlveSystemLinearEquations() {
        System.out.println("Testing testRowReduceSovlveSystemLinearEquations");
        DoubleEntry[][] array = new DoubleEntry[2][];
        array[0] = new DoubleEntry[] {new DoubleEntry(8), new DoubleEntry(-1), new DoubleEntry(9)};
        array[1] = new DoubleEntry[]{new DoubleEntry(4), new DoubleEntry(9), new DoubleEntry(7)};

        MatrixBase other = new MatrixBase(array);   
        other.rowReduce();
        System.out.println(other);
        assertTrue(false);
    } // end testAddMultipleRowNegativeSourceRow    

    // one row
    @Test
    public void testRowReduceOneRow() {
        System.out.println("Testing testRowReduceOneRow");
        DoubleEntry[][] array = new DoubleEntry[1][3];
        array[0][0] = new DoubleEntry(1);
        array[0][1] = new DoubleEntry(2);
        array[0][2] = new DoubleEntry(3);

        MatrixBase other = new MatrixBase(array);   
        other.rowReduce();
        System.out.println(other);
        assertTrue(false);
    } // end testAddMultipleRowNegativeSourceRow    

    // one column
    @Test
    public void testRowReduceOneColumn() {
        System.out.println("Testing testRowReduceOneColumn");
        DoubleEntry[][] array = new DoubleEntry[3][1];
        array[0][0] = new DoubleEntry(1);
        array[1][0] = new DoubleEntry(2);
        array[2][0] = new DoubleEntry(3);

        MatrixBase other = new MatrixBase(array);   
        other.rowReduce();
        System.out.println(other);
        assertTrue(false);
    } // end testAddMultipleRowNegativeSourceRow    

    // empty matrix
    @Test
    public void testRowReduceEmptyMatrix() {
        System.out.println("Testing testRowReduceEmptyMatrix");
        DoubleEntry[][] array = new DoubleEntry[0][0];
        
        MatrixBase other = new MatrixBase(array);   
        other.rowReduce();
        System.out.println(other);
        assertTrue(false);
    } // end testAddMultipleRowNegativeSourceRow       
    
    @Test
    public void testRowReduceSwapRowNeeded() {
        System.out.println("Testing testRowReduceSwapRowNeeded");
        DoubleEntry[][] array = new DoubleEntry[4][];
        array[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(3), new DoubleEntry(5), new DoubleEntry(9)};
        array[1] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(3), new DoubleEntry(1), new DoubleEntry(7)};
        array[2] = new DoubleEntry[] {new DoubleEntry(4), new DoubleEntry(3), new DoubleEntry(9), new DoubleEntry(7)};
        array[3] = new DoubleEntry[] {new DoubleEntry(5), new DoubleEntry(2), new DoubleEntry(0), new DoubleEntry(9)};

        MatrixBase other = new MatrixBase(array);   
        other.rowReduce();
        System.out.println(other);
        assertTrue(false);
    } // end testAddMultipleRowNegativeSourceRow    
} // end MatrixBaseTest