import java.util.Arrays;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] ans = new int[n];
        int start = 0;
        
        while (start < n) {
            int end = start + 1;
            while (end < n && pairs[end][0] - pairs[end - 1][0] <= limit) {
                end++;
            }
            int len = end - start;
            int[] indices = new int[len];
            for (int i = start; i < end; i++) {
                indices[i - start] = pairs[i][1];
            }
            Arrays.sort(indices);
            for (int i = start; i < end; i++) {
                ans[indices[i - start]] = pairs[i][0];
            }
            start = end;
        }
        
        return ans;
    }
}