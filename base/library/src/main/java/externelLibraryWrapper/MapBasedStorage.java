package externelLibraryWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implement IStorage methods.
 * The main use of this class is for Mocking (testing).
 * 
 * @author Aviad
 *
 */
public class MapBasedStorage implements IStorage {

	private Map<Integer, String> mapStorage;
	
	public MapBasedStorage() {
		this.mapStorage = new HashMap<Integer, String>();
	}
	
	@Override
	public void addItemToStorage(String item) {		
		mapStorage.put(mapStorage.size(), item);
	}

	@Override
	public String getItemByIndex(int index) throws InterruptedException {
		if (mapStorage.containsKey(index)) {
			String result = mapStorage.get(index);
			
			Thread.sleep(result.length());
			
			return result;
		} else {
			return null;
		}
	}

	@Override
	public int getNumberOfItems() throws InterruptedException {
		Thread.sleep(100);
		
		return mapStorage.size();
	}
}
