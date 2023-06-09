//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Twitter Feed
// Course:   CS 300 Spring 2023
//
// Author:   Ziji Li
// Email:    zli2296@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Online Sources:  learned how to use repeat() method and use of iterators:
// https://www.studytonight.com/java-examples/how-to-repeat-string-n-times-in-java
// https://www.geeksforgeeks.org/iterators-in-java/
//////////////////////////////////////////////////////////////////////////////
import java.util.Calendar;
import java.util.Iterator;

/**
 * Tester class for the functionality of TwitterFeed
 */
public class TwiteratorTester {

  /**
   * Main method for TwiteratorTester class
   */
  public static void main(String[] args) {
    System.out.println("testUser: " + (testUser() ? "PASSED" : "FAILED"));
    System.out.println("testTweet: " + (testTweet() ? "PASSED" : "FAILED"));
    System.out.println("testNode: " + (testNode() ? "PASSED" : "FAILED"));
    System.out.println("testAddTweet: " + (testAddTweet() ? "PASSED" : "FAILED"));
    System.out.println("testInsertTweet: " + (testInsertTweet() ? "PASSED" : "FAILED"));
    System.out.println("testDeleteTweet: " + (testDeleteTweet() ? "PASSED" : "FAILED"));
    System.out.println("testChronoTwiterator: " + (testChronoTwiterator() ? "PASSED" : "FAILED"));
    System.out.println(
        "testVerifiedTwiterator: " + (testVerifiedTwiterator() ? "PASSED" : "FAILED"));
    System.out.println("testRatioTwiterator: " + (testRatioTwiterator() ? "PASSED" : "FAILED"));
  }

  /**
   * tester method for the User class
   */
  public static boolean testUser() {
    // Initialize dateGenerator
    Calendar cal = Calendar.getInstance();
    Tweet.setCalendar(cal);

    // initialize testUser
    User user = new User("testUser");

    // test getUsername() method
    if (!user.getUsername().equals("testUser")) {
      return false;
    }

    // test isVerified() method
    if (user.isVerified()) {
      return false;
    }

    // test verify() method
    user.verify();
    if (!user.isVerified()) {
      return false;
    }

    // test toString() method1
    String userString1 = user.toString();
    if (!userString1.equals("@testUser*")) {
      return false;
    }

    // test revokeVerification() method
    user.revokeVerification();
    if (user.isVerified()) {
      return false;
    }

    // test toString() method2
    String userString2 = user.toString();
    if (!userString2.equals("@testUser")) {
      return false;
    }

    // test invalid username
    try {
      User invalidUser1 = new User(null);
      return false; // Should not reach this line if exception is thrown
    } catch (IllegalArgumentException | NullPointerException e) {
      // Exception is expected, do nothing
    }

    try {
      User invalidUser2 = new User("");
      return false;
    } catch (IllegalArgumentException | NullPointerException e) {
      // Exception is expected, do nothing
    }

    try {
      User invalidUser3 = new User("invalid*user");
      return false;
    } catch (IllegalArgumentException | NullPointerException e) {
      // Exception is expected, do nothing
    }

    // Now everything looks good!
    return true;
  }

  /**
   * tester method for the Tweet class
   */
  public static boolean testTweet() {
    Calendar cal = Calendar.getInstance();
    Tweet.setCalendar(cal);

    User user = new User("testUser");
    Tweet tweet = new Tweet(user, "Hello, Elon Musk!");

    // test getText() method
    if (!tweet.getText().equals("Hello, Elon Musk!")) {
      return false;
    }

    // test getTotalEngagement() method
    if (tweet.getTotalEngagement() != 0) {
      return false;
    }

    // test isUserVerified() method
    if (tweet.isUserVerified()) {
      return false;
    }

    // test the method after like and retweet
    tweet.like();
    tweet.retweet();

    if (tweet.getTotalEngagement() != 2) {
      return false;
    }

    if (tweet.getLikesRatio() != 0.5) {
      return false;
    }

    // test invalid tweets
    try {
      Tweet invalidTweet1 = new Tweet(null, "Invalid tweet");
      return false; // Should not reach this line if exception is thrown
    } catch (NullPointerException e) {
      // Exception is expected, do nothing
    }

    try {
      Tweet invalidTweet2 = new Tweet(user, null);
      return false;
    } catch (NullPointerException e) {
      // Exception is expected, do nothing
    }

    try {
      String longText = "A".repeat(300);
      Tweet invalidTweet3 = new Tweet(user, longText);
      return false;
    } catch (IllegalArgumentException e) {
      // Exception is expected, do nothing
    }

    // Now everything looks good!
    return true;
  }

  /**
   * tester method for the testing nodes functionality
   */
  public static boolean testNode() {
    // Initialize dateGenerator
    Calendar cal = Calendar.getInstance();
    Tweet.setCalendar(cal);

    User user1 = new User("testUser1");
    User user2 = new User("testUser2");
    Tweet tweet1 = new Tweet(user1, "Message1");
    Tweet tweet2 = new Tweet(user2, "Message2");

    TweetNode node1 = new TweetNode(tweet1);
    TweetNode node2 = new TweetNode(tweet2);

    node1.setNext(node2);
    if (node1.getNext() != node2 || node1.getTweet() != tweet1 || node2.getTweet() != tweet2) {
      return false;
    }

    // Test two-argument constructor
    TweetNode node3 = null;
    try {
      node3 = new TweetNode(tweet1, node2);
    } catch (Exception e) {
      return false; // Should not throw any exception
    }

    if (node3.getNext() != node2 || node3.getTweet() != tweet1) {
      return false;
    }

    // now everything looks good!
    return true;

  }

  /**
   * tester method for adding tweetss
   */
  public static boolean testAddTweet() {
    // Initialize dateGenerator
    Calendar cal = Calendar.getInstance();
    Tweet.setCalendar(cal);

    User user1 = new User("testUser1");
    User user2 = new User("testUser2");
    TwitterFeed feed = new TwitterFeed();
    Tweet tweet1 = new Tweet(user1, "Message1");

    if (!feed.isEmpty() || feed.size() != 0) {
      return false;
    }
    // test addFirst tweet
    feed.addFirst(tweet1);

    if (feed.isEmpty() || feed.size() != 1 || !feed.contains(tweet1) || !feed.get(0)
        .equals(tweet1)) {
      return false;
    }
    // test addLast tweet
    Tweet tweet2 = new Tweet(user2, "Message2");
    feed.addLast(tweet2);

    if (feed.size() != 2 || !feed.contains(tweet2) || !feed.get(1).equals(tweet2)) {
      return false;
    }

    return feed.getHead().equals(tweet1) && feed.getTail().equals(tweet2);
  }

  /**
   * tester method for inserting tweetss
   */
  public static boolean testInsertTweet() {
    // Initialize dateGenerator
    Calendar cal = Calendar.getInstance();
    Tweet.setCalendar(cal);

    User user1 = new User("testUser1");
    User user2 = new User("testUser2");
    User user3 = new User("testUser3");

    TwitterFeed feed = new TwitterFeed();
    Tweet tweet1 = new Tweet(user1, "Message1");
    Tweet tweet2 = new Tweet(user2, "Message2");
    Tweet tweet3 = new Tweet(user3, "Message3");

    // insert in different locations
    feed.add(0, tweet1);
    feed.add(1, tweet2);
    feed.add(1, tweet3);

    if (feed.size() != 3) {
      return false;
    }

    return feed.getHead().equals(tweet1) && feed.getTail().equals(tweet2) && feed.get(1)
        .equals(tweet3);

  }

  /**
   * tester method for deleting tweetss
   */
  public static boolean testDeleteTweet() {
    // Initialize dateGenerator
    Calendar cal = Calendar.getInstance();
    Tweet.setCalendar(cal);

    User user1 = new User("testUser1");
    User user2 = new User("testUser2");
    User user3 = new User("testUser3");
    User user4 = new User("testUser4");
    User user5 = new User("testUser5");

    TwitterFeed feed = new TwitterFeed();
    Tweet tweet1 = new Tweet(user1, "Message1");
    Tweet tweet2 = new Tweet(user2, "Message2");
    Tweet tweet3 = new Tweet(user3, "Message3");
    Tweet tweet4 = new Tweet(user4, "Message4");
    Tweet tweet5 = new Tweet(user5, "Message5");

    feed.addLast(tweet1);
    feed.addLast(tweet2);
    feed.addLast(tweet3);
    feed.addLast(tweet4);
    feed.addLast(tweet5);

    Tweet deletedTweet = feed.delete(4);

    if (!deletedTweet.equals(tweet5) || !feed.getTail().equals(tweet4)) {
      return false;
    }

    deletedTweet = feed.delete(0);

    if (!deletedTweet.equals(tweet1) || !feed.getHead().equals(tweet2)) {
      return false;
    }

    deletedTweet = feed.delete(1);

    if (!deletedTweet.equals(tweet3) || !feed.get(1).equals(tweet4)) {
      return false;
    }

    // Now everything looks good!
    return true;
  }

  /**
   * tester method for chronological iterator
   */
  public static boolean testChronoTwiterator() {
    // Initialize dateGenerator
    Calendar cal = Calendar.getInstance();
    Tweet.setCalendar(cal);

    User user1 = new User("testUser1");
    User user2 = new User("testUser2");
    User user3 = new User("testUser3");

    TwitterFeed twitterFeed = new TwitterFeed();

    Tweet tweet1 = new Tweet(user1, "This is tweet 1.");
    Tweet tweet2 = new Tweet(user2, "This is tweet 2.");
    Tweet tweet3 = new Tweet(user3, "This is tweet 3.");

    twitterFeed.addLast(tweet1);
    twitterFeed.addLast(tweet2);
    twitterFeed.addLast(tweet3);

    int counter = 0;
    // check the order returned by the Chrono Iterator
    for (Tweet tweet : twitterFeed) {
      if (counter == 0 && !tweet.equals(tweet1)) {
        return false;
      }
      if (counter == 1 && !tweet.equals(tweet2)) {
        return false;
      }
      if (counter == 2 && !tweet.equals(tweet3)) {
        return false;
      }
      counter++;
    }

    return counter == 3;

  }

  /**
   * tester method for verified iterator
   */
  public static boolean testVerifiedTwiterator() {
    // Initialize dateGenerator
    Calendar cal = Calendar.getInstance();
    Tweet.setCalendar(cal);

    TwitterFeed feed = new TwitterFeed();

    User user1 = new User("Alice");
    User user2 = new User("Bob");
    User user3 = new User("Charlie");

    // Verify user2
    user3.verify();

    Tweet tweet1 = new Tweet(user1, "Hello, Bruce Lee!");
    Tweet tweet2 = new Tweet(user2, "Hello, Jackie Chan!");
    Tweet tweet3 = new Tweet(user3, "Hello, Ziji Li!");

    feed.addLast(tweet1);
    feed.addLast(tweet2);
    feed.addLast(tweet3);

    // Set the iterator mode
    feed.setMode(TimelineMode.VERIFIED_ONLY);

    // Test the iterator using the enhanced for loop
    int verifiedTweetCount = 0;
    for (Tweet tweet : feed) {
      if (tweet.isUserVerified()) {
        verifiedTweetCount++;
      } else {
        return false;
      }
    }

    if (verifiedTweetCount != 1) {
      return false;
    }

    // Now everything looks good!
    return true;
  }

  /**
   * tester method for ratio_like iterator
   */
  public static boolean testRatioTwiterator() {
    // Initialize the dateGenerator in the Tweet class
    Calendar cal = Calendar.getInstance();
    Tweet.setCalendar(cal);

    // Create a new TwitterFeed
    TwitterFeed feed = new TwitterFeed();

    // Add Tweets to the feed
    for (int i = 1; i <= 10; i++) {
      User user = new User("User" + i);
      // do some random likes and retweets
      if (i % 2 == 0) {
        user.verify();
      }
      Tweet tweet = new Tweet(user, "Tweet" + i);
      if (i % 2 == 0) {
        tweet.like();
        tweet.like();
      }
      if (i % 3 == 0) {
        tweet.retweet();
      }
      feed.addLast(tweet);
    }

    // Set the iteration mode and threshold
    feed.setMode(TimelineMode.LIKE_RATIO);
    // Create the iterator
    Iterator<Tweet> iterator = feed.iterator();

    // Iterate through the tweets and check their like ratios
    while (iterator.hasNext()) {
      Tweet tweet = iterator.next();
      double likeRatio = tweet.getLikesRatio();
      if (likeRatio < 0.5) {
        return false;
      }
    }

    // Now everything looks good!
    return true;
  }
}
