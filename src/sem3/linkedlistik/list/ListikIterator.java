package sem3.linkedlistik.list;

import java.util.Iterator;

public class ListikIterator<T> implements Iterator<T> {

    private final LinkedListik<T> listik;
    private int index = -1;

    public ListikIterator(LinkedListik<T> listik) {
        this.listik = listik;
    }

    @Override
    public boolean hasNext() {
        return index + 1 < listik.getSize();
    }

    @Override
    public T next() {
        return listik.at(++index);
    }
}
