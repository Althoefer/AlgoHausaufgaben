package Hausaufgaben09;

import java.util.Arrays;

/**
 * Created by tom on 03.06.17.
 */


public class Damenproblem{

    /**
     * Searches all solutions for the famous "Damenproblem"
     * The problem:
     *      you want to place n queens on a chess board of the size n*n
     * The solution:
     *      backtracking!
     *
     * Initializes a empty chess board and starts the main logic with backtracking
     * @param brettgroesse      size of the chess board
     */
    public void damenProblem(int brettgroesse){
        //initialize empty board
        int[][] brett = new int[brettgroesse][brettgroesse];
        for(int i = 0; i<brett.length; i++){
            for(int j = 0; j<brett.length; j++){
                brett[i][j] = 0;
            }
        }

        damenProblem(0, brett);
    }

    /**
     * The main logic behind the damenProblem, explained above
     * @param spalte    the column, in which the next queen should be placed
     * @param brett     the chess board, which probalbly has some queens on it already
     */
    private boolean damenProblem(int spalte, int[][] brett){
        //all queens are already placed, this is a solution!
        if(spalte >= brett.length){
            //printBrett(brett);
            return true;
        }
        //go through all lines in the column and try to place a queen
        for(int i=0; i<brett.length; i++){
            if(validPos(spalte, i, brett)){ //position is valid, place the queen
                brett[spalte][i] = 1;
                //if there is a solution for the next queens too, pass that on
                if(damenProblem(spalte+1, brett)){
                    printBrett(brett);
                }
                //clear the placed queen again
                brett[spalte][i] = 0;
            }
        }
        return false;
    }

    /**
     * checks if the given position on a given board is a valid position for a queen
     * @param spalte    the column to be check
     * @param zeile     the line to be checked
     * @param brett     the board, where we check the position
     * @return          true if we can place a queen on the given position,
     *                  false if not
     */
    public boolean validPos(int spalte, int zeile, int[][] brett){
        //check if there is already a queen in the line
        for(int j = 0; j < spalte; j++){
            if(brett[j][zeile] != 0){
                return false;
            }
        }
        int i = zeile;
        int j = spalte;
        //check if there is a queen in the upper left diagonal
        for (; i >= 0 && j >= 0; i--, j--){
            if (brett[j][i] != 0) {
                return false;
            }
        }
        i = zeile;
        j = spalte;
        //check for a queen in the lower left diagonal
        for (; i<brett.length && j >= 0; i++, j--){
            if (brett[j][i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * print the board nicely formated
     * @param brett     the board to be printed
     */
    public void printBrett(int[][] brett){
        for(int i=0; i<brett.length; i++){
            for(int j=0; j<brett.length; j++){
                System.out.print(brett[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Damenproblem d = new Damenproblem();
        d.damenProblem(5);
    }
}
