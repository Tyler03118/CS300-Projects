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
import java.util.Date;

/**
 * This data type models a tweet - a short text post made on the social media service Twitter
 */
public class Tweet {
  // A shared Calendar object for this class to use to generate consistent dates.
  private static Calendar dateGenerator;
  // A value determining the maximum length of a tweet.
  private static final int MAX_LENGTH = 280;
  // The User associated with this tweet
  private User user;
  // The text of this tweet
  private String text;
  // The number of likes this tweet has
  private int numLikes;
  // The number of retweets this tweet has
  private int numRetweets;
  // The date and time this tweet was posted, a Date object created by calling
  // dateGenerator.getTime() at construction time
  private Date timestamp;

  /**
   * Creates a fresh, new tweet by the given user. This tweet has no likes or retweets yet, and its
   * timestamp should be set to the current time.
   *
   * @param user the user posting this tweet
   * @param text the text of the tweet
   * @throws IllegalArgumentException - if the tweet's text exceeds MAX_LENGTH characters
   * @throws NullPointerException     - if the provided text or user are null
   * @throws IllegalStateException    - if the dateGenerator field has not yet been initialized
   */
  public Tweet(User user, String text)
      throws IllegalArgumentException, NullPointerException, IllegalStateException {
    if (user == null || text == null) {
      throw new NullPointerException("User or text cannot be null");
    }
    if (text.length() > MAX_LENGTH) {
      throw new IllegalArgumentException("Tweet text exceeds maximum length");
    }
    if (dateGenerator == null) {
      throw new IllegalStateException("DateGenerator has not been initialized");
    }
    this.user = user;
    this.text = text;
    this.numLikes = 0;
    this.numRetweets = 0;
    this.timestamp = dateGenerator.getTime();
  }

  /**
   * Initializes the dateGenerator static field to the provided Calendar object. For tests which do
   * not require a consistent date, you can use Calendar.getInstance() to get a Calendar set to the
   * current time. If your tests require a consistent date, see the writeup for examples of how to
   * set the time.
   *
   * @param c the Calendar to use for date generation for this run of the program
   */
  public static void setCalendar(Calendar c) {
    dateGenerator = c;
  }

  /**
   * Accesses the text of this tweet
   *
   * @return the text of this tweet
   */
  public String getText() {
    return this.text;
  }

  /**
   * Gets the total engagement numbers (likes + retweets) of this tweet
   *
   * @return the total engagement of this tweet
   */
  public int getTotalEngagement() {
    return this.numLikes + this.numRetweets;
  }

  /**
   * Checks whether the author of this tweet is a verified user
   *
   * @return true if this tweet's User is verified, false otherwise
   */
  public boolean isUserVerified() {
    return this.user.isVerified();
  }

  /**
   * Returns the proportion of the total engagement that is composed of "likes". We only do
   * positive, uplifting ratios around here.
   *
   * @return the ratio of likes to total engagement , as a value between 0.0 and 1.0, or -1 if the
   * total engagement is 0.
   */
  public double getLikesRatio() {
    int totalEngagement = getTotalEngagement();
    if (totalEngagement == 0) {
      return -1;
    }
    return (double) this.numLikes / totalEngagement;
  }

  /**
   * Add one (1) to the number of likes for this tweet
   */
  public void like() {
    this.numLikes++;
  }

  /**
   * Add one (1) to the number of retweets for this tweet
   */
  public void retweet() {
    this.numRetweets++;
  }

  /**
   * Compares the contents of this tweet to the provided object. If the provided object is a Tweet
   * that contains the same text posted at the same time by the same User (use the toString() method
   * from User to compare these!) then the two Tweets are considered equal regardless of their
   * respective likes/retweets.
   *
   * @param o the object to compare this Tweet to
   * @return true if this Tweet is equivalent to the provided object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    // downcasting
    if (o instanceof Tweet) {
      Tweet other = (Tweet) o;
      return this.user.toString().equals(other.user.toString()) && this.text.equals(
          other.text) && this.timestamp.equals(other.timestamp);
    }
    return false;
  }

  /**
   * A string representation of this tweet. See writeup for examples.
   *
   * @return a formatted string representation of this tweet
   */
  @Override
  public String toString() {
    return "tweet from " + user.toString() + " at " +
        timestamp + ":\n" + "-- " +
        text + "\n" + "-- likes: " +
        numLikes + "\n" + "-- retweets: " +
        numRetweets;
  }
}
