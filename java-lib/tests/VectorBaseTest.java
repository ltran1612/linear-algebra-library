import linear_algebra.classes.*;
import linear_algebra.interfaces.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class VectorBaseTest {
    // Data you need for each test case
    private VectorBase vector;

   
    // 
    // Stuff you want to do before each test case
    //
    @BeforeEach
    public void setup() {
        System.out.println("\nTest starting...");
        vector = new VectorBase(2, DoubleEntry.class);
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
        DoubleEntry[] entries = new DoubleEntry[2];
        entries[0] = new DoubleEntry(1);
        entries[1] = new DoubleEntry(2);

        VectorBase other = new VectorBase(entries);
        System.out.println(other);
        for (int i = 0; i < 2; ++i) {
            MatrixEntry entry = other.getEntry(i);
            if (!entry.equals(new DoubleEntry(++i)))
                assertTrue(false);
        } // end for i

        assertTrue(true);
    } // end testArrayConstructor

// transform
    @Test
    public void testTransformNull() {
        System.out.println("Testing testTransformNull");
        assertThrows(IllegalArgumentException.class, () -> vector.transform(null));
    } // end testArrayConstructor

    @Test
    public void testTransformWrongType() {
        System.out.println("Testing testTransformWrongType");
        PolynomialEntry[][] matrix = new PolynomialEntry[2][2];
        matrix[0][0] = new PolynomialEntry();
        matrix[1][1] = new PolynomialEntry();    
        matrix[1][0] = new PolynomialEntry();    
        matrix[0][1] = new PolynomialEntry();    

        assertThrows(IllegalArgumentException.class, () -> vector.transform(new MatrixBase(matrix)));
    } // end testArrayConstructor

    @Test
    public void testTransformNormal() {
        System.out.println("Testing testTransformNormal");
        DoubleEntry[][] matrix = new DoubleEntry[2][2];
        matrix[0][0] = new DoubleEntry(1);
        matrix[1][1] = new DoubleEntry(1);
        matrix[1][0] = new DoubleEntry(1);
        matrix[0][1] = new DoubleEntry(1);

        vector.setEntry(0, new DoubleEntry(1));
        vector.setEntry(1, new DoubleEntry(1));

        vector.transform(new MatrixBase(matrix));
        System.out.println(vector);
        assertTrue(vector.getEntry(0).equals(new DoubleEntry(2)) &&
                    vector.getEntry(1).equals(new DoubleEntry(2)));
    } // end testArrayConstructor

}