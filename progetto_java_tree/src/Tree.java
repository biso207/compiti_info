public class Tree<T extends Comparable<T>> {
    private Node<T> root;

    // costruttore
    public Tree() {
        root=null;
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);

        if (root==null) { root=newNode; return; }
        Node<T> temp = root;
        while (true) {
            if (value.compareTo(temp.getValue())>0) {
                if (temp.getRight()==null) {
                    temp.setRight(temp);
                    break;
                }
            }
            else {
                if (temp.getLeft()==null) {
                    temp.setRight(temp);
                    break;
                }
            }
        }
    }
}
