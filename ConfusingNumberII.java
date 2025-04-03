// TC: O(5 ^ L), where L is the number of digits in 𝑛
// SC: O(L)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.HashMap;

class Solution {
    HashMap<Integer, Integer> map;
    int count;

    public int confusingNumberII(int n) {
        if(n == 0) return 0;
        map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        count = 0;
        dfs(0, n);
        return count;
    }

    private void dfs(int curr, int n) {
        // base
        if(curr > n) return;
        // logic
        if(isConfusing(curr)) {
            count++;
        }
        for(int i : map.keySet()) {
            int newNumber = curr * 10 + i;
            if(newNumber != 0) { // to prevent stack overflow
                dfs(newNumber, n);
            }
        }
    }

    private boolean isConfusing(int num) {
        int original = num, rotated = 0;
        while(num > 0) {
            int digit = num % 10;
            if(!map.containsKey(digit)) return false; // if digit is not in map, return false
            rotated = rotated * 10 + map.get(digit); // get the rotated value of the digit
            num /= 10; // remove the last digit
        }
        return original != rotated; // check if original and rotated are different
    }
}