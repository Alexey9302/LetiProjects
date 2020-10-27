package simplealgo.algos;

/**
 * Variant: 2, tasks: 1, 2, 4, 5, 6
 */

public class Algorithm {

    private static final int MAX_VALUE_CHAR = 65536;

    public static int binarySearch(int[] nums, int elem){
        int l = -1;
        int r = nums.length;

        while(l + 1 < r){
            int mid = (l + r) / 2;

            if(elem > nums[mid])
                l = mid;
            else
                r = mid;
        }

        return r;
    }

    public static void quickSort(int[] nums){
        sort(nums, 0, nums.length - 1);
    }

    public static void bubbleSort(int[] nums){
        int len = nums.length - 1;

        for(int i = 0; i < len; i++){
            for(int j = 0; j < len - i; j++){
                if(nums[j] > nums[j + 1]){
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public static void bogoSort(int[] nums){
        while (!isCorrect(nums)){
            for(int i = 0; i < nums.length; i++){
                int index = (int)(Math.random() * nums.length);

                swap(nums, i, index);
            }
        }
    }

    public static void countingSort(char[] nums){
        int min = getMin(nums);
        int max = getMax(nums);
        int len = max - min + 1;
        int[] count = new int[len];
        int index = 0;

        for(char s: nums){
            ++count[s - min];
        }

        for(int i = 0; i < len; i++){
            for(int j = 0; j < count[i]; j++){
                nums[index++] = (char)(i + min);
            }
        }
    }

    public static boolean isCorrect(int[] nums){
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] > nums[i + 1])
                return false;
        }

        return true;
    }

    public static boolean isCorrect(char[] nums){
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] > nums[i + 1])
                return false;
        }

        return true;
    }

    private static void sort(int[] nums, int l, int r){
        if(l < r){
            int q = partition(nums, l, r);

            sort(nums, l, q);
            sort(nums, q + 1, r);
        }
    }

    private static int partition(int[] nums, int l, int r){
        int mid = nums[(l + r) / 2];
        int i = l;
        int j = r;

        while(i <= j){
            while(nums[i] < mid)
                ++i;

            while(nums[j] > mid)
                --j;

            if(i >= j)
                break;

            swap(nums, i, j);

            ++i;
            --j;
        }

        return j;
    }

    private static int getMin(char[] nums){
        int min = MAX_VALUE_CHAR;

        for(char s: nums) {
            if (s < min)
                min = s;
        }

        return min;
    }

    private static int getMax(char[] nums){
        int max = 0;

        for(char s: nums) {
            if (s > max)
                max = s;
        }

        return max;
    }

    /**
     * Swap element A with element B (nums[i] with nums[j])
     *
     * @param nums array
     * @param i for element A in array
     * @param j for element B in array
     */
    private static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
