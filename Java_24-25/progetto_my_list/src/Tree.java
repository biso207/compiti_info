public class Tree<T extends Comparable<T>> {
    protected TreeNode<T> root;

    // costruttore
    public Tree() {
        root=null;
    }

    public void add(T value) {
        if (root==null) {
            root = new TreeNode<>(value);
        }
        else {
            
        }
    }
}
