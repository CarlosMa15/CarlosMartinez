/*****************************************************************************
 * Author: Carlos Martinez
 * Date: October 3, 2018
 * Assignment: Object Oriented File System, for proofpoint
 ****************************************************************************/

package memory;

/**
 * This class extends Entity and is used to represent a TextFile in Memory.
 * 
 * @author Carlos Martinez
 */
public class TextFile extends Entity {

	/**
	 * This holds the content of the text file
	 */
	private String content;

	/**
	 * This creates an object of a text file The content of a new text file is empty
	 * 
	 * @param name the name of the text file
	 * @param path the path to the text file from the drive
	 * @param size the size of the text file
	 */
	public TextFile(String name, String path, int size) {
		super(name, path, size);
		this.content = "";
	}

	/**
	 * This sets the content of the text file This will replace the old content
	 * 
	 * @param content the new content of the text file
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * This returns the content of the file
	 * 
	 * @return the content of the TextFile
	 */
	public String getContent() {
		return this.content;
	}

	/****************************************************************************************
	 * The methods Below are are override methods from the Entity class. Because a
	 * Text File does not hold any other entities, most of the methods below are
	 * just letting memory know that you can't use the text file as container
	 * Entity. What the method does is stated in the Entity Class, the
	 * implementation is based off of what a Text File is and how it would respond
	 * to such requests
	 ***************************************************************************************/

	@Override
	public boolean entityAdd(char type, String name, String[] path, int index) {
		System.out.println("You can not add entities to a Text File");
		return false;
	}

	@Override
	public int delete(String[] path, int index) {
		System.out.println("You can not remove item from a textfile, it holds nothing!");
		return 0;
	}

	@Override
	public Entity getSourceMoveHelper(String[] path, int index) {
		return null;
	}

	@Override
	public boolean setDestination(String[] path, int index, Entity entity) {
		return false;
	}

	@Override
	public int WriteToFile(String[] path, String content, int index) {
		System.out.println("Text file hold no enties");
		return 0;
	}
}