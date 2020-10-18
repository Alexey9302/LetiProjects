package linkedlistik.list;

/**
 * author: Bikchurin Alexey, 9302;
 *
 *
 * How to use:
 * linkedlistik.list.LinkedListik<Integer> listik = new Listik<>();
 * listik.pushBack(10);
 *
 * linkedlistik.list.LinkedListik<Integer> listik1 = new Listik<>(listik);
 *
 *
 * @param <T> type (For example: Integer, String, etc)
 */

public class LinkedListik<T> {

    private int size = 0;

    private Node<T> first;
    private Node<T> last;

    public LinkedListik(){

    }

    public LinkedListik(LinkedListik<T> listik){
        checkListNull(listik);

        first = listik.get(0);
        last = listik.get(listik.getSize() - 1);
        size += listik.getSize();
    }

    public void pushBack(T elem){
        final Node<T> l = last;
        final Node<T> newNode = new Node<T>(last, elem, null);
        last = newNode;

        if(l == null)
            first = newNode;
        else
            l.next = newNode;

        ++size;
    }

    public void pushFront(T elem){
        final Node<T> f = first;
        final Node<T> newNode = new Node<T>(null, elem, first);
        first = newNode;

        if(f == null)
            last = newNode;
        else
            f.prev = newNode;

        ++size;
    }

    public void popBack(){
        remove(size - 1);
    }

    public void popFront(){
        remove(0);
    }

    public void insert(int index, T elem){
        final Node<T> c = get(index);
        final Node<T> p = c.prev;
        final Node<T> newNode = new Node<>(p, elem, c);
        c.prev = newNode;

        if(p == null)
            first = newNode;
        else
            p.next = newNode;

        ++size;
    }

    public T at(int index){
        return get(index).value;
    }

    public void remove(int index){
        final Node<T> c = get(index);
        final Node<T> n = c.next;
        final Node<T> p = c.prev;

        if(p == null)
            first = p;
        else
            p.next = n;

        if(n == null)
            last = p;
        else
            n.prev = p;

        c.value = null;
        c.prev = null;
        c.next = null;

        --size;
    }

    public int getSize(){
        return size;
    }

    public void printAll(){
        for(int i = 0; i < size; i++)
            System.out.println(get(i).value);
    }

    public void clear(){
        for (Node<T> x = first; x != null;) {
            Node<T> next = x.next;
            x.value = null;
            x.next = null;
            x.prev = null;
            x = next;
        }

        first = last = null;
        size = 0;
    }

    public void set(int index, T elem){
        get(index).value = elem;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //task 14
    public void insert(int index, LinkedListik<T> listik){
        checkListNull(listik);

        final Node<T> lf = listik.get(0);
        final Node<T> ll = listik.get(listik.size - 1);

        if(index == size){
            final Node<T> c = get(index - 1);
            last = ll;
            c.next = lf;
        } else {
            final Node<T> c = get(index);
            final Node<T> p = c.prev;

            if(p == null)
                first = lf;
            else
                p.next = lf;

            lf.prev = p;
            c.prev = ll;
            ll.next = c;
        }

        size += listik.getSize();
    }

    public boolean isValidPosition(int index){
        return 0 <= index && index < size;
    }

    private Node<T> get(int index){
        checkPosition(index);

        Node<T> elem = first;

        for(int i = 0; i < index; i++)
            elem = elem.next;

        return elem;
    }

    private void checkPosition(int index){
        if(!isValidPosition(index))
            throw new IndexOutOfBoundsException("Index " + index + " is not included in interval [" + 0 + " ... " + size + "]");
    }

    private void checkListNull(LinkedListik<T> listik){
        if(listik == null || listik.getSize() == 0)
            throw new NullPointerException();
    }
}
