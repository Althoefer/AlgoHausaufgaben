package src.Hausaufgaben01;

import java.util.ArrayList;

/**
 * Created by Tom on 04.04.2017.
 */
public class Triple {
    private int a,b,c = 0;

    public Triple(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String toString(){
        return "(" + a + "/" + b + "/" + c + ")";
    }

    public static ArrayList<Triple> getTriple(int n){
        ArrayList<Triple> list = new ArrayList<>();
        for(int i=0; i<n-3; i++){
            for(int j=i+1; j<n-2; j++){
                for(int k=j+1; k<n-1; k++){
                    if((i + j + k) == n) {
                        if ((i * i + j * j) == k * k) {
                            list.add(new Triple(i,j,k));
                        }
                    }
                }
            }
        }
        return list;
    }

    public static void main(String args[]){
        System.out.println(getTriple(30));
        System.out.println(getTriple(252));
    }
}