package simplealgo.test;

import org.junit.Assert;
import org.junit.Test;
import simplealgo.algos.Algorithm;

import static org.junit.Assert.*;

public class AlgorithmTest {

    @Test
    public void binarySearch() {
        int[] arr = new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        };

        int res = Algorithm.binarySearch(arr, 2);

        Assert.assertEquals(res, 1);
    }

    @Test
    public void quickSort() {
        int[] arr = new int[]{
                5, 1, 2, 4, 3
        };

        Algorithm.quickSort(arr);
        boolean res = Algorithm.isCorrect(arr);

        assertTrue(res);
    }

    @Test
    public void bubbleSort() {
        int[] arr = new int[]{
                5, 1, 2, 4, 3
        };

        Algorithm.bubbleSort(arr);
        boolean res = Algorithm.isCorrect(arr);

        assertTrue(res);
    }

    @Test
    public void bogoSort() {
        int[] arr = new int[]{
                5, 1, 2, 4, 3
        };

        Algorithm.bogoSort(arr);
        boolean res = Algorithm.isCorrect(arr);

        assertTrue(res);
    }

    @Test
    public void countingSort() {
        char[] arr = new char[]{
                'd', 'a', 'c', 'b', 'e'
        };

        Algorithm.countingSort(arr);
        boolean res = Algorithm.isCorrect(arr);

        assertTrue(res);
    }
}