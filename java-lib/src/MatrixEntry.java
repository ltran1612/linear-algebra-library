public interface MatrixEntry extends Cloneable{
    public void add(Object other);
    public void substract(Object other);
    public void multiply(Object other);
    public void divide(Object other);
    public boolean equals(Object other);
    public boolean isZero();
    public String toString();
    public MatrixEntry clone();
} // end MatrixEntry