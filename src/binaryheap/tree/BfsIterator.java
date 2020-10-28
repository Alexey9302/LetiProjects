package binaryheap.tree;

import java.util.Iterator;

public class BfsIterator implements Iterator {

    private BinaryHeap list;
    private int index = -1;

    public BfsIterator(BinaryHeap list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return index + 1 < list.size();
    }

    @Override
    public Integer next() {
        return list.getInList(++index);
    }

    @Override
    public void remove() {
        list.remove(index--);
    }
}
