package accountpackage;

import java.util.Arrays;

public class Hash {
	final static int SIZE = 25;
	String[] recordId = new String[SIZE];
	int placed;

	public Hash() {
		Arrays.fill(recordId, "X");
	}

	public int getH(String id) {
		int i = -1;
		hashFunction(id, recordId);
		i = placed;
		return i;
	}

	public void hashFunction(String stringForArray, String[] recordId) {

		int arrayIndex = Integer.parseInt(stringForArray) % 25;

		while (recordId[arrayIndex] != "X") {
			++arrayIndex;
			System.out.println("Slot full, next available slot is : "
					+ arrayIndex);
			arrayIndex %= SIZE;
		}
		System.out.println(stringForArray + " stored at " + arrayIndex);
		placed = arrayIndex;
		recordId[arrayIndex] = stringForArray;
		System.out.println("hashed to slot " + arrayIndex);
	}

	public String findKey(String key) {

		int hashIndex = Integer.parseInt(key) % 25;

		while (recordId[hashIndex] != "X") {
			if (recordId[hashIndex] == key) {
				System.out.println(key + " found at " + hashIndex);
				return recordId[hashIndex];
			}
			++hashIndex;

			hashIndex %= SIZE;
		}
		return null;
	}

}// end hash function