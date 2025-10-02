public class TreeNode<T> {
    private T value;
    private TreeNode<T> left, right;

    // costruttore
    public TreeNode(T value) {
        this.value = value;
        left = right = null;
    }

    // getter
    public T getValue() {
        return value;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    // setter
    public void setValue(T v) {
        this.value = v;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode [value=" + value + "]";
    }
}
