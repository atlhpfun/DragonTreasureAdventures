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
 * This class models the room that are within the game.
 *
 */
public class Room {
	private String description; //verbal description of the room
	private ArrayList<Room> adjRooms; //list of all rooms directly connect
	private final int ID; // a "unique" identifier for each room
	protected static PApplet processing; //PApplet object which the rooms will use to
	//draw stuff to the GUI
	private PImage image; //stores the image that corresponds to the background of a room
	/**
	 * This constructor initializes the Room fields
	 * @param ID - The identifier for each room
	 * @param description - The description of each room
	 * @param image - Holds the background image of a room
	 */
	public Room(int ID, String description, processing.core.PImage image) {
		this.ID = ID;
		this.description = description;
		this.image = image;
		this.adjRooms = new ArrayList <Room>();
	}
	/**
	 * Gets the ID
	 * @return the ID
	 */
	public int getID() {
		return this.ID;
	}
	/**
	 * Gets the description
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * Gets the adjacent rooms arraylist
	 * @return the adjrooms arraylist
	 */
	public ArrayList <Room> getAdjacentRooms(){
		return adjRooms;
	}
	/**
	 * Sets the processing PApplet object's value
	 * @param processing - the PApplet object used to draw things to the GUI
	 */
	public static void setProcessing(processing.core.PApplet processing) {
		Room.processing = processing;
	}
	/**
	 * Adds Rooms to the adjacent rooms arraylist
	 * @param toAdd - Room to add to the arraylist
	 */
	public void addToAdjacentRooms(Room toAdd) {
		this.adjRooms.add(toAdd);
	}
	/**
	 * Checks if an input room is in the adjacent room arraylist
	 * @param r - the input room to be checked
	 * @return return - true if the room is in the list, false otherwise
	 */
	public boolean isAdjacent(Room r) {
		return adjRooms.contains(r);
	}
	@Override
	/**
	 * Checks if two Rooms are equal to each other by ID
	 */
	public boolean equals(Object other) {
		if(other instanceof Room) {
		      Room otherRoom = (Room)other;
		      return this.ID == otherRoom.ID;
		    }
		    
		    return false;
	}
	@Override
	/**
	 * Converts a Room into its ID, description, and prints all adjacent rooms
	 */
	public String toString() {
		 String s = this.ID + ": " + this.description+ "\n Adjacent Rooms: ";
		    for(int i = 0; i < adjRooms.size(); i++){
		      s+= adjRooms.get(i).ID +" ";
		    }
		    
		    return s;
	}
	/**
	 * Outputs the image onto the window, and fills the blank space with color and text
	 */
	public void draw() {
		processing.image(image, 0, 0);
		processing.fill(-7028);
		processing.rect(0, 500, 800, 600);
		processing.fill(0);
		processing.text(toString(), 300, 525);
	}
	
}
