import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DoubleEntry implements MatrixEntry<Double> {
    private Double value;
    private int roundDecimalNum;

    /**
     * Create a DoubleEntry with value 0 with 8 decimal places to which the entry will be rounded when doing math operations
     */
    public DoubleEntry() {
        value = Double.valueOf(0);
        roundDecimalNum = 8;
    } // end DoubleEntry

    /**
     * Create a DoubleEntry with parameter value and with parameter decimal places to which the entry will be rounded when doing math operations
     * @param _value The value of the DoubleEntry
     * @param _roundDecimalNum A positive decimal place to which the entry will be rounded when doing math operations
     * @throws IllegalArgumentException When the round decimal num is negative
     */
    public DoubleEntry(double _value, int _roundDecimalNum) {
        if (_roundDecimalNum < 0)
            throw new IllegalArgumentException("roundDecimalNum cannot be negative");

        value = Double.valueOf(_value);
        roundDecimalNum = _roundDecimalNum;
    } // end DoubleEntry

    /**
     * Create a DoubleEntry with parameter value with 8 decimal places to which the entry will be rounded when doing math operations
     * @param _value The value of the entry
     */
    public DoubleEntry(double _value) {
        value = Double.valueOf(_value);
        roundDecimalNum = 8;
    } // end DoubleEntry

    // took the core mechanism from curtisk https://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
    /**
     * Round the number to the expected digit
     * @param num The number to be rounded
     * @param roundDecimalNum A positive decimal place at which the number will be rounded. 
     * @return The rounded number
     * @throws IllegalArgumentException When the the round decimal place is negative and when num parameter is null
     */
    public static Double roundToDecimalNum(Double num, int roundDecimalNum) {
        if (num == null)
            throw new IllegalArgumentException("parameter num cannot be null");
        if (roundDecimalNum < 0)
            throw new IllegalArgumentException("roundDecimalNum cannot be negative");

        String format = "#";
        if (roundDecimalNum != 0) {
            format += ".";
            for (int i = 0; i < roundDecimalNum; ++i)
                format += "#";
        } // end if

        DecimalFormat df = new DecimalFormat(format);
        df.setRoundingMode(RoundingMode.HALF_UP);  
        return Double.valueOf(df.format(num));
    } // end round

    /**
     * Add the value of a DoubleEntry (in the parameter) into the value of this entry and round the value to the decimal place specified in the constructor.
     * @param other The DoubleEntry that will be added into this entry
     * @throws IllegalArgumentException When the parameter is null or it is not a DoubleEntry object
     */
    public void add(Object other) {
        if (other == null)
            throw new IllegalArgumentException("The right operand cannot be null");
        if (!(other instanceof DoubleEntry))
            throw new IllegalArgumentException("The right operand has to be of type DoubleEntry");
        
        DoubleEntry entry = (DoubleEntry) other;
        value = Double.valueOf(value.doubleValue() + entry.getValue().doubleValue());
    } // end sum

    /**
     * Substract the value of a DoubleEntry (in the parameter) from the value of this entry and round the value to the decimal place specified in the constructor.
     * @param other The DoubleEntry that will be substracted into this entry
     * @throws IllegalArgumentException When the parameter is null or it is not a DoubleEntry object
     */
    public void substract(Object other) {
        if (other == null)
            throw new IllegalArgumentException("The right operand cannot be null");
        if (!(other instanceof DoubleEntry))
            throw new IllegalArgumentException("The right operand has to be of type DoubleEntry");
        
        DoubleEntry entry = (DoubleEntry) other;
        value = Double.valueOf(value.doubleValue() - entry.getValue().doubleValue());
    }

    /**
     * Multiply the value of this entry with the value of a DoubleEntry (in the parameter) and round the value to the decimal place specified in the constructor.
     * @param other The DoubleEntry that this entry will multiply with.
     * @throws IllegalArgumentException When the parameter is null or it is not a DoubleEntry object
     */
    public void multiply(Object other) {
        if (other == null)
            throw new IllegalArgumentException("The right operand cannot be null");
        if (!(other instanceof DoubleEntry))
            throw new IllegalArgumentException("The right operand has to be of type DoubleEntry");
        
        DoubleEntry entry = (DoubleEntry) other;
        value = Double.valueOf(value.doubleValue() * entry.getValue().doubleValue());
    }

    /**
     * Divide the value of this entry with the value of a DoubleEntry (in the parameter) and round the value to the decimal place specified in the constructor.
     * @param other The DoubleEntry that this entry will be divided by.
     * @throws IllegalArgumentException When the parameter is null or it is not a DoubleEntry object, or it has value 0.
     */
    public void divide(Object other) {
        if (other == null)
            throw new IllegalArgumentException("The right operand cannot be null");
        if (!(other instanceof DoubleEntry))
            throw new IllegalArgumentException("The right operand has to be of type DoubleEntry");
        
        DoubleEntry entry = (DoubleEntry) other;
        if (entry.getValue().doubleValue() == 0) 
            throw new IllegalArgumentException("DoubleEntry cannot be divided by 0");

        value = roundToDecimalNum(Double.valueOf(value.doubleValue() / entry.getValue().doubleValue()), roundDecimalNum);
    }

    /**
     * Return the value of the entry
     * @return the value of the entry.
     */
    public Double getValue() {
        return value;
    }
} // end DoubleEntry