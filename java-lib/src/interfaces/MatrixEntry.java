package linear_algebra.interfaces;

public interface MatrixEntry extends Cloneable{
    public MatrixEntry add(Object other);
    public MatrixEntry substract(Object other);
    public MatrixEntry multiply(Object other);
    public MatrixEntry multiply(double scalar);
    public MatrixEntry divide(Object other);
    public boolean equals(Object other);
    public boolean isZero();
    public String toString();
    public MatrixEntry clone();
} // end MatrixEntry