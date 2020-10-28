package linkedlistik.test;

import linkedlistik.list.LinkedListik;
import org.junit.Assert;
import org.junit.Test;

public class LinkedListikTest {

    @Test
    public void pushBack() {
        LinkedListik<Integer> listik = new LinkedListik<>();
        listik.pushBack(43110);
        int num = listik.at(0);

        Assert.assertEquals(num, 43110);
    }

    @Test
    public void pushFront() {
        LinkedListik<Integer> listik = new LinkedListik<>();
        listik.pushFront(43110);
        int num = listik.at(0);

        Assert.assertEquals(num, 43110);
    }

    @Test
    public void popBack() {
        LinkedListik<Integer> listik = new LinkedListik<>();
        listik.pushBack(43110);
        listik.popBack();
        int size = listik.getSize();

        Assert.assertEquals(size, 0);
    }

    @Test
    public void popFront() {
        LinkedListik<Integer> listik = new LinkedListik<>();
        listik.pushBack(43110);
        listik.popFront();
        int size = listik.getSize();

        Assert.assertEquals(size, 0);
    }

    @Test
    public void insert() {
        LinkedListik<Integer> listik = new LinkedListik<>();
        listik.pushBack(43110);
        listik.pushFront(43109);
        listik.insert(0, 1);
        int num = listik.at(0);

        Assert.assertEquals(num, 1);
    }

    @Test
    public void remove() {
        LinkedListik<Integer> listik = new LinkedListik<>();
        listik.pushBack(43110);
        listik.remove(0);
        int size = listik.getSize();

        Assert.assertEquals(size, 0);
    }

    @Test
    public void clear() {
        LinkedListik<Integer> listik = new LinkedListik<>();
        listik.pushBack(43110);
        listik.clear();

        Assert.assertEquals(listik.getSize(), 0);
    }

    @Test
    public void insertList() {
        LinkedListik<Integer> listik = new LinkedListik<>();
        listik.pushBack(43110);

        LinkedListik<Integer> listik1 = new LinkedListik<>();
        listik1.pushBack(43111);
        listik1.pushBack(43112);

        listik.insert(1, listik1);

        Assert.assertEquals(listik.getSize(), 3);
    }
}