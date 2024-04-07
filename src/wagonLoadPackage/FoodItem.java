package wagonLoadPackage;


/**
 * FoodItem.java 
 * Created on 3/23/2024
 * By David Flores
 * 
 * Creates a FoodItem that inherits Item 
 * , sets whether it is cookable, can be consumed,
 * and consumption is factored by the FoodItem's nutrientPerLbs.
 * 
 * Note that the nutrientPerLbs is disabled for MP3!!!
 */
public class FoodItem extends Item {
	
	// Initialize Variables
    boolean cookable;
    boolean edible;
    //int nutrientPerLbs;
    
    
    
    /**
     * Initializes new food item to the item HashMap
     * @param name - Name of the item
     * @param weight - Weight of the item (in lbs)
     * @param cookable - Whether the food item can be cooked.
     * @param edible - Whether the food item can be eaten.
     * 
     * TODO: Add nutrientsPerLbs
     * int nutrientPerLbs
     * this.nutrientPerLbs = nutrientPerLbs;
     */
    public FoodItem(String name, int weight, boolean cookable, boolean edible) {
        super(name, weight);
        this.cookable = cookable;
        this.edible = edible;
    }
    
    
    /**
     * Checks whether the FoodItem is cookable
     * @return - Returns boolean cookable. If true, the FoodItem is cookable. 
     */
    public boolean getCookable() {
    	return cookable;
    }
    
    
    /**
     * Checks whether the FoodItem is edible.
     * @return - True if nutrientPerLbs is greater than zero. False otherwise. 
     */
    public boolean isEdible() {
    	/*
    	boolean edible = false;
    	
    	if ( this.nutrientPerLbs > 0) {
    		edible = true;
    	}
    	*/
    	
    	return this.edible;
    }
    
    
    /**
     * Consume FoodItem weight by subtracting from weight by nutrientPerLbs. 
     * Will not consume if weightConsumed is greater than weight of FoodItem.
     * weight =- weightConsumed / nutrientPerLbs
     * @param weightConsumed - Amount, in lbs, to subtract from weight
     * @return - True if weight was able to be adjusted by weightConsumed. 
     * Can return false if FoodItem is not edible.
     */
    /*
    public boolean eatFood( int weightConsumed ) {
		boolean result = false;
		
		// Check edible and edible amount
		if ( this.isEdible() == true && this.weight >= weightConsumed ) {
			// Consume weight w/ nutrient scaling factor
			this.weight =- weightConsumed / this.nutrientPerLbs;						
		} 
		
		// Fail: Not possible to Eat (not edible or amount is too much)
		else {
			result = false;
		}
		    	
    	return result;
    }
    */
}