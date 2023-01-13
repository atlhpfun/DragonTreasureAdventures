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
 * This class models the treasure room. This class is a subclass of Room
 *
 */
public class TreasureRoom extends Room {
	private static final String TREASURE_WARNING = "You sense that there is treasure nearby. \n"; //The warning for when the player is near the treasure
	private static PImage treasureBackground; //the image ALWAYS used for treasure rooms
	/**
	 * The constructor for the treasure room. It initializes all the subclass fields
	 * @param ID - The input ID for the room
	 */
	public TreasureRoom(int ID) {
		super(ID, TREASURE_WARNING, treasureBackground);
	}
	/**
	 * Gets the treasure warning for when the player is near the treasure
	 * @return - the treasure warning
	 */
	public static String getTreasureWarning() {
		return TREASURE_WARNING;
	}
	/**
	 * Sets the PImage for the treasurebackground
	 * @param treasureBackground - The input image to be set
	 */
	public static void setTreasureBackground(processing.core.PImage treasureBackground) {
		TreasureRoom.treasureBackground = treasureBackground;
	}
	/**
	 * Checks if the player is in the treasure room
	 * @param p - Input player
	 * @return - True if player has key and room is treasure room, false otherwise
	 */
	public boolean playerCanGrabTreasure(Player p) {
		if(p.hasKey() && p.getCurrentRoom() instanceof TreasureRoom) {
			return true;
		}
		return false;
	}
}
