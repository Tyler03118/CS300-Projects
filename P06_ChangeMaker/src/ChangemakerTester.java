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
 * This class is used to test the implementation of Changemaker class
 */
public class ChangemakerTester {

  /**
   * test method for count basic values scenarios
   *
   * @return true if passed all the tests
   */
  public static boolean testCountBase() {
    int[] denominations = {1, 5, 10, 25};
    int[] coinsRemaining = {5, 1, 3, 1};
    int valueNegative = -1;
    int valuePositive = 999;
    int valueZero = 0;

    // 1. count() returns 0 when value is negative
    int expected1 = 0;
    int actual1 = Changemaker.count(denominations, coinsRemaining, valueNegative);
    if (actual1 != expected1)
      return false;

    // 2. count() returns 0 when value is positive but there is no way to make change. You can create
    // such a scenario by choosing the sum total of all the coins in the register to be smaller than value.
    int expected2 = 0;
    int actual2 = Changemaker.count(denominations, coinsRemaining, valuePositive);
    if (actual2 != expected2)
      return false;

    // 3. count() returns 1 when value = 0
    int expected3 = 1;
    int actual3 = Changemaker.count(denominations, coinsRemaining, valueZero);
    if (actual3 != expected3)
      return false;

    // return true if passes all the tests above
    return true;
  }

  /**
   * test method for count recursive values scenarios
   *
   * @return true if passed all the tests
   */
  public static boolean testCountRecursive() {
    // 1.count() returns the correct result in a scenario in which at least three different coins can be
    //used to make change.
    int[] denominations1 = {1, 5, 10, 25};
    int[] coinsRemaining1 = {1, 1, 1, 1};
    int value1 = 36;
    int expected1 = 6;
    int actual1 = Changemaker.count(denominations1, coinsRemaining1, value1);
    if (actual1 != expected1)
      return false;

    // 2. count() returns the correct result in a scenario in which there are at least two different
    //optimal ways to make change using the same number of coins
    int[] denominations2 = {2, 5, 7, 10};
    int[] coinsRemaining2 = {1, 1, 1, 1};
    int value2 = 12;
    int expected2 = 4;
    int actual2 = Changemaker.count(denominations2, coinsRemaining2, value2);
    if (actual2 != expected2)
      return false;

    // 3. count() returns the correct result in a scenario in which always greedily choosing the largest
    //coin first does not produce a result with the minimum number of coins used
    int[] denominations3 = {1, 5, 6, 9};
    int[] coinsRemaining3 = {2, 1, 1, 1};
    int value3 = 11;
    int expected3 = 5;
    int actual3 = Changemaker.count(denominations3, coinsRemaining3, value3);
    if (actual3 != expected3)
      return false;

    // return true if passes all the tests above
    return true;
  }

  /**
   * test method for count minimum coins needed in basic values scenarios
   *
   * @return true if passed all the tests
   */
  public static boolean testMinCoinsBase() {
    int[] denominations = {1, 5, 6, 9};
    int[] coinsRemaining = {2, 1, 1, 1};
    int valueNegative = -1;
    int valuePositive = 999;
    int valueZero = 0;

    // 1. minCoins() returns -1 when value is negative
    int expected1 = -1;
    int actual1 = Changemaker.minCoins(denominations, coinsRemaining, valueNegative);
    if (actual1 != expected1)
      return false;

    // 2. minCoins() returns -1 when value is positive but there is no way to make change
    int expected2 = -1;
    int actual2 = Changemaker.minCoins(denominations, coinsRemaining, valuePositive);
    if (actual2 != expected2)
      return false;

    // 3. minCoins() return 0 when value = 0
    int expected3 = 0;
    int actual3 = Changemaker.minCoins(denominations, coinsRemaining, valueZero);
    if (actual3 != expected3)
      return false;

    // return true if passes all the tests above
    return true;
  }

  /**
   * test method for count minimum coins needed in recursive values scenarios
   *
   * @return true if passed all the tests
   */
  public static boolean testMinCoinsRecursive() {
    // 1. minCoins() returns the correct result in a scenario in which at least three different coins can be
    //used to make change.
    int[] denominations1 = {1, 5, 10, 25};
    int[] coinsRemaining1 = {1, 1, 1, 1};
    int value1 = 36;
    int expected1 = 3;
    int actual1 = Changemaker.minCoins(denominations1, coinsRemaining1, value1);
    if (actual1 != expected1)
      return false;

    // 2. minCoins() returns the correct result in a scenario in which there are at least two different
    //optimal ways to make change using the same number of coins
    int[] denominations2 = {2, 5, 7, 10};
    int[] coinsRemaining2 = {1, 1, 1, 1};
    int value2 = 12;
    int expected2 = 2;
    int actual2 = Changemaker.minCoins(denominations2, coinsRemaining2, value2);
    if (actual2 != expected2)
      return false;

    // 3. minCoins() returns the correct result in a scenario in which always greedily choosing the largest
    //coin first does not produce a result with the minimum number of coins used
    int[] denominations3 = {1, 5, 6, 9};
    int[] coinsRemaining3 = {2, 1, 1, 1};
    int value3 = 11;
    int expected3 = 2;
    int actual3 = Changemaker.minCoins(denominations3, coinsRemaining3, value3);
    if (actual3 != expected3)
      return false;

    // return true if passes all the tests above
    return true;
  }

  /**
   * test method for making coin changes combinations in basic scenarios
   *
   * @return true if passed all the tests
   */
  public static boolean testMakeChangeBase() {
    int[] denominations = {1, 5, 6, 9};
    int[] coinsRemaining = {2, 1, 1, 1};
    int valueNegative = -1;
    int valuePositive = 999;
    int valueZero = 0;

    // 1. makeChange() returns null when value is negative
    int[] expected1 = null;
    int[] actual1 = Changemaker.makeChange(denominations, coinsRemaining, valueNegative);
    if (!Arrays.equals(expected1, actual1))
      return false;

    // 2. makeChange() returns null when value is positive but there is no way to make change
    int[] expected2 = null;
    int[] actual2 = Changemaker.makeChange(denominations, coinsRemaining, valuePositive);
    if (!Arrays.equals(expected2, actual2))
      return false;

    // 3. makeChange() returns an array of all 0 when value = 0
    int[] expected3 = {0, 0, 0, 0};
    int[] actual3 = Changemaker.makeChange(denominations, coinsRemaining, valueZero);
    if (!Arrays.equals(expected3, actual3))
      return false;

    // return true if passes all the tests above
    return true;
  }

  /**
   * test method for making coin changes combinations in recursive scenarios
   *
   * @return true if passed all the tests
   */
  public static boolean testMakeChangeRecursive() {
    // 1. makeChange() returns an optimal array in a scenario in which at least three different coins
    //must be used to make change
    int[] denominations1 = {1, 5, 10, 25};
    int[] coinsRemaining1 = {1, 1, 1, 1};
    int value1 = 36;
    int[] expectedOne = {1, 0, 1, 1};
    int[] actual1 = Changemaker.makeChange(denominations1, coinsRemaining1, value1);
    if (!Arrays.equals(actual1, expectedOne))
      return false;

    // 2. makeChange() returns an optimal array in a scenario in which there are at least two different
    //ways to make change using the same optimal number of coins.
    int[] denominations2 = {2, 5, 7, 10};
    int[] coinsRemaining2 = {1, 1, 1, 1};
    int value2 = 12;
    int[] expectedTwo1 = {1, 0, 0, 1};
    int[] expectedTwo2 = {0, 1, 1, 0};
    int[] actual2 = Changemaker.makeChange(denominations2, coinsRemaining2, value2);
    if (!Arrays.equals(actual2, expectedTwo1) && !Arrays.equals(actual2, expectedTwo2))
      return false;

    // 3. makeChange() returns an optimal array in a scenario in which always choosing the largest coin
    //first does not produce a result with the minimum number of coins used.
    int[] denominations3 = {1, 5, 6, 9};
    int[] coinsRemaining3 = {2, 1, 1, 1};
    int value3 = 11;
    int[] expectedThree = {0, 1, 1, 0};
    int[] actual3 = Changemaker.makeChange(denominations3, coinsRemaining3, value3);
    if (!Arrays.equals(actual3, expectedThree))
      return false;

    // return true if passes all the tests above
    return true;
  }

  /**
   * main method for implementing the tester class
   *
   * @param args certain arguments
   */
  public static void main(String[] args) {
    System.out.println("Count base: " + testCountBase());
    System.out.println("Count recursive: " + testCountRecursive());
    System.out.println("Min coin base: " + testMinCoinsBase());
    System.out.println("Min coin base recursive: " + testMinCoinsRecursive());
    System.out.println("Make change base: " + testMakeChangeBase());
    System.out.println("Make change recursive: " + testMakeChangeRecursive());
  }
}

