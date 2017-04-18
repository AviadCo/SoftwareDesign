package externelLibraryWrapper;

import il.ac.technion.cs.sd.grades.ext.LineStorage;

/**
 * This class implements IStorage API using LineStorage API.
 * 
 * @author Aviad
 *
 */
public class BasicStorage implements IStorage {

	@Override
	public void addItemToStorage(String item) {
		LineStorage.appendLine(item);
	}

	@Override
	public String getItemByIndex(int index) throws InterruptedException {
		return LineStorage.read(index);
	}

	@Override
	public int getNumberOfItems() throws InterruptedException {
		return LineStorage.numberOfLines();
	}
}
