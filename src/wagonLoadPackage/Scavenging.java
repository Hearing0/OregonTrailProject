package wagonLoadPackage;

import java.util.Random;

/**
 * Scavenging.java
 * Version 2.0 - May 2, 2024
 * Created by Cody Dusek
 * 
 * creates a SCAVENGING system for obtaining food while not at a fort
 */

public class Scavenging {

    Random rand = new Random();
    int foodAmount = 0;

    /**
     * generates an amount of food depending on a random number
     * 
     * @return - an integer representing the amount of food scavenged, in pounds
     */
    public int scavengeFood() {
        int foodCheck = rand.nextInt(6); // determines how much food should be added
        switch (foodCheck) {
            case 0:
                foodAmount = 20;
                break;
            case 1:
                foodAmount = 40;
                break;
            case 2:
                foodAmount = 60;
                break;
            case 3:
                foodAmount = 80;
                break;
            case 4:
                foodAmount = 100;
                break;
            case 5:
                foodAmount = 0;
                break;
        }

        int foodVary = rand.nextInt(5); // adds some variation to the amount of food
        boolean addToNothing = rand.nextBoolean();
        boolean addOrSubtract = rand.nextBoolean();
        if (foodAmount == 0) {
            if (addToNothing) // determines if the player should receive any food, or if they simply failed
                foodAmount += foodVary; // adds the variation to the food gained
            else
                foodAmount = 0; // results in no food being gained
        } else {
            if (addOrSubtract) // chooses whether or not to add or subtract the variation from the food value
                foodAmount += foodVary;
            else
                foodAmount -= foodVary;
        }
        return foodAmount;
    }

    /**
     * gets a prompt that represents how much food was gained, if any
     * 
     * @return - a String describing the amount of food gained
     */
    public String getFoodPrompt() {
        String foodPrompt;
        if (foodAmount != 0)
            foodPrompt = "You scavenged " + foodAmount + " pounds of food!";
        else
            foodPrompt = "You didn't find anything."; // returns a special prompt if 0 pounds of food was found
        return foodPrompt;
    }

}