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
 * This class models a portal Room. This class is a subclass of Room.
 *
 */
public class PortalRoom extends Room {
	private Random randGen; //random number generator for location picking
	private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
	private static final String TELEPORT_MESSAGE = "The space distortion teleported you to another room!\n";
	private static PImage portalImage; //image of a portal to be shown in all portal rooms
	
	/**
	 * Constructor for portal Room. It initializes all the field values for it.
	 * @param ID - Input ID of room
	 * @param description - Input description of room
	 * @param image - input PImage of the room
	 */
	public PortalRoom(int ID, String description, processing.core.PImage image) {
		super(ID, description, image);
		randGen = new Random(); // initializes randgen
	}
	/**
	 * Gets the Portal Warning
	 * @return - the portal warning
	 */
	public static String getPortalWarning() {
		return PortalRoom.PORTAL_WARNING;
	}
	/**
	 * Gets the teleport message
	 * @return the teleport message
	 */
	public static String getTeleportMessage() {
		return PortalRoom.TELEPORT_MESSAGE;
	}
	/**
	 * Gets the teleport location
	 * @return - the teleport location
	 */
	public Room getTeleportLocation() {
		int hold = randGen.nextInt(this.getAdjacentRooms().size());
		return this.getAdjacentRooms().get(hold);
	}
	/**
	 * sets the portal Image
	 * @param portalImage - Input PImage to be set to the portal Image
	 */
	public static void setPortalImage(processing.core.PImage portalImage) {
		PortalRoom.portalImage = portalImage;
	}
	/**
	 * Draws the portal image on the background
	 */
	public void draw() {
		super.draw();
		Room.processing.image(portalImage, 325, 225);
		
	}
	
}
