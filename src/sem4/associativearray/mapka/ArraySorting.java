package sem4.associativearray.mapka;

import java.util.Comparator;

/**
 * Simple sorting array algorithm
 */
public class ArraySorting {

    public static <T> void quickSort(T[] nums, Comparator<? super T> comparator){
        sort(nums, comparator, 0, nums.length - 1);
    }

    private static <T> void sort(T[] nums, Comparator<? super T> comparator, int left, int right){
        if(left < right){
            int q = partition(nums, comparator, left, right);

            sort(nums, comparator, left, q);
            sort(nums, comparator, q + 1, right);
        }
    }

    private static <T> int partition(T[] nums, Comparator<? super T> comparator, int left, int right){
        T mid = nums[(left + right) / 2];
        int i = left;
        int j = right;

        while(i <= j){
            while(comparator.compare(nums[i], mid) < 0)
                ++i;

            while(comparator.compare(nums[j], mid) > 0)
                --j;

            if(i >= j)
                break;

            swap(nums, i, j);

            ++i;
            --j;
        }

        return j;
    }

    private static <T> void swap(T[] nums, int i, int j){
        T temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
