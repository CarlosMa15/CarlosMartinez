/*****************************************************************************
 * Author: Carlos Martinez
 * Date: October 3, 2018
 * Assignment: Object Oriented File System, for proofpoint
 ****************************************************************************/

package memory;

import java.util.HashMap;
import java.util.Map;

/**
 * This class extends the Entity Class and it is used to represent a Folder
 * Entity
 * 
 * @author Carlos Martinez
 */
public class Drive extends Entity {

	/**
	 * This holds the entities of the drive
	 */
	private Map<String, Entity> drive;

	/**
	 * This creates a drive object
	 * 
	 * @param name the name of the drive
	 * @param path the path to the drive from the drive
	 * @param size the size of the drive
	 */
	public Drive(String name, String path, int size) {
		super(name, path, size);
		this.drive = new HashMap<String, Entity>();
	}

	/*************************************************************************************************
	 * The Methods below are from the Entity class, and are implemented in the way a
	 * Drive would act
	 ************************************************************************************************/

	@Override
	public boolean entityAdd(char type, String name, String[] path, int index) {
		// Add object to the drive
		if (path.length - 1 == index) {
			if (drive.containsKey(path[index])) {
				System.out.println("The object you are trying to add already exist!");
				return false;
			} else {

				if (!path[index].equals(name)) {
					System.out.println("Problem with name and path!");
					return false;
				}

				String entitiesPath = "";
				for (String el : path) {
					entitiesPath += el + "\\";
				}
				entitiesPath = entitiesPath.substring(0, entitiesPath.length() - 1);

				if (type == 'D') {
					drive.put(name, new Drive(name, entitiesPath, 0));
					System.out.println("Drive was Added!");
				}
				if (type == 'F') {
					drive.put(name, new Folder(name, entitiesPath, 0));
					System.out.println("Folder was Added!");
				}
				if (type == 'T') {
					drive.put(name, new TextFile(name, entitiesPath, 0));
					System.out.println("TextFile was Added!");
				}

				if (type == 'Z') {
					drive.put(name, new ZipFile(name, entitiesPath, 0));
					System.out.println("ZipFile was Added!");
				}
				return true;
			}
		}

		// Further into memory
		else {
			if (this.drive.containsKey(path[index])) {
				return this.drive.get(path[index]).entityAdd(type, name, path, index + 1);
			} else {
				System.out.println("The path to your object does not seem to exist!");
				return false;
			}
		}
	}

	@Override
	public int delete(String[] path, int index) {
		// Remove Entity
		if (path.length - 1 == index) {
			if (drive.containsKey(path[index])) {
				int size = this.drive.get(path[index]).getSize();
				this.setSize(this.getSize() - size); // - 1
				drive.remove(path[index]);
				System.out.println("Entity removed");
				return size; // + 1
			}

			else {
				System.out.println("The object does not not seem to exist");
				return 0;
			}
		}

		// Remove further in memory
		else {
			if (this.drive.containsKey(path[index])) {
				int size = this.drive.get(path[index]).delete(path, index + 1);
				this.setSize(this.getSize() - size);
				return size;
			}

			else {
				System.out.println("Path to file does not seem to exist");
				return 0;
			}
		}
	}

	@Override
	public Entity getSourceMoveHelper(String[] path, int index) {
		if (index == path.length - 1) {
			if (drive.containsKey(path[index])) {
				return drive.get(path[index]);
			}

			else {
				return null;
			}
		}

		else {
			if (drive.containsKey(path[index])) {
				return drive.get(path[index]).getSourceMoveHelper(path, index + 1);
			} else {
				return null;
			}
		}
	}

	@Override
	public void update() {
		for (Entity el : drive.values()) {
			el.setPath(this.getPath() + "\\" + el.getName());
			el.update();
		}
	}

	@Override
	public boolean setDestination(String[] path, int index, Entity entity) {
		if (index == path.length - 1) {
			if (drive.containsKey(path[index])) {
				return false;
			} else {
				String entitiesPath = "";
				for (String el : path) {
					entitiesPath += el + "\\";
				}
				entitiesPath = entitiesPath.substring(0, entitiesPath.length() - 1);
				entity.setPath(entitiesPath);
				drive.put(path[index], entity);
				drive.get(path[index]).update();
				this.setSize(this.getSize() + entity.getSize());// + 1
				return true;
			}
		}

		else {
			if (drive.containsKey(path[index])) {
				if (drive.get(path[index]).setDestination(path, index + 1, entity)) {
					this.setSize(this.getSize() + entity.getSize());// + 1
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	@Override
	public int WriteToFile(String[] path, String content, int index) {
		if (index == path.length - 1) {
			// Right to text file
			if (drive.containsKey(path[index])) {
				if (drive.get(path[index]) instanceof TextFile) {
					int start = drive.get(path[index]).getSize();
					((TextFile) drive.get(path[index])).setSize(drive.get(path[index]).getSize()
							- ((TextFile) drive.get(path[index])).getContent().length());
					((TextFile) drive.get(path[index])).setContent(content);
					drive.get(path[index]).setSize(drive.get(path[index]).getSize() + content.length());
					int end = drive.get(path[index]).getSize();
					System.out.println("Write to file successful!");
					return end - start;
				} else {
					System.out.println("This entity is not a textFile, Can't Write here!");
					return 0;
				}
			} else {
				System.out.println("The entity does not seem to exist");
				return 0;
			}
		}

		// Right further in memory
		else {
			if (drive.containsKey(path[index])) {
				int size = drive.get(path[index]).WriteToFile(path, content, index + 1);
				if (drive.get(path[index]) instanceof ZipFile) {
					size = size / 2;
				}
				drive.get(path[index]).setSize(drive.get(path[index]).getSize() + size);
				return size;
			} else {
				System.out.println("The path to object does not seem to exist");
				return 0;
			}
		}
	}
}