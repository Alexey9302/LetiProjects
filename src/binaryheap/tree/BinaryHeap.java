package binaryheap.tree;

import linkedlistik.list.LinkedListik;

/**
 * In this project I decided to use bfs save format instead of graph,
 * because it makes the job easier.
 */

public class BinaryHeap {

    private LinkedListik<Integer> list = new LinkedListik<>();

    public void insert(int value){
        list.pushBack(value);
        int i = list.getSize() - 1;
        int parent = (i - 1) / 2;

        while(i > 0 && list.at(parent) < list.at(i)){
            swap(i, parent);

            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public void remove(int index){
        list.remove(index);
        createHeap();
    }

    public boolean contains(int elem){
        return list.contains(elem);
    }

    public int size(){
        return list.getSize();
    }

    public int getInList(int index){
        return list.at(index);
    }

    public DfsIterator getDfsIterator(){
        return new DfsIterator(this);
    }

    public BfsIterator getBfsIterator(){
        return new BfsIterator(this);
    }

    private void createHeap(){
        for(int i = list.getSize() / 2; i >= 0; i--){
            heap(i);
        }
    }

    private void heap(int i){
        int left, right, m;

        while(true){
            left = 2 * i + 1;
            right = left + 1;
            m = i;

            if(left < list.getSize() && list.at(left) > list.at(m))
                m = left;

            if(right < list.getSize() && list.at(right) > list.at(m))
                m = right;

            if(m == i)
                break;

            swap(i, m);
            i = m;
        }
    }

    private void swap(int i, int j){
        int temp = list.at(i);
        list.set(i, list.at(j));
        list.set(j, temp);
    }
}
