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
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an iterator that moves through tweets in reverse chronological order
 */
public class ChronoTwiterator implements Iterator<Tweet> {
  // The TweetNode containing the next tweet to be returned to the iteration
  private TweetNode next;

  /**
   * Constructs a new twiterator at the given starting node.
   *
   * @param startNode the node to begin the iteration at
   */
  public ChronoTwiterator(TweetNode startNode) {
    this.next = startNode;
  }

  /**
   * Checks whether there is a next tweet to return.
   *
   * @return true if there is a next tweet, false if the value of next is null
   */
  @Override
  public boolean hasNext() {
    return next != null;
  }

  /**
   * Returns the next tweet in the iteration if one exists, and advances next to the next tweet.
   *
   * @throws NoSuchElementException if there is not a next tweet to return
   */
  @Override
  public Tweet next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("No more Tweets");
    }
    Tweet currentTweet = next.getTweet();
    next = next.getNext();
    return currentTweet;
  }
}
