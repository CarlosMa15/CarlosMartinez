/*****************************************************************************
 * Author: Carlos Martinez
 * Date: October 3, 2018
 * Assignment: Object Oriented File System, for proofpoint
 ****************************************************************************/

package memory;

import java.util.HashMap;
import java.util.Map;

/**
 * This class extends the entity class and it is used to represent a ZipFile in
 * memory.
 * 
 * @author Carlos Martinez
 *
 */
public class ZipFile extends Entity {

	/**
	 * This holds the entities of the zipfFile
	 */
	private Map<String, Entity> zipFile;

	/**
	 * This creates a zipFile object
	 * 
	 * @param name the name of the zipFile
	 * @param path the path to the zipFile from drive
	 * @param size the size of the ZipFile
	 */
	public ZipFile(String name, String path, int size) {
		super(name, path, size);
		this.zipFile = new HashMap<String, Entity>();
	}

	/************************************************************************************
	 * The Methods below are from the Entity class, and are implemented in the way
	 * that a ZipFile would react to such command. Way the method should do it
	 * stated in the Entity class
	 ***********************************************************************************/

	@Override
	public boolean entityAdd(char type, String name, String[] path, int index) {
		if (type == 'D') {
			System.out.println("Can only add Drive to another Drive!");
			return false;
		}

		// Add object to the drive
		if (path.length - 1 == index) {
			if (zipFile.containsKey(path[index])) {
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
					zipFile.put(name, new Folder(name, entitiesPath, 0));
					System.out.println("Folder was Added!");
				}
				if (type == 'T') {
					zipFile.put(name, new TextFile(name, entitiesPath, 0));
					System.out.println("TextFile was Added!");
				}

				if (type == 'Z') {
					zipFile.put(name, new ZipFile(name, entitiesPath, 0));
					System.out.println("ZipFile was Added!");
				}
				return true;
			}
		}

		// Further into memory
		else {
			if (this.zipFile.containsKey(path[index])) {
				return this.zipFile.get(path[index]).entityAdd(type, name, path, index + 1);
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
			if (zipFile.containsKey(path[index])) {
				int size = this.zipFile.get(path[index]).getSize();
				this.setSize(this.getSize() - size);// - 1
				zipFile.remove(path[index]);
				System.out.println("Entity removed");
				return size;// +1
			}

			else {
				System.out.println("The object does not not seem to exist");
				return 0;
			}
		}

		// Remove further in memory
		else {
			if (this.zipFile.containsKey(path[index])) {
				int size = this.zipFile.get(path[index]).delete(path, index + 1);
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
			if (zipFile.containsKey(path[index])) {
				return zipFile.get(path[index]);
			}

			else {
				return null;
			}
		}

		else {
			if (zipFile.containsKey(path[index])) {
				return zipFile.get(path[index]).getSourceMoveHelper(path, index + 1);
			} else {
				return null;
			}
		}
	}

	@Override
	public void update() {
		for (Entity el : zipFile.values()) {
			el.setPath(this.getPath() + "\\" + el.getName());
			el.update();
		}
	}

	@Override
	public boolean setDestination(String[] path, int index, Entity entity) {
		if (index == path.length - 1) {
			if (zipFile.containsKey(path[index])) {
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
				zipFile.put(path[index], entity);
				zipFile.get(path[index]).update();
				this.setSize(this.getSize() + entity.getSize());// + 1
				return true;
			}
		}

		else {
			if (zipFile.containsKey(path[index])) {
				if (zipFile.get(path[index]).setDestination(path, index + 1, entity)) {
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
			if (zipFile.containsKey(path[index])) {
				if (zipFile.get(path[index]) instanceof TextFile) {
					int start = zipFile.get(path[index]).getSize();
					((TextFile) zipFile.get(path[index])).setSize(zipFile.get(path[index]).getSize()
							- ((TextFile) zipFile.get(path[index])).getContent().length());
					((TextFile) zipFile.get(path[index])).setContent(content);
					zipFile.get(path[index]).setSize(zipFile.get(path[index]).getSize() + content.length());
					int end = zipFile.get(path[index]).getSize();
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
			if (zipFile.containsKey(path[index])) {
				int size = zipFile.get(path[index]).WriteToFile(path, content, index + 1);
				if (zipFile.get(path[index]) instanceof ZipFile) {
					size = size / 2;
				}
				zipFile.get(path[index]).setSize(zipFile.get(path[index]).getSize() + size);
				return size;

			} else {
				System.out.println("The path to object does not seem to exist");
				return 0;
			}
		}
	}
}