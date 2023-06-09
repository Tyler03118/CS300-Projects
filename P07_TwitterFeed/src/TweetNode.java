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
//
/**
 * A container for a Tweet in a singly-linked list
 */
public class TweetNode {
  // The next TweetNode in this linked list
  private TweetNode nextTweet;
  //The tweet contained in this node
  private Tweet tweet;

  /**
   * Constructs a singly-linked node containing a tweet
   *
   * @param tweet the tweet to put in this node
   * @param next  the next tweet in the linked list
   */
  public TweetNode(Tweet tweet, TweetNode next) {
    this.tweet = tweet;
    this.nextTweet = next;
  }

  /**
   * Constructs a singly-linked node containing a tweet, with no successor tweet
   *
   * @param tweet the tweet to put in this node
   */
  public TweetNode(Tweet tweet) {
    this.tweet = tweet;
    this.nextTweet = null;
  }

  /**
   * Accesses the tweet in this node
   *
   * @return the tweet in this node
   */
  public Tweet getTweet() {
    return tweet;
  }

  /**
   * Accesses the next TweetNode in the list
   *
   * @return the successor tweetNode
   */
  public TweetNode getNext() {
    return nextTweet;
  }

  /**
   * Links this node to another node
   *
   * @next the successor TweetNode (can be null)
   */
  public void setNext(TweetNode next) {
    this.nextTweet = next;
  }
}
