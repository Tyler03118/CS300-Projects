
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class models a graphic Thing which can be drawn at a give (x,y) position within the display
 * window of a graphic application.
 *
 */
public class Thing {
  // Fields defined to draw a Thing in the application display window
  /**
   * PApplet object that represents the display window of this graphic application
   */
  private static PApplet processing; // PApplet object that represents the display window

  /**
   * image of this graphic thing of type PImage
   */
  private PImage image; // image of this graphic thing

  /**
   * x-position of this thing in the display window
   */
  private float x; // x-position of this thing in the display window
  /**
   * y-position of this thing in the display window
   */
  private float y; // y-position of this thing in the display window

  /**
   * Creates a new graphic Thing located at a specific (x, y) position of the display window
   * 
   * @param x             x-coordinate of this thing in the display window
   * @param y             y-coordinate of this thing in the display window
   * @param imageFilename filename of the image of this thing, for instance "name.png"
   */
  public Thing(float x, float y, String imageFilename) {
    // Set drawing parameters
    this.image = processing.loadImage("images" + File.separator + imageFilename);
    // sets the position of this decoration object
    this.x = x;
    this.y = y;
  }

  /**
   * Draws this thing to the display window at its current (x,y) position
   */
  public void draw() {
    // draw the thing at its current position
    processing.image(this.image, this.x, y);

  }

  /**
   * Sets the PApplet object display window where this Thing will be drawn. The processing PApplet
   * static data field should be set to Badger.getProcessing() since Badgers and Thing objects are going
   * to be displayed to the same screen.
   */
  public static void setProcessing() {
    processing = Badger.getProcessing();
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
   * Returns the x-position of this thing in the display window
   * 
   * @return the X coordinate of the thing position
   */
  public float getX() {
    return x;
  }

  /**
   * Returns the y-position of this thing in the display window
   * 
   * @return the y-position of the thing
   */
  public float getY() {
    return y;
  }


  /**
   * Sets the x-position of this thing in the display window
   * 
   * @param x the x-position to set
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Sets the y-position of this thing in the display window
   * 
   * @param y the y-position to set
   */
  public void setY(float y) {
    this.y = y;
  }
}
