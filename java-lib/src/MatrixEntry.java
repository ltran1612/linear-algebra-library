public interface MatrixEntry<T> {
    public void add(Object other);
    public void substract(Object other);
    public void multiply(Object other);
    public void divide(Object other);

    public T getValue();
} // end MatrixEntry