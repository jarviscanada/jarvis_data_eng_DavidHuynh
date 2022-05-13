package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-187f9bc323f14195949c94bed7a7eb45
 */
public class TwoMaps {

  /**
   * Big-O: O(n)
   * Justification: Compares to see if each key-value pair exists in each map.
   */
  public <K,V> boolean compareMaps(Map<K,V> m1, Map<K,V> m2){
    return m1.equals(m2);
  }
}
