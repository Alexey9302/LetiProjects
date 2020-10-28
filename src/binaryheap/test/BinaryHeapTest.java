package binaryheap.test;

import binaryheap.tree.BfsIterator;
import binaryheap.tree.BinaryHeap;
import binaryheap.tree.DfsIterator;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryHeapTest {

    @Test
    public void insert() {
        BinaryHeap binaryHeap = new BinaryHeap();
        binaryHeap.insert(1);
        binaryHeap.insert(5);
        binaryHeap.insert(3);
        binaryHeap.insert(9);
        binaryHeap.insert(2);

        int size = binaryHeap.size();

        Assert.assertEquals(size, 5);
    }

    @Test
    public void remove() {
        BinaryHeap binaryHeap = new BinaryHeap();
        binaryHeap.insert(1);
        binaryHeap.insert(5);
        binaryHeap.insert(3);
        binaryHeap.insert(9);
        binaryHeap.insert(2);

        binaryHeap.remove(0);
        int size = binaryHeap.size();

        Assert.assertEquals(size, 4);
    }

    @Test
    public void contains() {
        BinaryHeap binaryHeap = new BinaryHeap();
        binaryHeap.insert(1);
        binaryHeap.insert(5);
        binaryHeap.insert(3);
        binaryHeap.insert(9);
        binaryHeap.insert(2);

        binaryHeap.remove(0);

        boolean after = binaryHeap.contains(9);

        assertFalse(after);
    }

    @Test
    public void getDfsIterator() {
        BinaryHeap binaryHeap = new BinaryHeap();
        binaryHeap.insert(1);
        binaryHeap.insert(5);
        binaryHeap.insert(3);
        binaryHeap.insert(9);
        binaryHeap.insert(2);

        StringBuilder builder = new StringBuilder();
        DfsIterator iterator = binaryHeap.getDfsIterator();
        while(iterator.hasNext()){
            builder.append(iterator.next());
        }

        Assert.assertEquals(builder.toString(), "95123");
    }

    @Test
    public void getBfsIterator() {
        BinaryHeap binaryHeap = new BinaryHeap();
        binaryHeap.insert(1);
        binaryHeap.insert(5);
        binaryHeap.insert(3);
        binaryHeap.insert(9);
        binaryHeap.insert(2);

        StringBuilder builder = new StringBuilder();
        BfsIterator iterator = binaryHeap.getBfsIterator();
        while(iterator.hasNext()){
            builder.append(iterator.next());
        }

        Assert.assertEquals(builder.toString(), "95312");
    }
}