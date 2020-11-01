package binaryheap.tree;

import linkedlistik.list.LinkedListik;

import java.util.Iterator;

public class DfsIterator implements Iterator {

    private BinaryHeap list;
    private LinkedListik<Integer> listGraph = new LinkedListik<>();
    private int index = -1;

    public DfsIterator(BinaryHeap list) {
        this.list = list;
        myDfs(0);
    }

    @Override
    public boolean hasNext() {
        return index + 1 < list.size();
    }

    @Override
    public Integer next() {
        return listGraph.at(++index);
    }

    @Override
    public void remove() {
        list.remove(index--);
        myDfs(0);
    }

    /**
     * Create dfs result array
     *
     * @param i index of graph
     */
    private void myDfs(int i){
        if(i >= list.size())
            return;

        listGraph.pushBack(list.getInList(i));
        i = (i + 1) * 2;

        myDfs(i - 1);
        myDfs(i);
    }
}
