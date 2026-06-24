//package cp;

import java.io.IOException;
import java.util.*;

class Solution {
    int m;
    // store the states
    static class state{
        int start,end,op;
        public state(int s,int e,int op){
            start=s;
            end=e;
            this.op=op;
        }
    }
    Map<state,Integer>map=new HashMap<>();
    int dfs(state s,int[] nums,int[] mul){
        if(s.op==m)return 0;
        if(map.containsKey(s))return map.get(s);
        int takestart=nums[s.start]*mul[s.op]+dfs(new state(s.start+1,s.end,s.op+1 ),nums,mul);
        int takeend=nums[s.end]*mul[s.op]+dfs(new state(s.start,s.end-1,s.op+1 ),nums,mul);
        return Math.max(takeend,takestart);

    }
    public int maximumScore(int[] nums, int[] multipliers) {
        int n=nums.length;
        m=multipliers.length;
        state s=new state(0,n-1,0);
        int ans=dfs(s,nums,multipliers);
        return ans;
    }
    // Main method to execute your test case
    public static void main(String[] args) {

            Solution solver = new Solution();
            int[] nums={-5,-3,-3,-2,7,1};
            int[] multipliers={-10,-5,3,4,6};
            int ans=solver.maximumScore(nums, multipliers);
          System.out.println(ans);

    }
}


//
//You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.
//
//In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.
//
//Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.
//
//Each answer[i] is calculated considering the initial state of the boxes.
//
// 
//
//Example 1:
//
//Input: boxes = "110"
//Output: [1,1,3]
//Explanation: The answer for each box is as follows:
//        1) First box: you will have to move one ball from the second box to the first box in one operation.
//        2) Second box: you will have to move one ball from the first box to the second box in one operation.
//        3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
//Example 2:
//
//Input: boxes = "001011"
//Output: [11,8,5,4,3,4]
//         
//
//Constraints:
//
//n == boxes.length
//1 <= n <= 2000
//boxes[i] is either '0' or '1'.©leetcode