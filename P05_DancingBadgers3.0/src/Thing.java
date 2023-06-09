//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P05_DancingBadgers3.0
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
// Online Sources:  https://www.geeksforgeeks.org/arraylist-removeif-method-in-java/
//                  https://www.w3schools.com/java/java_lambda.asp
//  (Learned how to use removeIf method; Learned how to write concise code via lambda expressions)
//
///////////////////////////////////////////////////////////////////////////////

import processing.core.PImage;
import java.io.File;

/**
 * This class models a graphic Thing which can be drawn at a give (x,y)
 * position within the display window of a graphic application.
 */
public class Thing {
  //image of this graphic thing of type PImage
  private processing.core.PImage image;
  //PApplet object that represents the display window of this graphic application
  protected static processing.core.PApplet processing;
  //x-position of this thing in the display window
  protected float x;
  //y-position of this thing in the display window
  protected float y;

  /**
   * Creates a new graphic Thing located at a specific (x, y) position of the display window
   *
   * @param x             x-coordinate of this thing in the display window
   * @param y             y-coordinate of this thing in the display window
   * @param imageFilename filename of the image of this thing, for instance "name.png"
   */
  public Thing(float x, float y, String imageFilename) {
    this.x = x;
    this.y = y;
    this.image = processing.loadImage("images" + File.separator + imageFilename);
  }


  /**
   * Draws this thing to the display window at its current (x,y) position
   */
  public void draw() {
    // draw the thing at its current position
    processing.image(this.image, this.x, y);
  }

  /**
   * Returns a reference to the image of this thing
   *
   * @return the image of type PImage of the thing object
   */
  public PImage image() {
    return image;
  }

  /**
   * Sets the PApplet object display window where this Thing object will be drawn
   */
  public static void setProcessing(processing.core.PApplet processing) {
    Thing.processing = processing;
  }

  /**
   * Checks if the mouse is over this Thing object
   *
   * @return true if the mouse is over this Thing object
   */
  public boolean isMouseOver() {
    int objectWidth = this.image().width;
    int objectHeight = this.image().height;

    // checks if the mouse is over this Thing object
    return processing.mouseX >= this.x - objectWidth / 2 &&
        processing.mouseX <= this.x + objectWidth / 2 &&
        processing.mouseY >= this.y - objectHeight / 2 &&
        processing.mouseY <= this.y + objectHeight / 2;
  }
}
