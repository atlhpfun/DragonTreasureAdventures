//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    DragonTreasureAdventure simulates the loading and setup of the game. It also
// draws the images and outputs the warnings.
// Course:   CS 300 Fall 2022
//
// Author:   Rohan Balachander
// Email:    rbalachander@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Vincent Gallegos
// Partner Email:   vggallegos@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __x_ Write-up states that pair programming is allowed for this assignment.
//   __x_ We have both read and understand the course Pair Programming Policy.
//   __x_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * 
 * This class models the Dragon of the game. It is a subclass of the character class, and implements Moveable
 *
 */
public class Dragon extends Character implements Moveable{
	private Random randGen; //random num generator used for moving
	private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
	private static final String DRAGON_ENCOUNTER = "Oh no! You ran into the fire breathing dragon!\n";
	/**
	 * Constructor for the dragon. It initializes the data fields for the class
	 * @param currentRoom - The current Room of the dragon to be set to
	 * @throws IllegalArgumentException - If the constructor input isn't a treasureRoom
	 */
	public Dragon(Room currentRoom) throws IllegalArgumentException{
		super(currentRoom, "DRAGON");
		if(!(currentRoom instanceof TreasureRoom)) {
			throw new IllegalArgumentException("Room isn't a TreasureRoom");
		}
		randGen = new Random();
	}
	/**
	 * Changes the room of the dragon.
	 * @param destination - the room to be changed to
	 */
	public boolean changeRoom(Room destination) {
		if(canMoveTo(destination)) {
			super.setCurrentRoom(destination);
			return true;
		}
		return false;
	}
	/**
	 * Checks if the player can move the input room
	 * @param destination - the room to be checked to see if it can be moved to.
	 * @returns - true if can be moved, false otherwise
	 */
	public boolean canMoveTo(Room destination) {
		if(super.getAdjacentRooms().contains(destination) && !(destination instanceof PortalRoom)) {
			return true;
		}
		return false;
	}
	/**
	 * Randomly generates room for dragon to move to
	 * @return the room for the dragon to move to
	 */
	public Room pickRoom() {
		int hold = randGen.nextInt(this.getAdjacentRooms().size());
		return this.getAdjacentRooms().get(hold);
	}
	/**
	 * Gets the warning for the dragon
	 * @return the dragon warning
	 */
	public static String getDragonWarning() {
		return DRAGON_WARNING;
	}
	/**
	 * Gets the Dragon encounter message
	 * @return the dragon encounter message
	 */
	public static String getDragonEncounter() {
		return DRAGON_ENCOUNTER;
	}
}
