package src.Hausaufgaben05;

/**
 * Created by Tom on 06.05.2017.
 */
public class BinTree {

    private TreeNode root;

    public BinTree(){
        this.root = null;
    }

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

    public boolean search(int x){
        if(getNode(x) == null){
            return false;
        }
        return true;
    }

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

    public void clear(){
        this.root = null;
    }

    public void remove(){

    }

}

