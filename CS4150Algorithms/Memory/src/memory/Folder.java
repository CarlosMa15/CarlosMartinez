/*****************************************************************************
 * Author: Carlos Martinez
 * Date: October 3, 2018
 * Assignment: Object Oriented File System, for proofpoint
 ****************************************************************************/

package memory;

import java.util.HashMap;
import java.util.Map;

/**
 * This class extends the Entity class and is used to represent a Folder type
 * Entity in Memory.
 * 
 * @author Carlos Martinez
 */
public class Folder extends Entity {

	/**
	 * This holds the entities of the folder
	 */
	private Map<String, Entity> folder;

	/**
	 * This creates a folder
	 * 
	 * @param name name of folder
	 * @param path path to folder from drive
	 * @param size size of the folder
	 */
	public Folder(String name, String path, int size) {
		super(name, path, size);
		this.folder = new HashMap<String, Entity>();
	}

	/*************************************************************************************************
	 * The Methods below are from the Entity class, and are implemented in the way a
	 * Folder would act
	 ************************************************************************************************/

	@Override
	public boolean entityAdd(char type, String name, String[] path, int index) {
		if (type == 'D') {
			System.out.println("Can only add Drive to another Drive!");
			return false;
		}

		// Add object to the drive
		if (path.length - 1 == index) {
			if (folder.containsKey(path[index])) {
				System.out.println("The object you are trying to add already exist!");
				return false;
			} else {

				if (!path[index].equals(name)) {
					System.out.println("name and path don't match!");
					return false;
				}

				String entitiesPath = "";
				for (String el : path) {
					entitiesPath += el + "\\";
				}
				entitiesPath = entitiesPath.substring(0, entitiesPath.length() - 1);

				if (type == 'F') {
					folder.put(name, new Folder(name, entitiesPath, 0));
					System.out.println("Folder was Added!");
				}
				if (type == 'T') {
					folder.put(name, new TextFile(name, entitiesPath, 0));
					System.out.println("TextFile was Added!");
				}

				if (type == 'Z') {
					folder.put(name, new ZipFile(name, entitiesPath, 0));
					System.out.println("ZipFile was Added!");
				}
				return true;
			}
		}

		// Further into memory
		else {
			if (this.folder.containsKey(path[index])) {
				return this.folder.get(path[index]).entityAdd(type, name, path, index + 1);
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
			if (folder.containsKey(path[index])) {
				int size = this.folder.get(path[index]).getSize();
				this.setSize(this.getSize() - size ); // - 1
				folder.remove(path[index]);
				System.out.println("Entity removed");
				return size;// + 1
			}

			else {
				System.out.println("The object does not not seem to exist");
				return 0;
			}
		}

		// Remove further in memory
		else {
			if (this.folder.containsKey(path[index])) {
				int size = this.folder.get(path[index]).delete(path, index + 1);
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
			if (folder.containsKey(path[index])) {
				return folder.get(path[index]);
			}

			else {
				return null;
			}
		}

		else {
			if (folder.containsKey(path[index])) {
				return folder.get(path[index]).getSourceMoveHelper(path, index + 1);
			} else {
				return null;
			}
		}
	}
	
	@Override
	public void update() {
		for (Entity el : folder.values()) {
			el.setPath(this.getPath() + "\\" + el.getName());
			el.update();
		}
	}

	@Override
	public boolean setDestination(String[] path, int index, Entity entity) {
		if (index == path.length - 1) {
			if (folder.containsKey(path[index])) {
				return false;
			} else {

				if (entity instanceof Drive) {
					return false;
				}
				String entitiesPath = "";
				for (String el : path) {
					entitiesPath += el + "\\";
				}
				entitiesPath = entitiesPath.substring(0, entitiesPath.length() - 1);
				entity.setPath(entitiesPath);
				folder.put(path[index], entity);
				folder.get(path[index]).update();
				this.setSize(this.getSize() + entity.getSize());// + 1
				return true;
			}
		} else {
			if (folder.containsKey(path[index])) {
				if (folder.get(path[index]).setDestination(path, index + 1, entity)) {
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
			if (folder.containsKey(path[index])) {
				if (folder.get(path[index]) instanceof TextFile) {
					int start = folder.get(path[index]).getSize();
					((TextFile) folder.get(path[index])).setSize(folder.get(path[index]).getSize()
							- ((TextFile) folder.get(path[index])).getContent().length());
					((TextFile) folder.get(path[index])).setContent(content);
					folder.get(path[index]).setSize(folder.get(path[index]).getSize() + content.length());
					int end = folder.get(path[index]).getSize();
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
			if (folder.containsKey(path[index])) {
				int size = folder.get(path[index]).WriteToFile(path, content, index + 1);
				if(folder.get(path[index]) instanceof ZipFile) {
					size = size / 2;
				}
				folder.get(path[index]).setSize(folder.get(path[index]).getSize() + size);
				return size;
			} else {
				System.out.println("The path to object does not seem to exist");
				return 0;
			}
		}
	}
}