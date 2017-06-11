package Hausaufgaben10;

import java.util.Arrays;

/**
 * Created by tom on 10.06.17.
 */
public class Dijkstra {
    public static void printDijkstra(int[] kanten){
        int[] shortest = new int[kanten[0]+1];    //initialize the table on the left
        for(int i=1; i<shortest.length; i++){
            shortest[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<kanten[0]; i++){                 //run once for every node
            //now we have to check all node connecting to our current node
            for(int j=1; j<kanten.length ; j += 3){     //runs once for every vertex
                if(kanten[j] == i){                     //the edge starts at our node at i
                    if(shortest[kanten[j+1]] > kanten[j+2] + shortest[i]){  //the current weight is bigger than the new one+1
                        shortest[kanten[j+1]] = kanten[j+2] + kanten[i];
                    }
                }
            }
            print(i, shortest);
        }
    }

    public static void print(int pos, int[] shortest){
        System.out.printf("%3d|", pos);
        for(int i : shortest){
            if(i == Integer.MAX_VALUE){
                System.out.printf("%3s", "--");
            } else{
                System.out.printf("%3d", i);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printDijkstra(new int[] {4,1,2,2,1,4,5,2,4,1,2,3,4,3,1,1,4,3,1});
        System.out.println();
        printDijkstra(new int[] {10,1,2,30,1,3,10,2,5,15,2,8,55,3,4,5,3,9,35,4,2,10,4,5,45,4,6,10,5,3,20,5,7,15,5,9,25,6,7,5,7,10,20,8,10,15,9,8,10,9,10,30});

    }
}
