/**
 * Class Damenproblem gives the ability to solve a "Damenproblem"
 * by applying a backtracking algorithm
 * 
 * Created by tom on 03.06.17.
 */

public class Damenproblem {

	/**
	 * Searches all solutions for the famous "Damenproblem"
	 * The problem: you want to place n queens on a chess board of the size n*n
	 * The solution: backtracking!
	 *
	 * Initializes an empty chess board and starts the main logic with
	 * backtracking
	 * 
	 * @param brettgroesse
	 *            size of the chess board
	 */
	public void damenProblem(int brettgroesse) {
		// initialize empty board
		int[][] brett = new int[brettgroesse][brettgroesse];
		for (int i = 0; i < brett.length; i++) {
			for (int j = 0; j < brett.length; j++) {
				brett[i][j] = 0;
			}
		}

		damenProblem(0, brett);
	}

	/**
	 * The main logic behind the damenProblem, explained above
	 * 
	 * @param spalte
	 *            the column, in which the next queen should be placed
	 * @param brett
	 *            the chess board, which probably has some queens on it already
	 */
	private boolean damenProblem(int spalte, int[][] brett) {
		// all queens are already placed, this is a solution!
		if (spalte >= brett.length) {
			return true;
		}
		// go through all lines in the column and try to place a queen
		for (int zeile = 0; zeile < brett.length; zeile++) {
			if (validPos(zeile, spalte, brett)) { // position is valid, place the queen
				brett[zeile][spalte] = 1;
				// if there is a solution for the next queens too, pass that on
				if (damenProblem(spalte + 1, brett)) {
					printBrett(brett);
				}
				// clear the placed queen again
				brett[zeile][spalte] = 0;
			}
		}
		return false;
	}

	/**
	 * checks if the given position on a given board is a
	 * valid position for a queen
	 * 
	 * @param zeile
	 *            the line to be check
	 * @param spalte
	 *            the column to be checked
	 * @param brett
	 *            the board, where we check the position
	 * @return true if we can place a queen on the given position, false if not
	 */
	public boolean validPos(int zeile, int spalte, int[][] brett) {
		// check if there is already a queen in the line
		for (int j = 0; j < spalte; j++) {
			if (brett[zeile][j] != 0) {
				return false;
			}
		}
		// check if there is a queen in the upper left diagonal
		for (int i = zeile, j = spalte; i >= 0 && j >= 0; i--, j--) {
			if (brett[i][j] != 0) {
				return false;
			}
		}
		// check for a queen in the lower left diagonal
		for (int i = zeile, j = spalte; i < brett.length && j >= 0; i++, j--) {
			if (brett[i][j] != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * print the board nicely formatted
	 * 
	 * @param brett
	 *            the board to be printed
	 */
	public void printBrett(int[][] brett) {
		for (int i = 0; i < brett.length; i++) {
			for (int j = 0; j < brett.length; j++) {
				System.out.print(brett[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Damenproblem d = new Damenproblem();
		d.damenProblem(6);
	}
}
