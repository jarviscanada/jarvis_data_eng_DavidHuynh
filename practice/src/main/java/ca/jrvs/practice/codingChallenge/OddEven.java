package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-2ab008d921d8471ea5a0cc6c63e4b020
 */
public class OddEven {

  /**
   * Big-O: O(1)
   * Justification: it's an arithmetic operation
   */
  public String oddEvenMod(int i){
    return i % 2 == 0 ? "Even" : "Odd";
  }
}