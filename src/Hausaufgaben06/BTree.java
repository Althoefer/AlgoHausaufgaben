package src.Hausaufgaben06;

import com.sun.javaws.exceptions.BadMimeTypeResponseException;

import java.util.ArrayList;

/**
 * Created by Manuel on 09.05.2017.
 */
public class BTree {
    private int d = 2;
    private BTreeNode root;

    public boolean search(int x){
        if(root == null)return false;
        if(searchNode(x, root) == null)return true;
        return false;
    }


    private BTreeNode searchNode(int x, BTreeNode node){
        int pos = 0;
        for(int value : node.values){
            if(value == x) return null;
            if(value < x){
                pos++;
            }
            if(value > x)break;
        }
        if(node.children == null)return node;
        return searchNode(x,node.children.get(pos));
    }

    public int insert(int x){
        if(root == null){
            root = new BTreeNode();
            root.values = new ArrayList<>();
            root.values.add(x);
            return 0;
        }else {
            newNodeL = newNodeR = null;
            return insertIntoNode(x, searchNode(x, root));
        }
    }

    private BTreeNode newNodeL;
    private BTreeNode newNodeR;

    private int insertIntoNode(int x , BTreeNode node){
        if(node == null){
            node = new BTreeNode();
            node.values = new ArrayList<>();
            node.values.add(x);
            node.children = new ArrayList<>();
            node.children.add(newNodeL);
            newNodeL.parent = node;
            node.children.add(newNodeR);
            newNodeR.parent = node;
            root = node;
            return 2;
        }
        int pos = 0;
        for(int value : node.values){
            if(value == x) throw  new ArithmeticException();
            if(value < x){
                pos++;
            }
            if(value > x)break;
        }
        node.values.add(pos, x);
        if(node.children != null) {
            node.children.remove(pos);
            node.children.add(pos, newNodeR);
            node.children.add(pos, newNodeL);
        }
        if(node.values.size() > 2*d){
            int mid = node.values.get(d);
            newNodeL = new BTreeNode();
            newNodeL.values = new ArrayList<>();
            newNodeL.values.addAll(node.values.subList(0, d));
            newNodeL.parent = node.parent;
            if(node.children != null) {
                newNodeL.children = new ArrayList<>();
                newNodeL.children.addAll(node.children.subList(0, d));
            }
            newNodeR = new BTreeNode();
            newNodeR.values = new ArrayList<>();
            newNodeR.values.addAll(node.values.subList(d+1, node.values.size()));
            newNodeR.parent = node.parent;
            if(node.children != null) {
                newNodeR.children = new ArrayList<>();
                newNodeR.children.addAll(node.children.subList(d+1, node.children.size()));
            }
            int ret = insertIntoNode(mid, node.parent);
            if(ret == 2 )return 2;
            return 1;
        }else{

            return 0;
        }
    }

    public static void main(String[] args) {
        BTree btree = new BTree();
        int [] toInsert = {5,15,10,20,25,30,3,4,29,28,2,21,26,22,8,12,13};
        String solution = "00002000011001002";
        String statusOfInsertion = "";
        for(int x : toInsert){
            statusOfInsertion += btree.insert(x);
            System.out.println(statusOfInsertion);
        }
        System.out.println("Das Einfuegen in den Baum hat richtig funktioniert: " + solution.equals(statusOfInsertion));
        System.out.println(btree.search(26)); //true
        System.out.println(btree.search(100)); //false
    }

}
