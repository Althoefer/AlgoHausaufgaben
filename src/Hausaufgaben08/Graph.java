package Blatt08;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tom on 23.05.17.
 */
public class Graph {
    private ArrayList<Integer>[] graph;

    public Graph(int v){
        this.graph = new ArrayList[v];
        for(int i=0; i<v; i++){
            graph[i] = new ArrayList<Integer>(0);
        }
    }

    public Graph(int[] list){
        this.graph = new ArrayList[list[0]+1];
        for(int i=0; i<=list[0]; i++){
            graph[i] = new ArrayList<Integer>(0);
        }
        for(int i=2; i<list.length; i+=2){
            graph[list[i]].add(list[i+1]);
        }
    }

    public int getVertexCount(){
        return graph.length -1;
    }

    public int getEdgeCount(){
        int ret = 0;
        for(int i=0; i<graph.length; i++){
            ret += graph[i].size();
        }
        return ret -1;
    }

    public void addEdge(int from, int to){
        graph[from].add(to);
    }

    public ArrayList<Integer> getAdjacent(int v){
        return graph[v];
    }

    public String toString(){
        String ret = "[ ";
        for(int i=1; i<graph.length; i++){
            ret += graph[i] + " ";
        }
        return ret + "]";
    }

    public static void main(String[] args) {
        int[] vList = {6,10,1,5,1,4,2,3,2,6,3,4,3,5,4,5,4,6,5,6,6,4};
        Graph g = new Graph(vList);
        System.out.println(g);
        g.addEdge(5, 1);
        System.out.println(g);
        System.out.println("VertexCount:    " + g.getVertexCount());
        System.out.println("EdgeCount:      " + g.getEdgeCount());
        System.out.println("getAdjacent(1): " + g.getAdjacent(1));
    }
}
