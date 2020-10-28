package binaryheap.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In this project I decided to use bfs save format instead of graph,
 * because it makes the job easier.
 */

public class BinaryHeap {

    List<Integer> list = new ArrayList<>();

    public void insert(int value){
        list.add(value);
        int i = list.size() - 1;
        int parent = (i - 1) / 2;

        while(i > 0 && list.get(parent) < list.get(i)){
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
        return list.size();
    }

    public int getInList(int index){
        return list.get(index);
    }

    public DfsIterator getDfsIterator(){
        return new DfsIterator(this);
    }

    public BfsIterator getBfsIterator(){
        return new BfsIterator(this);
    }

    public Map<Integer, int[]> getGraph(){
        Map<Integer, int[]> graph = new HashMap<>();
        int l = 0;
        int r = 1;

        while(l <= list.size()){
            for(int i = l; i < r; i++){
                graph.put(list.get(i), new int[]{
                        -1, -1
                });
            }

            l = r;
            r *= 2;
        }

        return graph;
    }

    private void createHeap(){
        for(int i = list.size() / 2; i >= 0; i--){
            heap(i);
        }
    }

    private void heap(int i){
        int l, r, m;

        while(true){
            l = 2 * i + 1;
            r = l + 1;
            m = i;

            if(l < list.size() && list.get(l) > list.get(m))
                m = l;

            if(r < list.size() && list.get(r) > list.get(m))
                m = r;

            if(m == i)
                break;

            swap(i, m);
            i = m;
        }
    }

    private void swap(int i, int j){
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
