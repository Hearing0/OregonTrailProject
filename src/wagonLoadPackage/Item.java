package wagonLoadPackage;

/**
 * Item.java 
 * Created on 3/23/2024
 * By David Flores
 * 
 * Creates a item that stores its name, weight (in pounds), 
 * and whether it is loaded into the wagon. Weight and isLoaded
 * can be set.
 */
public class Item {
	// Initialize Variables
    final String name;
    int weight; // in lbs
    boolean isLoaded;

    
    /**
     * Initializes Item with its name and weight.
     * @param name - String of the Item's name
     * @param weight - Integer of the Item's weight
     */
    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
        this.isLoaded = false;
    }
    
    /**
     * Get weight of item
     * @return - weight of item instance
     */
    public int getWeight() {
    	return this.weight;
    }
    
    /**
     * Sets weight of item
     * @param newWeight - New weight of item instance.
     */
    public void setWeight( int newWeight ) {
    	this.weight = newWeight;
    }
    
    /**
     * Sets isLoaded to tell whether or not the item
     * has been loaded into the wagon.
     * @param flag - Boolean that is set to isLoaded.
     */
    public void setIsLoaded( boolean flag ) {
    	this.isLoaded = flag;
    }
    
    /**
     * Returns whether the item is loaded into the wagon.
     * @return - Boolean; True if loaded, otherwise, false.
     */
    public boolean getIsLoaded() {
    	return this.isLoaded;
    }
    
    // TODO: Setter for name
}
