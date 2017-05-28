package Blatt08;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by tom on 23.05.17.
 */
public class Graph {
    //Adjacentliste
    private ArrayList<Integer>[] graph;

    /**
     * Erzeugt einen leeren Graphen mit v Knoten
     * @param v Anzahl der Knoten
     */
    public Graph(int v){
        this.graph = new ArrayList[v];
        for(int i=0; i<=v; i++){
            graph[i] = new ArrayList<Integer>(0);
        }
    }

    /**
     * Erzeugt einen Graphen ausgehend von Kantenliste
     * @param list Kantenliste
     */
    public Graph(int[] list){
        this.graph = new ArrayList[list[0]+1];
        for(int i=0; i<=list[0]; i++){
            graph[i] = new ArrayList<Integer>(0);
        }
        for(int i=2; i<list.length; i+=2){
            graph[list[i]].add(list[i+1]);
        }
    }

    /**
     * gibt die Anzahl der Knoten zurück
     * @return Anzahl der Knoten
     */
    public int getVertexCount(){
        return graph.length -1;
    }


    /**
     * gibt die Anzahl der Kanten zurück
     * @return Anzahl der Kanten
     */
    public int getEdgeCount(){
        int ret = 0;
        for(int i=0; i<graph.length; i++){
            ret += graph[i].size();
        }
        return ret -1;
    }

    /**
     * Fügt eine neue Kante vom Konten from zum Knoten to zum Graph hinzu
     * @param from Knoten
     * @param to Knoten
     */
    public void addEdge(int from, int to){
        graph[from].add(to);
    }

    /**
     * Gibt die Kanten ausgehend vom Knoten v ausgehend zurück
     * @param v Knoten
     * @return Adjacentliste
     */
    public ArrayList<Integer> getAdjacent(int v){
        return graph[v];
    }


    /**
     *
     * @return
     */
    public String toString(){
        String ret = "[ ";
        for(int i=1; i<graph.length; i++){
            ret += graph[i] + " ";
        }
        return ret + "]";
    }


    //private ArrayList für die Tiefensuche
    private ArrayList<Integer> dfs;

    //private rekursive Methode für die Tiefensuche
    private void dfsRek(int node){

        ArrayList<Integer> nodes = graph[node];
        Collections.sort(nodes);
        dfs.add(node);
        for(int i = 0; i < nodes.size() ;i++){
            if(!dfs.contains(nodes.get(i))){
                dfsRek(nodes.get(i));
            }
        }


    }

    /**
     * Tiefensuche durch den Graphen ausgehend vom Knoten start
     * @param start Knoten
     * @return Knotenliste sortiert nach Tifensuche
     */
    public ArrayList<Integer> dfs(int start){
        dfs = new ArrayList<>();
        dfsRek(start);
        return dfs;
    }


    /**
     * Breitensuche durch den Graphen ausgehend vom Knoten start
     * @param start Knoten
     * @return Knotenliste sortiert nach Breitensuche
     */
    public ArrayList<Integer> bfs(int start){
        ArrayList<Integer> ret = new ArrayList<>();
        ArrayList<Integer> nodes = new ArrayList<>();
        nodes.add(start);
        while(nodes.size() > 0){
            ArrayList<Integer> newNodes = new ArrayList<>();
            for(Integer i : nodes){
                if(!ret.contains(i)){
                    ret.add(i);
                    newNodes.addAll(graph[i]);
                }
            }
            Collections.sort(newNodes);
            nodes = newNodes;
        }

        return ret;
    }

   //Test
    public static void main(String[] args) {
        int[] vList = {6,10,1,5,1,4,2,3,2,6,3,4,3,5,4,5,4,2,5,6,6,1};
        Graph g = new Graph(vList);
        System.out.println(g.dfs(1));
        System.out.println(g.bfs(1));
    }
}
