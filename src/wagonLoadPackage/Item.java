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
     * Creates an unloaded Item with specified name and weight.
     * @param name - String of the Item's name
     * @param weight - Integer of the Item's weight
     */
    public Item(String name, int weight) {
        this(name, weight, false);
    }
    
    /**
     * Initializes Item with its name and weight.
     * @param name - String of the Item's name
     * @param weight - Integer of the Item's weight
     * @param isLoaded - Boolean of if Item is loaded in the wagon
     */
    public Item(String name, int weight, boolean isLoaded) {
        this.name = name;
        this.weight = weight;
        this.isLoaded = isLoaded;
    }
    
    /**
     * Get weight of item
     * @return - weight of item instance
     */
    public int getWeight() {
    	return this.weight;
    }
    
    /**
     * Sets weight of item. If new weight is greater than 0,
     * sets isLoaded to true, otherwise false
     * @param newWeight - New weight of item instance.
     */
    public void setWeight( int newWeight ) {
    	if (newWeight > 0) {
    		this.weight = newWeight;
    	}
    	
    	if (this.weight > 0 ) {
    		this.isLoaded = true;
    	} else {
    		this.isLoaded = false;
    	}
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
 
    /**
     * Returns the item's name
     * @return - Item name
     */
    public String getName() {
    	return this.name;
    }
}
