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

/**
 * This (very basic) data type models a Twitter user.
 */
public class User {
  // The verified status of this User (whether they have a blue checkmark or not)
  private boolean isVerified;
  // The username this User tweets under
  private String username;

  /**
   * Constructs a new User object with a given username.
   *
   * @param username the username of this User
   * @throws IllegalArgumentException if the given username contains an asterisk ("*") character, or
   *                                  is null/blank
   */
  public User(String username) throws IllegalArgumentException {
    if (username == null || username.isEmpty() || username.contains("*")) {
      throw new IllegalArgumentException("The username is invalid");
    }
    this.isVerified = false;
    this.username = username;
  }

  /**
   * Accesses the username of this User
   *
   * @return the username this User tweets under
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Determines whether the User is a verified user or not
   *
   * @return true if User is verified
   */
  public boolean isVerified() {
    return this.isVerified;
  }

  /**
   * Gives this User an important-looking asterisk
   */
  public void verify() {
    this.isVerified = true;
  }

  /**
   * Takes this User's important-looking asterisk away
   */
  public void revokeVerification() {
    this.isVerified = false;
  }

  /**
   * Creates a String representation of this User for display. For example, if this User's username
   * is "uwmadison" and they are verified, this method will return "@uwmadison*"; if this User's
   * username is "dril" and they are not verified, this method will return "@dril" (with no
   * asterisk).
   *
   * @return a String representation of this User as described above
   */
  @Override
  public String toString() {
    String res = "@" + this.username;
    // add the asterisk if necessary
    if (isVerified) {
      res += "*";
    }
    return res;
  }
}
