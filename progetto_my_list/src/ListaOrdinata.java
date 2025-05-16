public class ListaOrdinata<T extends Comparable<T>> extends Lista<T> {

    @Override
    public void add(T newval) {
        Nodo<T> newNode = new Nodo<>(newval);
        if (root==null) { root=newNode; return; }
        Nodo<T> temp = root;

        if (root.getValore().compareTo(newval)>0) {
            newNode.setSucc(root);
            root=newNode;
            return;
        }
        while (temp.getSucc()!=null && temp.getSucc().getValore().compareTo(newval)<0) temp=temp.getSucc();

        newNode.setSucc(temp.getSucc());
        temp.setSucc(newNode);
    }

} 