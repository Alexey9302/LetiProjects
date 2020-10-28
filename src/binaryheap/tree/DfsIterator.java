package binaryheap.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DfsIterator implements Iterator {

    private BinaryHeap list;
    private List<Integer> listGraph = new ArrayList<>();
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
        return listGraph.get(++index);
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

        listGraph.add(list.getInList(i));
        i = (i + 1) * 2;

        myDfs(i - 1);
        myDfs(i);
    }
}
