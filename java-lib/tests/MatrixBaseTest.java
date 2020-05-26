import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

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
        
} // end MatrixBaseTest