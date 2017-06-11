package LA;

import javax.swing.*;
import java.util.Arrays;

public class QuadMatrix{
    private double[][] a;

    /**
     * creates a new QuadMatrix from a double array
     * throws IllegalArgumentException when the array is not square
     * @param b
     */
    public QuadMatrix(double[][] b){
        for(int i=0; i<b.length; i++){
            if(b[i].length != b.length){
                throw new IllegalArgumentException("The given array is not square, line " + i + " has different dimension");
            }
        }
        //array is square
        a = new double[b.length][b.length];
        for(int j=0; j<b.length; j++){
            for(int k=0; k<b.length; k++){
                a[j][k] = b[j][k];
            }
        }
    }

    /**
     * creates a new QuadMatrix from another QuadMatrix
     * @param qm
     */
    public QuadMatrix(QuadMatrix qm){
        this.a = new double[qm.a.length][qm.a.length];
        for(int j=0; j<qm.a.length; j++){
            for(int k=0; k<a.length; k++){
                a[j][k] = qm.a[j][k];
            }
        }
    }

    /**
     *
     * @return the dimension of the matrix
     */
    public int getDim(){
        return a.length;
    }

    public String toString(){
        String ret = "(";
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a.length; j++){
                ret = ret + (int)(a[i][j]) + " ";
            }
            ret = ret.substring(0, ret.length()-1) + "; ";
        }
        return ret.substring(0, ret.length() -2) + ")";
    }

    public QuadMatrix addiere(QuadMatrix qm){
        if(this.a.length != qm.a.length){
            throw new IllegalArgumentException("Dimensions are not equal");
        }
        double[][] x = new double[a.length][a.length];
        for(int i=0; i<x.length; i++){
            for(int j=0; j<x.length; j++){
                x[i][j] = qm.a[i][j] + this.a[i][j];
            }
        }
        return new QuadMatrix(x);

    }

    public QuadMatrix multipliziere(double lambda){
        double[][] x = new double[a.length][a.length];
        for(int i=0; i<x.length; i++){
            for(int j=0; j<x.length; j++){
                x[i][j] = this.a[i][j] * lambda;
            }
        }
        return new QuadMatrix(x);
    }

    public double det_laplace(){
        return det_laplace(this.a);
    }

    public static double det_laplace(double[][] matrix){

        double ret = 0;

        if(matrix.length == 1){
            return matrix[0][0];
        } else {

            double[][] temp = new double[matrix.length - 1][matrix.length - 1];

            for (int i = 0; i < matrix.length; i++) {             //lauft für jede "Untermatrix" 1x mal

                if (i == 0) {                                 //erste Spalte fällt weg
                    for (int j = 1; j < matrix.length; j++) {
                        for (int k = 1; k < matrix.length; k++) {
                            temp[j - 1][k - 1] = matrix[j][k];
                            //System.out.println("i= " + i + " j= " + j + " k= " + k);

                        }
                    }
                } else if (i == matrix.length - 1) {           //letzte Spalte fällt weg
                    for (int j = 1; j < matrix.length; j++) {
                        for (int k = 0; k < matrix.length - 1; k++) {
                            temp[j - 1][k] = matrix[j][k];
                            //System.out.println("i= " + i + " j= " + j + " k= " + k);

                        }
                    }
                } else {                                        //Zeile in der Mitte fällt weg
                    for (int j = 1; j < matrix.length; j++) {
                        for (int k = 0; k < matrix.length -1; k++) {
                            //System.out.println("i= " + i + " j= " + j + " k= " + k);
                            if (k >= i) {
                                temp[j - 1][k] = matrix[j][k+1];
                                //System.out.println(matrixes[i][j][k - 1] + " " + matrix[j][k]);
                            } else {
                                temp[j - 1][k] = matrix[j][k];
                            }
                            //System.out.println("i= " + i + " j= " + j + " k= " + k);
                        }
                    }
                }

                if (i % 2 == 0) {
                    ret += matrix[0][i] * det_laplace(temp);
                } else {
                    ret -= matrix[0][i] * det_laplace(temp);
                }
            }

            return ret;

        }
    }

    public static QuadMatrix randomMatrix(int n){
        double[][] random = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                random[i][j] = (double) (int) (Math.random()*(8)-4);
            }
        }
        return new QuadMatrix(random);
    }

    public double[] loeseLGS(double[] b){
        //check for dimensions
        if(b.length != a.length){
            throw new IllegalArgumentException("Dimension of b[] does not match the dimension of a[][]!");
        }
        double detA = det_laplace(a);

        double[] x = new double[b.length];              //solution
        double[][] a1 = new double[a.length][a.length]; //copy of a[][]

        for(int i=0; i<a.length; i++){  //make a working copy of a
            System.arraycopy(a[i], 0, a1[i], 0, b.length);
        }

        for(int i=0; i<a.length; i++){
            //copy b into the column at i
            for(int j=0; j<a.length; j++){
                a1[i][j] = b[j];
            }
            x[i] = det_laplace(a1) / detA;
            //make it a copy of a[][] again, dont want to write a extra method
            for(int j=0; j<a.length; j++){
                a1[i][j] = a[i][j];
            }
        }
        return x;
    }

    public static void main(String args[]){
        double[][] testA = {{-1, 0, -2},{4, 1, 0},{2, 0 ,1}};
        QuadMatrix a = new QuadMatrix(testA);
        double[] testB = {9,2,2};
        System.out.println(Arrays.toString(a.loeseLGS(testB)));
    }





    public static void test_laplace(){
        double[][] a = {{5, -3, 4}, {-1, 2, 0}, {-6, 3 ,1}};
        QuadMatrix A = new QuadMatrix(a);

        QuadMatrix B = QuadMatrix.randomMatrix(Integer.parseInt(JOptionPane.showInputDialog("Give me a number!")));
        System.out.println("Here is your random Matrix: \n" + B);

        QuadMatrix vier = QuadMatrix.randomMatrix(4);
        QuadMatrix sechs = QuadMatrix.randomMatrix(6);
        QuadMatrix acht = QuadMatrix.randomMatrix(8);
        QuadMatrix neun = QuadMatrix.randomMatrix(9);
        QuadMatrix zehn = QuadMatrix.randomMatrix(10);
        QuadMatrix elf = QuadMatrix.randomMatrix(11);
        QuadMatrix zwoelf = QuadMatrix.randomMatrix(12);


        System.out.println("Determinatne nach Laplace von c: " + A.det_laplace());
        long timeStart, timeEnd;

        timeStart = System.currentTimeMillis();
        vier.det_laplace();
        timeEnd = System.currentTimeMillis();
        System.out.println("n=4: " + (timeEnd - timeStart));

        timeStart = System.currentTimeMillis();
        sechs.det_laplace();
        timeEnd = System.currentTimeMillis();
        System.out.println("n=6: " + (timeEnd - timeStart));

        timeStart = System.currentTimeMillis();
        acht.det_laplace();
        timeEnd = System.currentTimeMillis();
        System.out.println("n=8: " + (timeEnd - timeStart));

        timeStart = System.currentTimeMillis();
        neun.det_laplace();
        timeEnd = System.currentTimeMillis();
        System.out.println("n=9: " + (timeEnd - timeStart));

        timeStart = System.currentTimeMillis();
        zehn.det_laplace();
        timeEnd = System.currentTimeMillis();
        System.out.println("n=10: " + (timeEnd - timeStart));

        timeStart = System.currentTimeMillis();
        elf.det_laplace();
        timeEnd = System.currentTimeMillis();
        System.out.println("n=11: " + (timeEnd - timeStart));

        timeStart = System.currentTimeMillis();
        zwoelf.det_laplace();
        timeEnd = System.currentTimeMillis();
        System.out.println("n=12: " + (timeEnd - timeStart));

    }




    public static void test_old(){
        double[][] a = {{1, 2, 3}, {1, 1, 2}, {3, 2, 2}};
        QuadMatrix A = new QuadMatrix(a);
        double[][] b = {{2, -1, 1}, {1, -2, 0}, {0, 3 ,1}};
        QuadMatrix B = new QuadMatrix(b);
        System.out.println("Dimension von A: " + A.getDim());
        System.out.println("Dimension von B: " + B.getDim());
        System.out.println("A zu b:");
        System.out.println(A.addiere(B));

        System.out.println("A mal 5:");
        System.out.println(A.multipliziere(5));
    }
}