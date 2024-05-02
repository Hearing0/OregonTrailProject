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
 * Note that the nutrientPerLbs is disabled
 */
public class FoodItem extends Item {
	
	// Initialize Variables
    //boolean cookable;
    //boolean edible;
    //int uncookedFood = 0;
    
    
    
    /**
     * Initializes new pre-loaded food item to the item HashMap
     * @param name - Name of the item
     * @param weight - Weight of the item (in lbs)
     * @param cookable - Whether the food item can be cooked.
     * @param edible - Whether the food item can be eaten.
     * @param isLoaded - Whether the food item is loaded in the wagon
     */
    public FoodItem(String name, int weight, boolean isLoaded) {
        super(name, weight, isLoaded);
        //this.cookable = cookable;
        //this.edible = edible;
    }
    
        
    /**
     * Initializes new food item to the item HashMap
     * @param name - Name of the item
     * @param weight - Weight of the item (in lbs)
     * @param cookable - Whether the food item can be cooked.
     * @param edible - Whether the food item can be eaten.
     */
    public FoodItem(String name, int weight, boolean cookable, boolean edible) {
        super(name, weight);
        //this.cookable = cookable;
        //this.edible = edible;
    }
    
    
    
    /**
     * Checks whether the FoodItem is cookable
     * @return - Returns boolean cookable. If true, the FoodItem is cookable. 
     */
    /*
    public boolean getCookable() {
    	return cookable;
    }
    
    
    /**
     * Checks whether the FoodItem is edible.
     * @return - True if nutrientPerLbs is greater than zero. False otherwise. 
     */
   /*
    public boolean isEdible() {
    	
    	boolean edible = false;
    	
    	if ( this.nutrientPerLbs > 0) {
    		edible = true;
    	}
    	
    	
    	return this.edible;
    }
    
    */
    
    /**
     * Consume FoodItem weight by subtracting from weight by nutrientPerLbs. 
     * Will not consume if weightConsumed is greater than weight of FoodItem.
     * weight =- weightConsumed / nutrientPerLbs
     * @param weightConsumed - Amount, in lbs, to subtract from weight
     * @return - True if weight was able to be adjusted by weightConsumed. 
     * Can return false if FoodItem is not edible.
     * DEPRECIATED
     */
    public boolean eatFood( int consumeSelect, int partyMembers) {
		boolean result = false;
		
		// Check edible and edible amount
		if ( this.weight >= consumeSelect ) {
			
			int consumeAmt = partyMembers * consumeSelect;
			
			// Consume weight w/ nutrient scaling factor
			this.weight =- this.weight - consumeAmt;
			result = true;
		} 
		
		// Fail: Cannot Eat
		else {
			result = false;
		}
		    	
    	return result;
    }
    
    /*
    //uses previous method to check if its possible to eat
    //if true, then remove the foodWeight and return the new weight
    //if false, then return -1 to signal that no food is left
    public int dailyConsume(int weightConsumed, int consumeSelect, int people) {
    	if(eatFood(weightConsumed) == true) {
    		this.weight = weightConsumed - (consumeSelect * people);
    	}
    	else {
    		this.weight = -1;
    		//put with a "you died" text pop-up?
    	}
    	return this.weight;
    }
    */
    
    
}