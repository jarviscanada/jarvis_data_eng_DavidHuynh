package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

/**
 * ticket: https://www.notion.so/jarvisdev/Two-Sum-4623734546204613ba8acad8795d4d6b
 */
public class TwoSum {

  /**
   * Big-O: O(n)
   * Justification: store each number into HashMap with the target - number and its index taking
   * O(n) time. Searching for if the number exists within HashMap takes normally constant time,
   * maximum O(n) which is still O(n) time.
   */
  public int[] twoSum(int[] nums, int target){
    HashMap<Integer, Integer> hm = new HashMap();
    for(int i = 0; i < nums.length; i++){

      if(hm.containsKey(nums[i])){
        return new int[]{hm.get(nums[i]), i};
      }
      hm.put(target - nums[i], i);
    }
    return new int[]{0, 0};
  }
}
