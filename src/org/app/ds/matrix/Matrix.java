/**
 * 
 */
package org.app.ds.matrix;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * @author anandm
 * 
 */
public class Matrix {

	private int[][] matrix;

	private int rows;
	private int cols;

	/**
	 * @param rows
	 * @param cols
	 */
	public Matrix(int rows, int cols) {
		super();
		if (rows < 0 || cols < 0) {
			throw new IllegalArgumentException(
					"invalid matrix dimensions rows : " + rows + ", cols : "
							+ cols);
		}
		this.rows = rows;
		this.cols = cols;
		this.matrix = new int[this.rows][this.cols];
	}

	/**
	 * 
	 * 
	 * @param matrix
	 */
	public Matrix(int[][] matrix) {
		super();

		checkMatrix(matrix);

		this.matrix = matrix;
		this.rows = this.matrix.length;
		this.cols = this.matrix[0].length;
	}

	private static final void checkMatrix(int[][] matrix) {
		if (matrix == null) {
			throw new IllegalArgumentException("matrix can't be null");
		}

		int lastArrayLength = -1;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] == null) {
				throw new IllegalArgumentException("invalid matrix");
			}

			if (lastArrayLength == -1) {
				lastArrayLength = matrix[i].length;
			}

			if (lastArrayLength != matrix[i].length) {
				throw new IllegalArgumentException("invalid matrix");
			}

		}
	}

	private void checkRange(int row, int col) {
		if ((row < 0 || row >= this.rows) || (col < 0 || col >= this.cols)) {
			throw new IllegalArgumentException("invalid range row : " + row
					+ ", col : " + col);
		}
	}

	public void set(int row, int col, int value) {
		checkRange(row, col);

		matrix[row][col] = value;
	}

	public int get(int row, int col) {
		checkRange(row, col);

		return matrix[row][col];
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public Matrix add(Matrix matrixToAdd) {
		if (rows != matrixToAdd.rows || cols != matrixToAdd.cols) {
			throw new IllegalArgumentException(
					"matrices have different dimensions");
		}

		int[][] result = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i][j] = matrix[i][j] + matrixToAdd.matrix[i][j];
			}
		}

		return new Matrix(result);
	}

	public Matrix subtract(Matrix matrixToSubtract) {
		if (rows != matrixToSubtract.rows || cols != matrixToSubtract.cols) {
			throw new IllegalArgumentException(
					"matrices have different dimensions");
		}

		int[][] result = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i][j] = matrix[i][j] - matrixToSubtract.matrix[i][j];
			}
		}

		return new Matrix(result);
	}

	public Matrix scale(int scalar) {
		int[][] result = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i][j] = scalar * matrix[i][j];
			}
		}

		return new Matrix(result);
	}

	public Matrix multiply(Matrix matrixToMultiplyWith) {

		if (cols != matrixToMultiplyWith.rows) {
			throw new IllegalArgumentException("matrix must have " + cols
					+ " rows");
		}

		int[][] result = new int[rows][matrixToMultiplyWith.cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < matrixToMultiplyWith.cols; j++) {

				int value = 0;
				for (int k = 0; k < cols; k++) {
					value = value
							+ (this.matrix[i][k] * matrixToMultiplyWith.matrix[k][j]);
				}
				result[i][j] = value;
			}
		}

		return new Matrix(result);
	}

	public Matrix transpose() {
		int[][] result = new int[cols][rows];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[j][i] = matrix[i][j];
			}
		}

		return new Matrix(result);
	}

	public void print(OutputStream stream) {
		PrintStream printStream = new PrintStream(stream);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				printStream.print(matrix[i][j]);
				printStream.print(" ");
			}
			printStream.println();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cols;
		result = prime * result + Arrays.hashCode(matrix);
		result = prime * result + rows;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Matrix other = (Matrix) obj;
		if (cols != other.cols) {
			return false;
		}
		if (!Arrays.deepEquals(matrix, other.matrix)) {
			return false;
		}
		if (rows != other.rows) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Matrix prices = new Matrix(new int[][] { { 3, 4, 2 } });

		Matrix quantities = new Matrix(new int[][] { { 13, 9, 7, 15 },
				{ 8, 7, 4, 6 }, { 6, 4, 0, 3 } });

		prices.print(System.out);

		quantities.print(System.out);

		prices.multiply(quantities).print(System.out);

		Matrix pricesOne = new Matrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } });

		Matrix quantitiesOne = new Matrix(new int[][] { { 7, 8 }, { 9, 10 },
				{ 11, 12 } });

		pricesOne.print(System.out);

		quantitiesOne.print(System.out);

		pricesOne.multiply(quantitiesOne).print(System.out);

		System.out.println(pricesOne.multiply(quantitiesOne).equals(
				new Matrix(new int[][] { { 58, 64 }, { 139, 154 } })));

		quantities.transpose().print(System.out);
	}

}
