/*****************************************************************************
 * Author: Carlos Martinez
 * Date: October 3, 2018
 * Assignment: Object Oriented File System, for proofpoint
 ****************************************************************************/

package memory;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to represent memory. The assignment is to design and
 * implement an in-memory file system. This file-system consists of 4 types of
 * entities: Drives, Folders, Text files, ZipFiles. These entities, very much
 * like their “real” file-system counterparts, obey the following relations. • A
 * folder, a drive or a zip file, may contain zero to many other folders, or
 * files (text or zip). • A text file does not contain any other entity. • A
 * drive is not contained in any other entity. • Any non-drive entity must be
 * contained in another entity. If entity A contains entity B then we say that A
 * is the parent of B. The system should be capable of supporting file-system
 * like operations • Create – Creates a new entity. Arguments: Type, Name, Path
 * of parent. • Delete – Deletes an existing entity (and all the entities it
 * contains). Arguments: Path • Move – Changing the parent of an entity.
 * Arguments: Source Path, Destination Path. • WriteToFile – Changes the content
 * of a text file. Arguments: Path, new Content.
 * 
 * @author Carlos Martinez
 */
public class Memory {

	/**
	 * This holds the entities of the memory home drive
	 */
	Map<String, Entity> homeDrive;

	/**
	 * This creates a simulated form of memory with a home drive in it as default
	 */
	public Memory() {
		this.homeDrive = new HashMap<String, Entity>();
		this.homeDrive.put("Home", new Drive("Home", "Home", 0));
	}

	/**
	 * This adds an object to memory
	 * 
	 * @param type The type of the entity in the form of a char, 'D' = drive, 'F' =
	 *             folder, 'Z' = zipFile, 'T' = text file.
	 * @param name name of the object
	 * @param path path to where create an object
	 */
	public void create(char type, String name, String path) {
		if (name == null || path == null || name.length() == 0 
				|| path.length() == 0  || !name.matches("^[a-zA-Z0-9]*$")) {
			System.out.println("Invalid Arguements");
			return;
		}

		String[] pathObjects = path.split("\\\\");

		// Add a drive
		if (type == 'D') {
			// Add a new drive at base level
			if (pathObjects.length == 1) {
				if (homeDrive.containsKey(pathObjects[0])) {
					System.out.println("The drive you are trying to create already exist!");
				} else {
					if (name != pathObjects[0]) {
						System.out.println("Invalid entity name!");
						return;
					}
					homeDrive.put(name, new Drive(name, path, 0));
					System.out.println("The drive has been added.");
				}
			}

			// Adding drive deeper in memory file
			else {
				if (!homeDrive.containsKey(pathObjects[0])) {
					System.out.println("The file path does not seem to exist!");
					return;
				} else {
					if (!(homeDrive.get(pathObjects[0]) instanceof Drive)) {
						System.out.println("You can only add a drive to another drive");
						return;
					}

					((Drive) homeDrive.get(pathObjects[0])).entityAdd(type, name, pathObjects, 1);
				}
			}
		}

		// Add folder, ZipFile, TextFile
		if (type == 'F' || type == 'Z' || type == 'T') {
			if (pathObjects.length <= 1) {
				System.out.println("Entity Needs a proper parent entity!");
				return;
			}
			// Adding entity deeper in memory
			else {
				if (!homeDrive.containsKey(pathObjects[0])) {
					System.out.println("The file path does not seem to exist!");
				} else {
					((Drive) homeDrive.get(pathObjects[0])).entityAdd(type, name, pathObjects, 1);
				}

			}
		}
	}

	/**
	 * This method deletes the given item from memory
	 * 
	 * @param path the path to where the object is to delete
	 */
	public void delete(String path) {
		if (path == null || path.length() == 0) {
			System.out.println("Invalid Argument");
			return;
		}

		// Remove from the base level
		String[] pathObjects = path.split("\\\\");
		if (pathObjects.length == 1) {
			if (homeDrive.containsKey(pathObjects[0])) {
				homeDrive.remove(pathObjects[0]);
				System.out.println("The entity has been removed!");
			} else {
				System.out.println("The entity that you are trying to remove does not seem to exist!");
			}
		}

		// This removes from deeper in memory
		else {
			if (homeDrive.containsKey(pathObjects[0])) {
				homeDrive.get(pathObjects[0]).delete(pathObjects, 1);
			}

			else {
				System.out.println("The path to the entity does not seem to exist");
			}

		}
	}

	/**
	 * This is a helper method to get the source in the move method, This method
	 * does not edit the size of the entity just goes out and finds the entity Also
	 * used to for the the get entity method.
	 * 
	 * @param path the path to the source
	 * @return the source, or null if it does not exist
	 */
	private Entity getSourceMoveHelper(String path) {
		String[] pathObjects = path.split("\\\\");
		if (pathObjects.length == 1) {
			if (homeDrive.containsKey(pathObjects[0])) {
				return homeDrive.get(pathObjects[0]);
			}

			else {
				return null;
			}
		}

		else {
			if (homeDrive.containsKey(pathObjects[0])) {
				return homeDrive.get(pathObjects[0]).getSourceMoveHelper(pathObjects, 1);
			}

			else {
				return null;
			}
		}
	}

	/**
	 * This method returns the entity at the given location
	 * 
	 * @param path the path of where the entity is at
	 * @return the entity at the given path
	 */
	public Entity getEntity(String path) {
		if (path == null || path.length() == 0) {
			System.out.println("Invalid Arguments");
			return null;
		}

		Entity source = getSourceMoveHelper(path);
		if (source == null) {
			System.out.println("Entity does not exist or wrong path");
			return null;
		}
		return new Entity(source.getName(), source.getPath(), source.getSize());
	}

	/**
	 * This is a helper method for the move method. This takes the entity that the
	 * get source helper method got and attempts to add it to the proper location.
	 * The method returns true if moving the entity in the desired destination was
	 * successful. If moving the entity failed it returns false.
	 * 
	 * @param path   the new location of the object
	 * @param entity the entity you are trying to move
	 * @return true if move was successful, false otherwise
	 */
	private boolean setDestination(String path, Entity entity) {
		String[] pathObjects = path.split("\\\\");
		
		//Move to base level, has to be a drive
		if (pathObjects.length == 1) {
			if (homeDrive.containsKey(pathObjects[0])) {
				return false;
			} else {

				if (!(entity instanceof Drive)) {
					return false;
				}

				entity.setPath(path);
				homeDrive.put(pathObjects[0], entity);
				return true;
			}
		}
		
		//Move deeper in memory
		else {
			if (homeDrive.containsKey(pathObjects[0])) {
				return homeDrive.get(pathObjects[0]).setDestination(pathObjects, 1, entity);
			} else {
				return false;
			}
		}

	}

	/**
	 * This method takes to paths, the first the source location and the second the
	 * destination location. The method attempts to move the entity from the source
	 * location to the desired destination. If the source does not exist, if any of
	 * the paths don't exist the method will fail to move the entity.
	 * 
	 * @param SourcePath      the location path where the entity exists
	 * @param DestinationPath location path where you wish to move the entity
	 */
	public void move(String SourcePath, String DestinationPath) {
		if (SourcePath == null || SourcePath.length() == 0 || DestinationPath == null
				|| DestinationPath.length() == 0) {
			System.out.println("Invalid Argument");
			return;
		}

		Entity source = getSourceMoveHelper(SourcePath);
		if (source == null) {
			System.out.println("Source does not exist, or wrong path");
			return;
		}

		if (setDestination(DestinationPath, source)) {
			System.out.print("Successfully moved\nOld ");
			this.delete(SourcePath);
			return;
		}

		System.out.println("Failed to move");

	}

	/**
	 * This writes to a text file
	 * 
	 * @param path       the path to the text file
	 * @param newContent the new content of the text file
	 */
	public void WriteToFile(String path, String newContent) {
		if(path == null || newContent == null || path.length() == 0) {
			System.out.println("Invalid Arguments");
			return;
		}
		
		String[] pathObjects = path.split("\\\\");
		if (pathObjects.length <= 1) {
			System.out.println("Can't write here");
			return;
		}

		else {
			if (homeDrive.containsKey(pathObjects[0])) {
				int size = homeDrive.get(pathObjects[0]).WriteToFile(pathObjects, newContent, 1);
				homeDrive.get(pathObjects[0]).setSize(homeDrive.get(pathObjects[0]).getSize() + size);

			}

			else {
				System.out.println("Path to entity does not seem to exist");
			}
		}
	}
}