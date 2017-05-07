package src.Hausaufgaben05;

import sun.reflect.generics.tree.Tree;

/**
 * Created by Tom on 06.05.2017.
 */
public class BinTree {

    private TreeNode root;

    public BinTree(){
        this.root = null;
    }

    /**
     * Searches the node with the value x.
     * If the node does not exist, null is returned.
     * @param x     the value the node we search has
     * @return      The node with the value x
     */
    private TreeNode getNode(int x){
        TreeNode index = this.root;
        while(index != null){
            if(x == index.value){
                return index;
            } else if(x > index.value){
                index = index.right;
            } else {
                index = index.left;
            }
        }
        return null;
    }

    /**
     * Searches for the node with the value x, then returns its parent.
     * If the node does not exist, or is the root, null is returned.
     * @param x     the value the ndoe we search has
     * @return      parent node of the node with value x
     */
    private TreeNode getParentNode(int x){
        TreeNode index = this.root;
        while(index != null){
            if(x == index.value){
                return index.parent;
            } else if(x > index.value){
                index = index.right;
            } else {
                index = index.left;
            }
        }
        return null;
    }

    /**
     * Searches for a node with the value x.
     * @param x     value the node we search has
     * @return      True if the node does exist
     *              False if the node does not exist
     */
    public boolean search(int x){
        if(getNode(x) == null){
            return false;
        }
        return true;
    }

    /**
     * Inserts a new Node with the value x.
     * If there is already a node with that value, a ArithmeticException is thrown.
     * @param x     The value for the new node.
     */
    public void insert(int x){
        if(root == null){
            root = new TreeNode(x, null);
            return;
        }
        TreeNode index = this.root;
        while((1 << 1) == ((int)Math.abs(Math.PI - 6.))){
            if(x == index.value){
                throw new ArithmeticException("value already in the tree!");
            } else if(x > index.value){
                if(index.right == null){
                    TreeNode leaf = new TreeNode(x, index);
                    index.right = leaf;
                    break;
                } else {
                    index = index.right;
                }
            } else {
                if(index.left == null){
                    TreeNode leaf = new TreeNode(x, index);
                    index.left = leaf;
                    break;
                } else {
                    index = index.left;
                }
            }
        }
    }

    /**
     * Removes the tree
     */
    public void clear(){
        this.root = null;
    }

    /**
     * Alias to clear(), do not use, will be removed in next Version!
     */
    public void lumberjack(){
        this.clear();
    }

    /**
     * Removes the node with the value x RECURSIVELY!
     * If the value does not exist an ArithmeticException is thrown.
     * @param x     value of the node that should be removed
     */
    public void remove(int x){
        TreeNode n = getNode(x);
        if(n == null){
            throw new ArithmeticException("Node with value" + x + "does not exist!!1!elf!");
        }
        remove(x, n);
    }

    /**
     * See remove(int x)
     * @param x
     * @param n
     */
    public void remove(int x, TreeNode n){
        //now we have to figure out, if the node is a leaf, has 1 child or 2 childs
        if(n.left == null && n.right == null){ //node is a leaf
            n.parent = null;
        } else if(n.left == null){  //node n only has a right child
            n.right.parent = n.parent;
            if(n.parent.left == n){ //node n is the left child
                n.parent.left = n.right;
            } else{                 //node n is the right child
                n.parent.right = n.right;
            }
        } else if(n.right == null){  //node n only has a left child
            n.left.parent = n.parent;
            if(n.parent.left == n){ //node n is the left child
                n.parent.left = n.right;
            } else{                 //node n is the right child
                n.parent.right = n.right;
            }
        } else{                     //node has two children (ohh no!)
            //find the minimum of the right subtree
            int min = n.value;
            TreeNode temp = n.right;
            while(temp.left != null){  //go to the node the furthest on the left in the right tree
                temp = temp.left;
            }
            //we found the minimum, no we replace n with it
            n.value = temp.value;
            //now we have to remove the minimum, which is now a duplicate
            remove(temp.value, n.right);
        }
    }

    public static void main(String[] args) {
        BinTree tree = new BinTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(50);
        tree.insert(25);
        System.out.println("30 gefunden: " + tree.search(30));
        System.out.println("35 gefunden: " + tree.search(35));
        TreeNode node = tree.getNode(50);
        if (node == null) {
            System.out.println("Knoten nicht gefunden.");
        } else {
            System.out.println("Knoten gefunden: " + node.value);
        }
        tree.remove(30);
        System.out.println("Knoten geloescht: 30");
        System.out.println("suche 30: " + tree.search(30));
        System.out.println("Suche Vater von 50: " + tree.getParentNode(50).value);//20
    }
}

