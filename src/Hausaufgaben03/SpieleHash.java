import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class SpieleHash stores game titles by their hashes (double hashing)
 * 
 * Created by Manuel on 18.04.2017.
 */
public class SpieleHash {

	// size of the HashTable
	private int size = 53;
	// the HashTable implemented as an array
	private String[] table = new String[size];

	// returns the index at which the game string should be stored (first hash
	// function)
	private int hashIndex(String spiel) {
		return Math.abs(spiel.hashCode()) % 100;
	}

	// returns the increment which is used to find alternate storing positions
	// if the initial one is occupied (second hash function)
	private int hashIncrement(String spiel) {
		return (Math.abs(spiel.hashCode() / 1000) % 100) + 1;
	}

	/**
	 * Adds a new game string to the HashTable
	 * 
	 * @param spiel
	 *            string which is added to the HashTable
	 * @throws IllegalArgumentException
	 *             if the spiel string could not be added to the HashTable
	 */
	public void add(String spiel) throws IllegalArgumentException {
		int h0 = hashIndex(spiel);
		int i = hashIncrement(spiel);
		for (int j = 0; j < size; j++) {
			if (table[(h0 + i * j) % size] == null) {
				table[(h0 + i * j) % size] = spiel;
				return;
			}
		}
		throw new IllegalArgumentException();
	}

	/**
	 * Checks if the given spiel string is contained in the HashTable
	 * 
	 * @param spiel
	 *            string which is checked
	 * @return whether spiel string is contained in the HashTable
	 */
	public boolean contains(String spiel) {
		int h0 = hashIndex(spiel);
		int i = hashIncrement(spiel);
		for (int j = 0; j < size; j++) {
			if (table[(h0 + i * j) % size] == null) {
				return false;
			}
			if (table[(h0 + i * j) % size].equals(spiel)) {
				return true;
			}

		}
		return false;
	}

	// Test SpieleHash
	public static void main(String[] args) {
		SpieleHash spieleHash = new SpieleHash();
		File f = new File("games50.txt");
		try (Scanner sc = new Scanner(f)) {
			while (sc.hasNextLine()) {
				spieleHash.add(sc.nextLine().split("\t")[1].trim());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(spieleHash.contains("FIFA 16"));
		System.out.println(spieleHash.contains("Star Wars: Battlefront"));
		System.out.println(spieleHash.contains("WOW"));
	}
}
