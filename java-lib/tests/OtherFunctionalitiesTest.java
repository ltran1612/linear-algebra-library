import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.Arrays;

/**
 * Tests for public functions in OtherFunctionalitiesTest
 * Note: Tests for findSolution will always wrong because there are no exact answer due to the possibility of a different implementation of rowReduce and entries. Thus, it should be checked by itself.
 */
public class OtherFunctionalitiesTest {
    // 
    // Stuff you want to do before each test case
    //
    @BeforeEach
    public void setup() {
        System.out.println("\nTest starting...");
    }

    // Stuff you want to do after each test case
    //
    @AfterEach
    public void teardown() {
        System.out.println("\nTest finished.");
    }

    // test cases
// findSolution
    @Test
    public void testFindSolutionNull() {
        System.out.println("Testing testFindSolutionNull");
        assertThrows(IllegalArgumentException.class, () -> OtherFunctionalities.findSolution(null));
    } // end testFindSolutionNull

    @Test
    public void testFindSolutionEmpty() {
        System.out.println("Testing testFindSolutionEmpty");
        assertThrows(IllegalArgumentException.class, () -> OtherFunctionalities.findSolution(new MatrixBase(0, 0, DoubleEntry.class)));
    } // end testFindSolutionNull

    @Test
    public void testFindSolutionColumnGreaterThanRow() {
        System.out.println("Testing testFindSolutionColumnGreaterThanRow");
        DoubleEntry[][] mySystem = new DoubleEntry[2][];
        mySystem[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(1), new DoubleEntry(1), new DoubleEntry(25)};
        mySystem[1] = new DoubleEntry[] {new DoubleEntry(5), new DoubleEntry(3), new DoubleEntry(2), new DoubleEntry(0)};

        String[] result = OtherFunctionalities.findSolution(new MatrixBase(mySystem));
        System.out.println(Arrays.toString(result));
        assertTrue(result[0].equals("(-1.0*(--3.0*x2)/-2.0-1.0*x2)/1.0+-37.5") && result[1].equals("(--3.0*x2)/-2.0+62.5") && result[2].equals("x2"));
    }

    @Test
    public void testFindSolutionRowGreaterThanColumn() {
        System.out.println("Testing testFindSolutionRowGreaterThanColumn");
        DoubleEntry[][] mySystem = new DoubleEntry[3][];
        mySystem[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(1), new DoubleEntry(1)};
        mySystem[1] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(-1), new DoubleEntry(2)};
        mySystem[2] = new DoubleEntry[] {new DoubleEntry(2), new DoubleEntry(0), new DoubleEntry(3)};

        String[] result = OtherFunctionalities.findSolution(new MatrixBase(mySystem));
        System.out.println(Arrays.toString(result));
        assertTrue(result[0].equals("1.5") && result[1].equals("-0.5"));
    }

    @Test
    public void testFindSolutionSquare() {
        System.out.println("Testing testFindSolutionSquare");
        DoubleEntry[][] mySystem = new DoubleEntry[2][];
        mySystem[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(1), new DoubleEntry(1)};
        mySystem[1] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(-1), new DoubleEntry(2)};

        String[] result = OtherFunctionalities.findSolution(new MatrixBase(mySystem));
        System.out.println(Arrays.toString(result));
        assertTrue(result[0].equals("1.5") && result[1].equals("-0.5"));
    }

    @Test
    public void testFindSolutionNoSolution() {
        System.out.println("Testing testFindSolutionNoSolution");
        DoubleEntry[][] mySystem = new DoubleEntry[2][];
        mySystem[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(1), new DoubleEntry(1)};
        mySystem[1] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(1), new DoubleEntry(3)};

        String[] result = OtherFunctionalities.findSolution(new MatrixBase(mySystem));
        System.out.println(Arrays.toString(result));
        assertTrue(result == null);
    }

// findRank
    @Test
    public void testFindRankNull() {
        System.out.println("Testing testFindRankNull");
        assertThrows(IllegalArgumentException.class, () -> OtherFunctionalities.findRank(null));
    } // end testFindSolutionNull

    @Test
    public void testFindRankEmpty() {
        System.out.println("Testing testFindRankEmpty");
        assertThrows(IllegalArgumentException.class, () -> OtherFunctionalities.findRank(new MatrixBase(0, 0, DoubleEntry.class)));
    } // end testFindSolutionNull

    @Test
    public void testFindRankSquare() {
        System.out.println("Testing testFindRankSquare");
        DoubleEntry[][] mySystem = new DoubleEntry[2][];
        mySystem[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(1)};
        mySystem[1] = new DoubleEntry[] {new DoubleEntry(3), new DoubleEntry(4)};

        assertTrue(OtherFunctionalities.findRank(new MatrixBase(mySystem)) == 2);
    }

    @Test
    public void testFindRankRowGreaterThanColumn() {
        System.out.println("Testing testFindRankRowGreaterThanColumn");
        DoubleEntry[][] mySystem = new DoubleEntry[4][];
        mySystem[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(2), new DoubleEntry(3)};
        mySystem[1] = new DoubleEntry[] {new DoubleEntry(4), new DoubleEntry(5), new DoubleEntry(6)};
        mySystem[2] = new DoubleEntry[] {new DoubleEntry(7), new DoubleEntry(8), new DoubleEntry(9)};
        mySystem[3] = new DoubleEntry[] {new DoubleEntry(10), new DoubleEntry(11), new DoubleEntry(12)};

        assertTrue(OtherFunctionalities.findRank(new MatrixBase(mySystem)) == 2);
    }

    @Test
    public void testFindRankColumnGreaterThanRow() {
        System.out.println("Testing testFindRankColumnGreaterThanRow");
        DoubleEntry[][] mySystem = new DoubleEntry[3][];
        mySystem[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(1), new DoubleEntry(3), new DoubleEntry(3)};
        mySystem[1] = new DoubleEntry[] {new DoubleEntry(4), new DoubleEntry(5), new DoubleEntry(6), new DoubleEntry(3)};
        mySystem[2] = new DoubleEntry[] {new DoubleEntry(7), new DoubleEntry(8), new DoubleEntry(9), new DoubleEntry(3)};
        
        assertTrue(OtherFunctionalities.findRank(new MatrixBase(mySystem)) == 3);
    }

// findDeterminant
    @Test
    public void testFindDeterminantNull() {
        System.out.println("Testing testFindDeterminantNull");
        assertThrows(IllegalArgumentException.class, () -> OtherFunctionalities.findDeterminant(null));
    } // end testFindSolutionNull

    @Test
    public void testFindDeterminantEmpty() {
        System.out.println("Testing testFindDeterminantEmpty");
        assertThrows(IllegalArgumentException.class, () -> OtherFunctionalities.findDeterminant( new MatrixBase(0, 0, DoubleEntry.class)));
    } // end testFindSolutionNull
   
    @Test
    public void testFindDeterminantSquare() {
        System.out.println("Testing testFindDeterminantSquare");
        DoubleEntry[][] mySystem = new DoubleEntry[2][];
        mySystem[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(1)};
        mySystem[1] = new DoubleEntry[] {new DoubleEntry(3), new DoubleEntry(4)};

        assertTrue(OtherFunctionalities.findDeterminant(new MatrixBase(mySystem)).equals(new DoubleEntry(1)));
    }

    @Test
    public void testFindDeterminantZero() {
        System.out.println("Testing testFindDeterminantZero");
        DoubleEntry[][] mySystem = new DoubleEntry[3][];
        mySystem[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(2), new DoubleEntry(3)};
        mySystem[1] = new DoubleEntry[] {new DoubleEntry(4), new DoubleEntry(5), new DoubleEntry(6)};
        mySystem[2] = new DoubleEntry[] {new DoubleEntry(7), new DoubleEntry(8), new DoubleEntry(9)};

        assertTrue(OtherFunctionalities.findDeterminant(new MatrixBase(mySystem)).equals(new DoubleEntry(0)));
    }

    @Test
    public void testFindDeterminantNegative() {
        System.out.println("Testing testFindDeterminantNegative");
        DoubleEntry[][] mySystem = new DoubleEntry[4][];
        mySystem[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(3), new DoubleEntry(5), new DoubleEntry(9)};
        mySystem[1] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(3), new DoubleEntry(1), new DoubleEntry(7)};
        mySystem[2] = new DoubleEntry[] {new DoubleEntry(4), new DoubleEntry(3), new DoubleEntry(9), new DoubleEntry(7)};
        mySystem[3] = new DoubleEntry[] {new DoubleEntry(5), new DoubleEntry(2), new DoubleEntry(0), new DoubleEntry(9)};
       
        DoubleEntry result = ((DoubleEntry) OtherFunctionalities.findDeterminant(new MatrixBase(mySystem)));
        assertTrue(DoubleEntry.roundToDecimalNum(result.getValue(), 1) == -376);
    }

    @Test
    public void testFindDeterminantNotSquare() {
        System.out.println("Testing testFindDeterminantNotSquare");
        DoubleEntry[][] mySystem = new DoubleEntry[4][];
        mySystem[0] = new DoubleEntry[] {new DoubleEntry(1), new DoubleEntry(2), new DoubleEntry(3)};
        mySystem[1] = new DoubleEntry[] {new DoubleEntry(4), new DoubleEntry(5), new DoubleEntry(6)};
        mySystem[2] = new DoubleEntry[] {new DoubleEntry(7), new DoubleEntry(8), new DoubleEntry(9)};
        mySystem[3] = new DoubleEntry[] {new DoubleEntry(10), new DoubleEntry(11), new DoubleEntry(12)};

        assertThrows(IllegalArgumentException.class, () -> OtherFunctionalities.findDeterminant(new MatrixBase(mySystem)));
    }
}