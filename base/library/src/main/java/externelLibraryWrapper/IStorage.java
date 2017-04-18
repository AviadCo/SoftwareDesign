package externelLibraryWrapper;

/**
 * This is interface represent Storage for students.
 * 
 * @author Aviad
 *
 */

public interface IStorage {

	/**
	 * 
	 * @param item - a string which represent the item to be add to database.
	 */
	public void addItemToStorage(String item);
	
	/**
	 * 
	 * @param index - the index of the wanted item
	 * @return - returns string which represents the wanted item.
	 * @throws InterruptedException - throws exception on failure
	 */
	public String getItemByIndex(int index) throws InterruptedException;
	
	/**
	 * 
	 * @return - how many items in the data storage
	 * @throws InterruptedException - throws exception on failure
	 */
	public int getNumberOfItems() throws InterruptedException;
}
