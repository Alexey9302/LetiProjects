import binaryheap.tree.BinaryHeap;
import binaryheap.tree.DfsIterator;

public class Main {
    public static void main(String... args){
        BinaryHeap binaryHeap = new BinaryHeap();
        binaryHeap.insert(1);
        binaryHeap.insert(5);
        binaryHeap.insert(3);
        binaryHeap.insert(9);
        binaryHeap.insert(2);

        DfsIterator iterator = binaryHeap.getDfsIterator();
        while(iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
    }
}
