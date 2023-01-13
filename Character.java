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
 * This class models each character.
 *
 */
public class Character {
	private Room currentRoom; //current room the character is in
	private String label; //a label giving a basic description of the character
	/**
	 * COnstructor for the character class. It initializes all the class field variables
	 * @param currentRoom - The room of the character
	 * @param label - The description of the character
	 * @throws IllegalArgumentException - Thrown if the input room is null
	 */
	public Character(Room currentRoom, String label) throws IllegalArgumentException{
		if(currentRoom == null) {
			throw new IllegalArgumentException("Room is null");
		}
		this.currentRoom = currentRoom;
		this.label = label;
	}
	/**
	 * Gets the currentRoom of the character
	 * @return the currentRoom of the character
	 */
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	/**
	 * Gets the label of the character
	 * @return the label of the character
	 */
	public String getLabel() {
		return this.label;
	}
	/**
	 * Gets the arraylist of adjacent rooms to the character
	 * @return the adjrooms arraylist
	 */
	public ArrayList <Room> getAdjacentRooms(){
		return this.currentRoom.getAdjacentRooms();
	}
	/**
	 * Sets the current room of the player
	 * @param newRoom - input room to be set as the current room
	 */
	public void setCurrentRoom(Room newRoom) {
		this.currentRoom = newRoom;
	}
}
