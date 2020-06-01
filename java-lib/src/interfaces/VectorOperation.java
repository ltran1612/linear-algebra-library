/**
 * Interface for a vector operation
 */
public interface VectorOperation extends MatrixForm {
    public void transform(MatrixForm transformMatrix);
}