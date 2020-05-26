import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class DoubleEntryTest {
   // Data you need for each test case
   private DoubleEntry entry;

    // 
    // Stuff you want to do before each test case
    //
    @BeforeEach
    public void setup() {
        System.out.println("\nTest starting...");
        entry = new DoubleEntry(2);
        //
    }

    // Stuff you want to do after each test case
    //
    @AfterEach
    public void teardown() {
        System.out.println("\nTest finished.");
    }

    // test cases
// test add
    @Test
    public void testAddNull() {
        System.out.println("Testing testAddNull");
        assertThrows(IllegalArgumentException.class, () -> entry.add(null));
    } // end testAddNull

    @Test
    public void testAddNoneEntry() {
        System.out.println("Testing testAddNoneEntry");
        assertThrows(IllegalArgumentException.class, () -> entry.add(Double.valueOf(1)));
    } // end testAddNull

    @Test
    public void testAddPositive() {
        System.out.println("Testing testAddPositive");
        DoubleEntry other = new DoubleEntry(2.5);
        entry.add(other);
        assertTrue(entry.getValue().doubleValue() == 4.5);
    } // end testAddPositive

    @Test
    public void testAddPositiveBound() {
        System.out.println("Testing testAddPositiveBound");
        DoubleEntry other = new DoubleEntry(0.00001);
        entry.add(other);
        assertTrue(entry.getValue().doubleValue() == 2.00001);
    } // end testAddNegativeBound

    @Test
    public void testAddNegative() {
        System.out.println("Testing testAddNegative");
        DoubleEntry other = new DoubleEntry(-2.5);
        entry.add(other);
        assertTrue(entry.getValue().doubleValue() == -0.5);
    } // end testAddNegative

    @Test
    public void testAddNegativeBound() {
        System.out.println("Testing testAddNegativeBound");
        DoubleEntry other = new DoubleEntry(-0.00001);
        entry.add(other);
        assertTrue(entry.getValue().doubleValue() == 1.99999);
    } // end testAddNegativeBound

    @Test
    public void testAddZero() {
        System.out.println("Testing testAddZero");
        DoubleEntry other = new DoubleEntry(0);
        entry.add(other);
        assertTrue(entry.getValue().doubleValue() == 2);
    } // testAddZero



    
// test substraction
    @Test
    public void testSubstractNull() {
        System.out.println("Testing testSubstractNull");
        assertThrows(IllegalArgumentException.class, () -> entry.substract(null));
    } // end testSubstractNull

    @Test
    public void testSubstractNoneEntry() {
        System.out.println("Testing testSubstractNoneEntry");
        assertThrows(IllegalArgumentException.class, () -> entry.substract(Double.valueOf(1)));
    } // end testSubstractNull

    @Test
    public void testSubstractPositive() {
        System.out.println("Testing testSubstractPositive");
        DoubleEntry other = new DoubleEntry(2.5);
        entry.substract(other);
        assertTrue(entry.getValue().doubleValue() == -0.5);
    } // end testSubstractPositive

    @Test
    public void testSubstractPositiveBound() {
        System.out.println("Testing testSubstractPositiveBound");
        DoubleEntry other = new DoubleEntry(0.00001);
        entry.substract(other);
        assertTrue(entry.getValue().doubleValue() == 1.99999);
    } // end testSubstractNegativeBound

    @Test
    public void testSubstractNegative() {
        System.out.println("Testing testSubstractNegative");
        DoubleEntry other = new DoubleEntry(-2.5);
        entry.substract(other);
        assertTrue(entry.getValue().doubleValue() == 4.5);
    } // end testSubstractNegative

    @Test
    public void testSubstractNegativeBound() {
        System.out.println("Testing testSubstractNegativeBound");
        DoubleEntry other = new DoubleEntry(-0.00001);
        entry.substract(other);
        assertTrue(entry.getValue().doubleValue() == 2.00001);
    } // end testSubstractNegativeBound

    @Test
    public void testSubstractZero() {
        System.out.println("Testing testSubstractZero");
        DoubleEntry other = new DoubleEntry(0);
        entry.substract(other);
        assertTrue(entry.getValue().doubleValue() == 2);
    } // testSubstractZero




// test multiply
    @Test
    public void testMultiplyNull() {
        System.out.println("Testing testMultiplyNull");
        assertThrows(IllegalArgumentException.class, () -> entry.multiply(null));
    } // end testMultiplyNull

    @Test
    public void testMultiplyNoneEntry() {
        System.out.println("Testing testMultiplyNoneEntry");
        assertThrows(IllegalArgumentException.class, () -> entry.multiply(Double.valueOf(1)));
    } // end testMultiplyNull

    @Test
    public void testMultiplyPositive() {
        System.out.println("Testing testMultiplyPositive");
        DoubleEntry other = new DoubleEntry(2.5);
        entry.multiply(other);
        assertTrue(entry.getValue().doubleValue() == 5);
    } // end testMultiplyPositive

    @Test
    public void testMultiplyPositiveBound() {
        System.out.println("Testing testMultiplyPositiveBound");
        DoubleEntry other = new DoubleEntry(0.00001);
        entry.multiply(other);
        assertTrue(entry.getValue().doubleValue() == 0.00002);
    } // end testMultiplyNegativeBound

    @Test
    public void testMultiplyNegative() {
        System.out.println("Testing testMultiplyNegative");
        DoubleEntry other = new DoubleEntry(-2.5);
        entry.multiply(other);
        assertTrue(entry.getValue().doubleValue() == -5);
    } // end testMultiplyNegative

    @Test
    public void testMultiplyNegativeBound() {
        System.out.println("Testing testMultiplyNegativeBound");
        DoubleEntry other = new DoubleEntry(-0.00001);
        entry.multiply(other);
        assertTrue(entry.getValue().doubleValue() == -0.00002);
    } // end testMultiplyNegativeBound

    @Test
    public void testMultiplyZero() {
        System.out.println("Testing testMultiplyZero");
        DoubleEntry other = new DoubleEntry(0);
        entry.multiply(other);
        assertTrue(entry.getValue().doubleValue() == 0);
    } // testMultiplyZero



// test division
    @Test
    public void testDivideNull() {
        System.out.println("Testing testDivideNull");
        assertThrows(IllegalArgumentException.class, () -> entry.divide(null));
    } // end testDivideNull

    @Test
    public void testDivideNoneEntry() {
        System.out.println("Testing testDivideNoneEntry");
        assertThrows(IllegalArgumentException.class, () -> entry.divide(Double.valueOf(1)));
    } // end testDivideNull

    @Test
    public void testDividePositive() {
        System.out.println("Testing testDividePositive");
        DoubleEntry other = new DoubleEntry(2.5);
        entry.divide(other);
        assertTrue(entry.getValue().doubleValue() == 0.8);
    } // end testDividePositive

    @Test
    public void testDividePositiveBound() {
        System.out.println("Testing testDividePositiveBound");
        DoubleEntry other = new DoubleEntry(0.00001);
        entry.divide(other);
       
        assertTrue(entry.getValue().doubleValue() == 200000);
    } // end testDivideNegativeBound

    @Test
    public void testDivideNegative() {
        System.out.println("Testing testDivideNegative");
        DoubleEntry other = new DoubleEntry(-2.5);
        entry.divide(other);
        assertTrue(entry.getValue().doubleValue() == -0.8);
    } // end testDivideNegative

    @Test
    public void testDivideNegativeBound() {
        System.out.println("Testing testDivideNegativeBound");
        DoubleEntry other = new DoubleEntry(-0.00001);
        entry.divide(other);
        assertTrue(entry.getValue().doubleValue() == -200000);
    } // end testDivideNegativeBound

    @Test
    public void testDivideZero() {
        System.out.println("Testing testDivideZero");
        DoubleEntry other = new DoubleEntry(0);
        
        assertThrows( IllegalArgumentException.class, () -> entry.divide(other));
    } // testDivideZero

// test roundToDecimalNum
    @Test
    public void testRoundToDecimalNumPositiveNumRoundedUp() {
        System.out.println("Testing testRoundToDecimalNumPositiveNumRoundedUp");
        assertTrue(DoubleEntry.roundToDecimalNum(Double.valueOf(12.12345), 4) == 12.1235);
    } // end testRoundToDecimalNumPositiveNumRoundedUp

    @Test
    public void testRoundToDecimalNumPositiveNumRoundedUnchanged() {
        System.out.println("Testing testRoundToDecimalNumPositiveNumRoundedDown");
        assertTrue(DoubleEntry.roundToDecimalNum(Double.valueOf(12.12344), 4).equals(Double.valueOf(12.1234)));
    } // end testRoundToDecimalNumPositiveNumRoundedDown

    @Test
    public void testRoundToDecimalNumNegativeNumRoundedUp() {
        System.out.println("Testing testRoundToDecimalNumNegativeNumRoundedUp");
        assertTrue(DoubleEntry.roundToDecimalNum(Double.valueOf(-12.12345), 4).equals(Double.valueOf(-12.1235)));
    } // end testRoundToDecimalNumNegativeNumRoundedUp

    @Test
    public void testRoundToDecimalNumNegativeNumRoundedDown() {
        System.out.println("Testing testRoundToDecimalNumNegativeNumRoundedDown");
        assertTrue(DoubleEntry.roundToDecimalNum(Double.valueOf(-12.12344), 4).equals(Double.valueOf(-12.1234)));
    } // end testRoundToDecimalNumNegativeNumRoundedDown

    @Test
    public void testRoundToDecimalNumZeroDecimalPlaceRoundedUp() {
        System.out.println("Testing testRoundToDecimalNumZeroDecimalPlaceRoundedUp");
        assertTrue(DoubleEntry.roundToDecimalNum(Double.valueOf(12.52345), 0).equals(Double.valueOf(13)));
    } // end testRoundToDecimalNumZeroDecimalPlaceRoundedUp

    @Test
    public void testRoundToDecimalNumZeroDecimalPlaceRoundedDown() {
        System.out.println("Testing testRoundToDecimalNumZeroDecimalPlaceRoundedDown");
        assertTrue(DoubleEntry.roundToDecimalNum(Double.valueOf(12.12345), 0).equals(Double.valueOf(12)));
    } // end testRoundToDecimalNumZeroDecimalPlaceRoundedDown

    @Test
    public void testRoundToDecimalNumNegativeDecimalPlace() {
        System.out.println("Testing testRoundToDecimalNumZeroDecimalPlace");
        assertThrows(IllegalArgumentException.class, () -> DoubleEntry.roundToDecimalNum(Double.valueOf(2.1232), -1));
    } // end testRoundToDecimalNumZeroDecimalPlace
} // end DoubleEntryTest