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
 * This class models the player of the game. It is a subclass of the character class, and implements Moveable
 *
 */
public class Player extends Character implements Moveable{
	private boolean hasKey; // boolean that represents if the player has the key, if so, is true, false otherwise
	/**
	 * Constructor for the player. It initializes the data fields for the class
	 * @param currentRoom - The current Room of the player to be set to
	 * @throws IllegalArgumentException - If the constructor input isn't a startRoom
	 */
	public Player(Room currentRoom) throws IllegalArgumentException{
		super(currentRoom, "PLAYER");
		if(!(currentRoom instanceof StartRoom)) {
			throw new IllegalArgumentException("Constructor Room isn't a StartRoom");
		}
		hasKey = false;
	}
	/**
	 * Checks if player has the key.
	 * @return true if they have the key, false otherwise
	 */
	public boolean hasKey() {
		return hasKey;
	}
	/**
	 * Gives player the key by setting hasKey to true.
	 */
	public void obtainKey() {
		hasKey = true;
	}
	
	
	/**
	 * Changes the room of the player.
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
		if(super.getAdjacentRooms().contains(destination)) {
			return true;
		}
		return false;
	}
	/**
	 * determines if player should teleport
	 * @return - true if the player is in a portalRoom, false otherwise
	 */
	public boolean teleport() {
		if(super.getCurrentRoom() instanceof PortalRoom) {
			return true;
		}
		return false;
	}
	/**
	 * Checks if a portal is in an adjacent room
	 * @return true if so, false otherwise
	 */
	public boolean isPortalNearby() {
		for(int i = 0; i < super.getAdjacentRooms().size(); i++) {
			if(super.getAdjacentRooms().get(i) instanceof PortalRoom) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Checks if treasure room is in an adjacent room
	 * @return true if so, false otherwise
	 */
	public boolean isTreasureNearby() {
		for(int i = 0; i < super.getAdjacentRooms().size(); i++) {
			if(super.getAdjacentRooms().get(i) instanceof TreasureRoom) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Checks if Dragon is in adjacent room
	 * @param d - Input dragon
	 * @return - true if it is, false otherwise.
	 */
	public boolean isDragonNearby(Dragon d) {
		for(int i = 0; i < super.getAdjacentRooms().size(); i++) {
			if(super.getAdjacentRooms().contains(d.getCurrentRoom())) {
				return true;
			}
		}
		return false;
	}
}
