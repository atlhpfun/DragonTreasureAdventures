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
 * This class loads, sets up, and outputs the images and text of the Dragon Treasure game
 *
 */
public class DragonTreasureGame extends PApplet{
	private ArrayList<Room> roomList; // list of rooms
	private ArrayList<Character> characters; // list of characters
	private File roomInfo; // input file of rooms
	private File mapInfo; // input file of map
	private boolean isDragonTurn; // boolean representing whose turn it is
	private int gameState; // int representing result of game (0 means in play, 1 means player won, 2 means dragon won
	@Override
	/**
	 * Creates the size for the PImage output
	 */
	public void settings() {
		size(800,600);
	}
	@Override
	/**
	 * Sets up the game. Fixes window settings, sets portal and treasure images, loads input files, and initializes variables
	 */
	public void setup() {
		this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
		this.imageMode(PApplet.CORNER); //Images are drawn using the x,y-coordinate
		//as the top-left corner
		this.rectMode(PApplet.CORNERS); //When drawing rectangles interprets args
		//as top-left corner and bottom-right corner respectively
		this.focused = true; // window will be active upon running program
		this.textAlign(CENTER); // sets the text alignment to center
		this.textSize(20); //sets the font size for the text
		this.roomList = new ArrayList <Room>();
		this.characters = new ArrayList <Character>();
		Room.setProcessing(this);	
		PImage tester2 = loadImage("images" + File.separator + "treasure.jpg");
		PImage tester = loadImage("images" + File.separator + "portal.png");
		PortalRoom.setPortalImage(tester);
		TreasureRoom.setTreasureBackground(tester2);
		roomInfo = new File("roominfo.txt");
		mapInfo = new File("map.txt");
		loadRoomInfo();
		loadMap();
		loadCharacters();
		isDragonTurn = false;
		gameState = 0;
	}
	/**
	 * Draws the windows, outputs the warnings, prints gamestate, and manages dragon's turn
	 */
	public void draw() {
		int playerIndex = -1;
		int dragonIndex = -1;
		// Outputs warnings if portal or treasure is adjacent
		for(Character player : characters) {
			if(player instanceof Player) {
				player.getCurrentRoom().draw();
				for(Room room : player.getAdjacentRooms()) {
					if(room instanceof PortalRoom) {
						System.out.println(PortalRoom.getPortalWarning());
					}
					if(room instanceof TreasureRoom) {
						System.out.println(TreasureRoom.getTreasureWarning());
					}
				}
			}
		}
		//gets player index
		for(int i = 0; i < characters.size(); i++) {
			if(characters.get(i) instanceof Player) {
				playerIndex = i;
			}
		}
		//outputs dragon warning if dragon is adjacent to player
		for(Character dragon : characters) {
			if(dragon instanceof Dragon) {
				Room dragonRoom = dragon.getCurrentRoom();
				if(characters.get(playerIndex).getCurrentRoom().isAdjacent(dragonRoom)) {
					System.out.println(Dragon.getDragonWarning());
				}
			}
		}
		// Outputs key obtained message if player obtains key
		for(Character keyHolder : characters) {
			if(keyHolder instanceof Character && keyHolder.getLabel().equals("KEYHOLDER")) {
				if(characters.get(playerIndex).getCurrentRoom().equals(keyHolder.getCurrentRoom())){
					System.out.println("KEY OBTAINED");
					((Player) characters.get(playerIndex)).obtainKey();
				}
			}
		} 
		//teleports character if character is in a portal room
		if(characters.get(playerIndex).getCurrentRoom() instanceof PortalRoom) {
			characters.get(playerIndex).setCurrentRoom(((PortalRoom) characters.get(playerIndex).getCurrentRoom()).getTeleportLocation());
		}
		//when it is dragon's turn, moves dragon by randomly picking adjacent non portal rooms
		if(isDragonTurn == true) {
			for(Character dragon : characters) {
				if(dragon instanceof Dragon) {
					Room hold = ((Dragon) dragon).pickRoom();
					boolean holding = ((Dragon) dragon).canMoveTo(hold);
					while(holding == false) {
						hold = ((Dragon) dragon).pickRoom();
						holding = ((Dragon) dragon).canMoveTo(hold);
					}
					((Dragon) dragon).changeRoom(hold);
					isDragonTurn = false;
				}
			}
		}
		//gets Dragon index in characters arraylist
		for(int i = 0; i < characters.size(); i++) {
			if(characters.get(i) instanceof Dragon) {
				dragonIndex = i;
			}
		}
		//outputs winning statement if player wins
		if(characters.get(playerIndex).getCurrentRoom() instanceof TreasureRoom) {
			if(((TreasureRoom) characters.get(playerIndex).getCurrentRoom()).playerCanGrabTreasure((Player) characters.get(playerIndex)) == true) {
				gameState = 1;
				System.out.println("You won!");
			}
		} 
		//outputs losing statement if player loses
		if(characters.get(playerIndex).getCurrentRoom().equals(characters.get(dragonIndex).getCurrentRoom())) {
			gameState = 2;
			System.out.println("You lost");
		}
		
	}
	@Override
	//takes keyinput and moves player with input if input is valid and game isn't over.
	public void keyPressed() {
		int key_int = key - '0';
		if(gameState != 1 && gameState != 2) {
			boolean validKey = false;
			for(int i = 0; i < roomList.size(); i++) {
				if(roomList.get(i).getID() == key_int) {
					for(Character player : characters) {
						if(player instanceof Player) {
							boolean success = ((Player) player).changeRoom(roomList.get(i));
							if (success) {
								isDragonTurn = true;
								validKey = true;
							}
						}
					}
				}
			}
			if(validKey == false) {
				System.out.println("You need to pick a valid room");
			}
		}
	}

	//main method, runs dragon treasure game
	public static void main(String[] args) {
		PApplet.main("DragonTreasureGame");
		
	}
	// loads characters into characters arraylist
	private void loadCharacters() {
		System.out.println("Adding characters...");
		characters.add(new Character(getRoomByID(5),"KEYHOLDER"));
		characters.add(new Player(getRoomByID(1)));
		characters.add(new Dragon(getRoomByID(9)));
		}
	/** Loads in room info using the file stored in roomInfo
	   *  @author Michelle 
	   */
	  private void loadRoomInfo() {
	    System.out.println("Loading rooms...");
	    Scanner fileReader = null;
	    try {
	      
	      //scanner to read from file
	      fileReader= new Scanner(roomInfo);
	      
	      //read line by line until none left
	      while(fileReader.hasNext()) {
	        String nextLine = fileReader.nextLine();
	        
	        //parse info and create new room 
	        String[] parts = nextLine.split(" \\| ");
	        int ID = Integer.parseInt(parts[1].trim()); //get the room id
	        String imageName = null;
	        String description = null;
	        PImage image = null;
	        Room newRoom = null;
	        
	        if(parts.length >= 3) {
	          imageName = parts[2].trim();
	          image = this.loadImage("images" + File.separator + imageName);
	         
	        }
	        
	        if(parts.length == 4) {
	          description = parts[3].trim(); //get the room description
	        }
	   
	        switch(parts[0].trim()) {
	          case "S":
	            newRoom = new StartRoom(ID, image);
	            break;
	          case "R":
	            newRoom = new Room(ID, description, image);
	            break;
	          case "P":
	            newRoom = new PortalRoom(ID, description, image);
	            break;
	          case "T":
	            newRoom = new TreasureRoom(ID);
	            break;
	          default:
	            break;
	        }  
	        
	        if(newRoom != null) {
	          roomList.add(newRoom);
	        }
	      }
	    }catch(IOException e) { //handle checked exception
	      e.printStackTrace();
	    }finally {
	      if(fileReader != null)
	        fileReader.close(); //close scanner regardless of what happened for security reasons :)
	    }
	  }
	  
	  /** Loads in room connections using the file stored in mapInfo
	   *  @author Michelle 
	   */
	  private void loadMap() {
	    System.out.println("Loading map...");
	    Scanner fileReader = null;
	    try {
	          //scanner to read from file
	          fileReader= new Scanner(mapInfo);
	          
	        //read line by line until none left
	          while(fileReader.hasNext()) {
	            
	            //parse info
	            String nextLine = fileReader.nextLine();
	            String parts[] = nextLine.split(" ");
	            int id = Integer.parseInt(parts[0]);
	            
	            Room toEdit = getRoomByID(id); //get the room we need to update info for adjacent rooms
	            
	            //add all the rooms to the adj room list of toEdit
	            for(int i=1; i<parts.length; i++) {
	              Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
	              toEdit.addToAdjacentRooms(toAdjAdd);
	            }
	          }
	        }catch(IOException e) { //handle checked exception
	          e.printStackTrace();
	        }finally {    //close scanner regardless of what happened for security reasons :)
	          if(fileReader != null)
	            fileReader.close();
	        }
	  }
	  
	  /**
	   * Get the room objected associated with the given ID.
	   * @param id the ID of the room to retrieve
	   * @return the Room that corresponds to that id
	   * @author Michelle
	   */
	  private Room getRoomByID(int id){
	    int indexToEdit = roomList.indexOf(new Room(id,"dummy", null));
	    Room toEdit = roomList.get(indexToEdit); 
	    return toEdit;
	  }
}
