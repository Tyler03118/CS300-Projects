import processing.core.PImage;
import processing.core.PApplet;
import java.io.File;
import java.util.Random;
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    DancingBadgers
// Course:   CS 300 Spring 2023
//
// Author:   Ziji Li
// Email:    zli2296@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////


/**
 * The class for creating methods for dancing badgers game
 */
public class DancingBadgers {
  // Declare DancingBadgers Static Fields
  private static PImage backgroundImage;
  private static Badger[] badgers;
  private static Random randGen;

  /**
   * A main method for creating graphical window
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * Set up the dancing badger system
   */
  public static void setup() {
    randGen = new Random();
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    badgers = new Badger[5];
    keyPressed();
  }

  /**
   * Draw the background and badgers according to commands
   */
  public static void draw() {
    Utility.background(Utility.color(255, 218, 185));
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);
    // Draw badgers via loop
    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] != null) {
        badgers[i].draw();
      }
    }
    mousePressed();
    mouseReleased();
  }

  /**
   * Checks if the mouse is over a specific Badger whose reference is provided as input parameter
   *
   * @param Badger reference to a specific Badger object
   * @return true if the mouse is over the specific Badger object passed as input (i.e. over the
   * image of the Badger), false otherwise
   */
  public static boolean isMouseOver(Badger Badger) {
    // Access the height and width
    if (Badger != null) {
      PImage badgerImage = Badger.image();
      int height = badgerImage.height;
      int width = badgerImage.width;
      // Ensure the mouse is within the boundary of height and width
      if (Utility.mouseX() >= Badger.getX() - width / 2
          && Utility.mouseX() <= Badger.getX() + width / 2
          && Utility.mouseY() >= Badger.getY() - height / 2
          && Utility.mouseY() <= Badger.getY() + height / 2) {
        return true;
      }
    }
    return false;
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    // Drag the badger when the mouse if over it
    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] != null && isMouseOver(badgers[i])) {
        badgers[i].startDragging();
        return;
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    // Stop dragging all the badgers
    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] != null) {
        badgers[i].stopDragging();
      }
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    // Add a badger when 'b' or 'B' is pressed
    if (Utility.key() == 'b' || Utility.key() == 'B') {
      for (int i = 0; i < badgers.length; i++) {
        if (badgers[i] == null) {
          badgers[i] = new Badger((float) randGen.nextInt(Utility.width()),
              (float) randGen.nextInt(Utility.height()));
          return;
        }
      }
    }
    // Remove a badger when 'r' or 'R' is pressed and is mouseOver
    if (Utility.key() == 'r' || Utility.key() == 'R') {
      for (int i = 0; i < badgers.length; i++) {
        if (isMouseOver(badgers[i])) {
          badgers[i] = null;
          return;
        }
      }
    }
  }
}



