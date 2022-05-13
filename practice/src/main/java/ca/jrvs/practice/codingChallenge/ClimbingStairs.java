package ca.jrvs.practice.codingChallenge;

import java.awt.List;
import java.util.ArrayList;

/**
 * https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-b626feea76a246f6bc49b872b980f4bb
 */
public class ClimbingStairs {
  /**
   * Big-O: O(2^n)
   * Justification: Recursion tree with each node branching into two paths for up to n levels
   * (height = n)
   */
  public int climbStairs1(int n){
    if (n == 1){
      return 1;
    } if (n == 2){
      return 2;
    }
    return climbStairs1(n-1) + climbStairs1(n-2);
  }

  /**
   * Big-O: O(n)
   * Justification: Using dynamic programming, get answers of climbstairs from 1 to n stairs. Retrieve
   * solutions of n-1 stairs and n-2 stairs to get result.
   */
  public int climbStairs2(int n){
    ArrayList<Integer> stairs = new ArrayList<Integer>();
    stairs.add(1);
    stairs.add(2);
    for(int i = 2; i < n; i++){
      stairs.add(stairs.get(i - 1) + stairs.get(i - 2));
    }
    return stairs.get(n - 1);
  }
}
