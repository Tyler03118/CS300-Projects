// DO NOT SUBMIT THIS CLASS TO GRADESCOPE
/**
 * This class models and simulates the Bottle filling and capping system in a Bottle Factory. A
 * Bottle supply line is first presented as a linked queue. Then, each Bottle is first added to a
 * filling queue, once filled, the bottle is going to be added to the capping queue. Capped Bottles
 * will be enqueued into a linked queue production line. Both filling and capping
 *
 */
public class BottleFillerMachine {

  private CircularBottleQueue fillingQueue; // queue of bottles to be filled
  private CircularBottleQueue cappingQueue; // queue of filled bottles to be capped

  private LinkedBottleQueue supplyLine; // queue of unfilled & uncapped bottles
  private LinkedBottleQueue productionLine; // queue of filled & capped bottles

  private int remainingBottlesCount; // number of bottles yet to be filled & capped

  /**
   * Initialize the queues and other fields for BottleFillerMachine
   *
   * @param productionLineCapacity capacity of the production line queue
   * @param fillingCapacity        capacity of the filler queue
   * @param cappingCapacity        capacity of the capper queue
   * @param supplyLineCapacity     capacity of the supply line queue
   * @throws IllegalArgumentException if any of the provided capacities is negative (less or equal
   *                                  to zero)
   */
  public BottleFillerMachine(int productionLineCapacity, int fillingCapacity, int cappingCapacity,
      int supplyLineCapacity) {

    if (fillingCapacity <= 0 || cappingCapacity <= 0 || productionLineCapacity <= 0
        || supplyLineCapacity <= 0) {
      throw new IllegalArgumentException("Capacity of a queue needs to be positive");
    }
    remainingBottlesCount = 0;
    productionLine = new LinkedBottleQueue(productionLineCapacity);
    fillingQueue = new CircularBottleQueue(fillingCapacity);
    cappingQueue = new CircularBottleQueue(cappingCapacity);
    supplyLine = new LinkedBottleQueue(supplyLineCapacity);
  }

  /**
   * Fills the front bottle of the filling queue and adds it to capping line If capping line is
   * full, cap the front bottle to make space
   */
  public void fillBottle() {
    Bottle bottle = fillingQueue.dequeue();
    bottle.fillBottle();
    // move the bottle to capping queue
    boolean workDone = false;
    while (!(workDone)) {
      if (cappingQueue.isFull()) {
        // capping queue is full, cap a bottle to make space
        capBottle();
      } else {
        // bottle moved from filling queue to capping queue
        cappingQueue.enqueue(bottle);
        workDone = true;
      }
    }
  }

  /**
   * Caps the front bottle of the capping queue and adds it to supply line If supply line is full,
   * remove the front bottle and print its details
   */
  public void capBottle() {

    Bottle bottle = cappingQueue.dequeue();
    bottle.sealBottle();
    // move the bottle to capping queue
    boolean workDone = false;
    while (!(workDone)) {
      if (supplyLine.isFull()) {
        System.out.println("Bottle " + supplyLine.dequeue().toString() + "!");
        System.out.println("Number of bottles remaining to fill & cap:" + (--remainingBottlesCount));
      } else {
        // bottle moved from filling queue to capping queue
        supplyLine.enqueue(bottle);
        workDone = true;
      }
    }
  }

  /**
   * Adds the bottle to production line, if line is full moves the front bottle to filling queue. If
   * filling queue is also full, fills a bottle to make space
   *
   * @param bottle Bottle to add to production
   */
  public void addToProduction(Bottle bottle) {

    boolean addedBottle = false;
    while (!addedBottle) {
      // If the line is full, move bottle to filling queue
      if (productionLine.isFull()) {
        if (fillingQueue.isFull()) {
          // if filling queue is full, fill a bottle
          fillBottle();
        }
        // adding to filling queue
        fillingQueue.enqueue(productionLine.dequeue());
      } else {
        // add bottle to production line
        productionLine.enqueue(bottle);
        addedBottle = true;
      }
    }
  }

  /**
   * Simulates the bottle factory: add bottles to production, fill, cap and supply
   *
   * @param numBottles Number of bottles to be filled and capped
   */
  public void runMachine(int numBottles) {

	if(numBottles <= 0) {
		System.out.println("No bottles to fill! Non-positive number of bottles " + numBottles + " requested");
		return;
	}

	System.out.println("Bottle Filler Machine to produce " + numBottles + " bottles.");

    // Initialize production line with full capacity
    remainingBottlesCount = numBottles;
    while (!(this.productionLine.isFull())) {
      productionLine.enqueue(new Bottle("Blue"));
      numBottles--;
    }

    while (remainingBottlesCount != 0) {
      if (numBottles != 0) {
        addToProduction(new Bottle("Blue"));
        numBottles--;
      } else if (!productionLine.isEmpty()) {
        if (fillingQueue.isFull()) {
          fillBottle();
        }
        fillingQueue.enqueue(productionLine.dequeue());
      } else if (!fillingQueue.isEmpty()) {
        fillBottle();
      } else if (!cappingQueue.isEmpty()) {
        capBottle();
      } else if (!supplyLine.isEmpty()) {
        System.out.println("Bottle " + supplyLine.dequeue().toString() + "!");
        System.out.println("Number of bottles remaining to fill & cap:" + (--remainingBottlesCount));
      }
    }
    System.out.println("All bottles filled!");
  }


  /**
   * Returns a string representation of the current state
   *
   * @return a String representation of the current state of this filling machine
   */
  @Override
  public String toString() {
    String representation = "Bottle Factory\n";
    representation = representation.concat("Production Line:");
    representation = representation.concat(productionLine.toString() + "\n");
    representation = representation.concat("Filling Queue:");
    representation = representation.concat(fillingQueue.toString() + "\n");
    representation = representation.concat("Capping Queue:");
    representation = representation.concat(cappingQueue.toString() + "\n");
    representation = representation.concat("Supply Line:");
    representation = representation.concat(supplyLine.toString());
    return representation;
  }

  /**
   * This the main method for the bottle factory (optional - testing purpose)
   * @param args List of input arguments if any
   */
  public static void main(String[] args) {
	int productionCapacity = 3, supplyCapacity = 3, fillingCapacity = 2, cappingCapacity = 2;
    BottleFillerMachine factory = new BottleFillerMachine(productionCapacity, fillingCapacity, cappingCapacity, supplyCapacity);
	System.out.println("Bottle Filler Machine Configuration\n"
			+ "Production Line("+ productionCapacity+") | Filling Queue("+ fillingCapacity+") | Capping Queue("+cappingCapacity+") | SupplyLine("+supplyCapacity+")");
    factory.runMachine(15);
    Bottle.resetBottleCounter();
    factory.runMachine(10);
    Bottle.resetBottleCounter();
    factory.runMachine(5);
    Bottle.resetBottleCounter();
    factory.runMachine(0);
  }
}
