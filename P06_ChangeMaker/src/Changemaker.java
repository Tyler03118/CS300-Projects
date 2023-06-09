//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P06 Change Maker
// Course:   CS 300 Spring 2023
//
// Author:   Ziji Li
// Email:    zli296@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Online Sources: used to learn how to write recursion methods for coins changes
// https://www.techiedelight.com/coin-change-problem-find-total-number-ways-get-denomination-coins/
// https://stackoverflow.com/questions/45401847/coin-change-recursive-approach
// https://stackoverflow.com/questions/53521174/coin-change-problem-brute-force-solution-with-
// backtracking-but-how-to-get-coi
// https://www.scaler.com/topics/coin-change-problem/
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Arrays;

/**
 * This class is used to implement recursion method for making coin changes
 */
public class Changemaker {

  /**
   * Determine the number of possible ways to make change for a given value with a limited number of
   * coins of varying denominations
   *
   * @param denominations  array describes the value of each type of coin in your register
   * @param coinsRemaining array describes the quantity of each type of coin in your register
   * @param value          parameter describes the total amount of change to be dispensed to the
   *                       customer
   * @return the number of possible ways to make change
   */
  public static int count(int[] denominations, int[] coinsRemaining, int value) {
    // base case: return 0 if it is negative
    if (value < 0) {
      return 0;
    }
    // base case: there is exactly one way if value is 0
    if (value == 0) {
      return 1;
    }
    boolean noCoinsLeft = true;
    // check if there are any coins left in each recursion step
    for (int i = 0; i < coinsRemaining.length; i++) {
      if (coinsRemaining[i] > 0) {
        noCoinsLeft = false;
        break;
      }
    }
    // base case: return 0 if no coins left
    if (noCoinsLeft) {
      return 0;
    }
    int count = 0;
    for (int i = 0; i < denominations.length; i++) {
      // start to make changes
      if (coinsRemaining[i] > 0 && value >= denominations[i]) {
        // make a deep copy
        int[] newCoinsRemaining = Arrays.copyOf(coinsRemaining, coinsRemaining.length);
        newCoinsRemaining[i]--;
        // start recursion
        count += count(denominations, newCoinsRemaining, value - denominations[i]);
      }
    }
    return count;
  }


  /**
   * Determine the minimum number of coins to make change for a given value with a limited number of
   * coins of varying denominations
   *
   * @param denominations  array describes the value of each type of coin in your register
   * @param coinsRemaining array describes the quantity of each type of coin in your register
   * @param value          parameter describes the total amount of change to be dispensed to the
   *                       customer
   * @return the minimum number of coins needed to make change
   */
  public static int minCoins(int[] denominations, int[] coinsRemaining, int value) {
    // base case
    if (value < 0) {
      return -1;
    }

    if (value == 0) {
      return 0;
    }

    boolean noCoinsLeft = true;
    for (int i = 0; i < coinsRemaining.length; i++) {
      if (coinsRemaining[i] > 0) {
        noCoinsLeft = false;
        break;
      }
    }

    if (noCoinsLeft) {
      return -1;
    }

    int minCoins = Integer.MAX_VALUE;
    boolean found = false;
    for (int i = 0; i < denominations.length; i++) {
      if (coinsRemaining[i] > 0 && value >= denominations[i]) {
        int[] newCoinsRemaining = Arrays.copyOf(coinsRemaining, coinsRemaining.length);
        newCoinsRemaining[i]--;
        // get the possible way by recursion
        int result = minCoins(denominations, newCoinsRemaining, value - denominations[i]);
        // if the result is valid, find the minimum coins needed
        if (result != -1) {
          // + 1 to include the current coin
          minCoins = Math.min(minCoins, result + 1);
          found = true;
        }
      }
    }
    // check if there is at least one valid coin change way
    if (!found) {
      return -1;
    }

    return minCoins;
  }

  /**
   * Determine the array combinations of coins to make change for a given value with a limited
   * number of coins of varying denominations
   *
   * @param denominations  array describes the value of each type of coin in your register
   * @param coinsRemaining array describes the quantity of each type of coin in your register
   * @param value          parameter describes the total amount of change to be dispensed to the
   *                       customer
   * @return the combination array of coins needed to make change
   */
  public static int[] makeChange(int[] denominations, int[] coinsRemaining, int value) {
    if (value == 0) {
      return new int[denominations.length];
    }
    if (value < 0) {
      return null;
    }
    boolean noCoinsLeft = true;
    for (int i = 0; i < coinsRemaining.length; i++) {
      if (coinsRemaining[i] > 0) {
        noCoinsLeft = false;
        break;
      }
    }
    if (noCoinsLeft) {
      return null;
    }
    // store what coins are used
    int[] minCoinsUsed = null;
    int minTotalCoins = Integer.MAX_VALUE;
    for (int i = 0; i < denominations.length; i++) {
      if (coinsRemaining[i] > 0 && value >= denominations[i]) {
        coinsRemaining[i]--;
        int[] result = makeChange(denominations, coinsRemaining, value - denominations[i]);
        // backtracking
        coinsRemaining[i]++;

        if (result != null) {
          result[i]++;
          // calculate the sum of result array and get minimum total coins
          int totalCoins = sumHelper(result);
          if (totalCoins < minTotalCoins) {
            minTotalCoins = totalCoins;
            minCoinsUsed = result;
          }
        }
      }
    }
    return minCoinsUsed;
  }

  /**
   * This method helps to calculate the sum of number of coins
   *
   * @param coins the array of coins to calculate
   * @return the sum of number of coins
   */
  private static int sumHelper(int[] coins) {
    int total = 0;
    for (int coin : coins) {
      total += coin;
    }
    return total;
  }
}
