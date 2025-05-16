public class Lista<T> {

    protected Nodo<T> root;

    public Lista() {
        root = null;
    }

    public void add(Nodo<T> newnode) {
        if (root==null) {
            root = newnode;
        } else {
            Nodo<T> temp = root;
            while (temp.getSucc()!=null) temp=temp.getSucc();
            temp.setSucc(newnode);
        }
    }

    public void add(T newval) {
        Nodo<T> newnode = new Nodo<>(newval);
        this.add(newnode);
    }

    public void addHead( T newval) {
        Nodo<T> nuovonodo = new Nodo<>(newval);
        nuovonodo.setSucc(root);
        root = nuovonodo; 
    }
    

    @Override
    public String toString() {
        String result = "Lista: ";
        Nodo<T> tmp = root;
        while (tmp!=null) {
            result += " --> " + tmp;
            tmp = tmp.getSucc();      
        }
        return result;
    }
}