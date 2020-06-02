# linear-algebra-library
This project is about building a simple linear algebra from everything I have learned in MATH280 - Introduction to Linear Algebra. 

The key point in this library is the ability to expand easily. You can design your own type of matrix entry without having to change the implementation of a matrix, and vice versa. 

With this, you can implement matrix entries of data types that are not available in the language like irrational number, polynomial, rational number, etc as long as they match the pre-defiend condition of a matrix entry (using interfaces for example).

## Features

Matrix and vector multiplication

Matrix and vector addition

Matrix and vector scalar multiplication

Row reduction of a matrix

Elementary row operations: times a row, add multiple of one row to another, swap row.

Finding the solution of a matrix equation

Matrix transformation

Find the determinant of a matrix

Find the rank of a matrix

Expandability

## Java
The expandability is achieved through the use of interfaces for matrix and each type of matrix entry. 
### Files
The library written in Java is in linear-algebra-library/java-lib folder. In this folder, there are:
+ src/: Contain the source codes
  + interfaces/
    + MatrixEntry.java - Interface for an entry of a matrix
    + MatrixForm.java - Interface for a matrix
    + VectorOperation.java - Interface for a vector
  + DoubleEntry.java - The double value type for a matrix entry.
  + MatrixBase.java - The matrix which will have only one type of entry.
  + VectorBase.java - Extend from MatrixBase with some enforcment for vectors
  + OtherFunctionalities.java - Containing three functions to find the solution of a system of linear equations, find the rank and find the determinant of a matrix respectively.
  + PolynomialEntry.java - Used for testing the type checking in MatrixBase (not important).
  
+ tests/: Contain some of my unit test cases during the development

### To make jar file
Clone this project and go to the linear-algebra-library/java-lib folder. 
Two ways below will create a bin folder containing the class files of all java files in src folder. 

#### Using ant
    ant
    
or

    ant compile
    
#### Using java commands
    mkdir bin
    javac -d bin/ -sourcepath src/*.java src/interfaces/*.java

