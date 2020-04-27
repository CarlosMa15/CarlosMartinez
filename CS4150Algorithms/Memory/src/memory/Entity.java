/*****************************************************************************
 * Author: Carlos Martinez
 * Date: October 3, 2018
 * Assignment: Object Oriented File System, for proofpoint
 ****************************************************************************/

package memory;

/**
 * This class is used to represent an Entity.
 * 
 * @author Carlos Martinez
 */
public class Entity {

	/**
	 * This is the alphanumeric name of the entity
	 */
	private String name;

	/**
	 * This is the path of the file starting from the drive
	 */
	private String path;

	/**
	 * This is the size of the entity
	 */
	private int size;

	/**
	 * This creates an Entity object
	 * 
	 * @param name - the name of the entity
	 * @param path - the path to the entity from the drive
	 * @param size - the size of the entity
	 */
	public Entity(String name, String path, int size) {
		super();
		this.name = name;
		this.path = path;
		this.size = size;
	}

	/**
	 * This method returns the name of the entity
	 * 
	 * @return the name of the entity
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method returns the path of the entity
	 * 
	 * @return the path of the entity
	 */
	public String getPath() {
		return path;
	}

	/**
	 * This method sets the path of the entity
	 * 
	 * @param path new path of the entity
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * This returns the size of the entity
	 * 
	 * @return the size of the entity
	 */
	public int getSize() {
		return size;
	}

	/**
	 * This set the size of the entity
	 * 
	 * @param size the new size of the entity
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**********************************************************************************
	 * The methods below are used so the collection of entities can call the same
	 * method with out knowing what type of entity it is. The methods below are
	 * supposed to be override the the every entity to match the desired
	 * functionality of each type of entity.
	 *********************************************************************************/

	/**
	 * This method is used by the entity to add to the object
	 * 
	 * @param type  is a char, 'D' = Drive, 'T' = TextFile, 'Z' = ZipFile, 'F' =
	 *              Folder.
	 * @param name  the name of the entity
	 * @param path  the path from the drive to entity
	 * @param index the index of the object you we are in the path
	 * @return true if added successfully, false otherwise
	 */
	public boolean entityAdd(char type, String name, String[] path, int index) {
		System.out.println("You are not supose to be here!");
		return false;
	}

	/**
	 * This deletes the element from memory
	 * 
	 * @param path the path to the file
	 * @return the size of the file you just deleted
	 */
	public int delete(String[] path, int index) {
		System.out.println("You are not supose to be here!");
		return 0;
	}

	/**
	 * This checks if the source exist, if so retrieve it, else return null. This is
	 * a helper method for the move method
	 * 
	 * @param path  the path to the source
	 * @param index the index of the object of where you are at
	 * @return the object of the source if it exist, null otherwise
	 */
	public Entity getSourceMoveHelper(String[] path, int index) {
		System.out.println("You are not supose to be here!");
		return null;
	}

	/**
	 * This checks if the place where the entity if being moved to exists if the
	 * location exist the object is placed there if you can, the method returns true
	 * if the entity was successfully placed there, otherwise you return false. This
	 * is a helper method for the move method.
	 * 
	 * @param path   the path to where to place entity
	 * @param index  the index of where we are at in the path
	 * @param entity the entity that we are moving.
	 * @return true if the entity was successfully moved
	 */
	public boolean setDestination(String[] path, int index, Entity entity) {
		System.out.println("You are not supose to be here!");
		return false;
	}

	/**
	 * This method updates the names of the children of the entity which paths are
	 * not updated yet
	 */
	public void update() {
		// DO NOTHING
	}

	/**
	 * This write to a TextFile
	 * 
	 * @param path    the path to the entity
	 * @param content the content of the text file
	 * @param index   the index of the object that we are in the path
	 */
	public int WriteToFile(String[] path, String content, int index) {
		System.out.println("You are not supose to be here!");
		return 0;
	}
}