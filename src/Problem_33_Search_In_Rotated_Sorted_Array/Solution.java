package Problem_33_Search_In_Rotated_Sorted_Array;

class Solution {
    private int[] nums;

    int search(int beg, int end, int target) {
        System.out.println("beg " + beg + ", end " + end);
        if (end < beg) {
            return -1;
        }
        if (beg == end) {
            if (nums[beg] != target) {
                return -1;
            }
            return beg;
        }

        int begVal = nums[beg];
        int endVal = nums[end];

        if (begVal < endVal && (target < begVal || target > endVal)) {
            return -1;
        }

        int pivot = (beg + end) / 2;
        System.out.println("pivot " + pivot);

        int leftIdx = search(beg, pivot, target);
        if (leftIdx != -1) {
            return leftIdx;
        }

        int rightIdx = search(pivot + 1, end, target);
        return rightIdx;
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        this.nums = nums;
        return search(0, nums.length - 1, target);
    }
}