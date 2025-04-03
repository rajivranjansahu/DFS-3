// TC: O(4 ^ n)
// SC: O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    // TC = O(4^ n), SC = O(n)
    public boolean makesquare(int[] matchsticks) {
        if(matchsticks == null || matchsticks.length == 0) return false;
        int sum = 0;
        for(int matchstick : matchsticks) sum += matchstick;
        if(sum % 4 != 0) return false;
        int side = sum / 4;
        // Arrays.sort(matchsticks, Collections.reverseOrder()); // primitive types cant use comparators
        Arrays.sort(matchsticks);
        reverse(matchsticks);
// Desc Sort: 1. Early Pruning: By placing the largest matchsticks first, you quickly hit invalid configurations. If a large matchstick doesn't fit into any side, you can backtrack immediately instead of wasting time trying to fit smaller ones later.
// 2. Reduced Search Space: Larger numbers are more "restrictive." When you add them early, they reduce the degrees of freedom for the remaining placements. This means fewer recursive calls & smaller search space overall.
        return backtrack(matchsticks, 0, new int[4], side);
    }
    private boolean backtrack(int[] matchsticks, int index, int[] square, int side) {
        // base
        if(index == matchsticks.length) {
            // if(square[0] == square[1] == square[2] == side) // comparison not required as sum = 4 * side. As all matchsticks are used & square[i] <= side, it must be that each side is exactly equal to side.
            return true;
        }
        // logic
        for(int i = 0; i < 4; i++) {
            if(square[i] + matchsticks[index] <= side) {
                square[i] += matchsticks[index]; // action
                if(backtrack(matchsticks, index + 1, square, side)) { // recurse
                    return true; // return true reqd inside if to stop recursion once valid soln is found & to ensure that soln is returned correctly
                }
                square[i] -= matchsticks[index]; // backtrack
            }
        }
        return false;
    }

    private void reverse(int[] matchsticks) {
        int l = 0, r = matchsticks.length - 1;
        while(l < r) {
            swap(matchsticks, l, r);
            l++;
            r--;
        }
    }

    private void swap(int[] matchsticks, int l, int r) {
        int temp = matchsticks[l];
        matchsticks[l] = matchsticks[r];
        matchsticks[r] = temp;
    }
}