import java.util.HashMap;

/**
 * Class Wortsuche searches a given text for a user defined pattern by using the
 * Boyer-Moore-Sunday algorithm
 * 
 * @author C.Wassermann
 *
 */
public class Wortsuche {
	// user defined text to search through
	private String text;

	/**
	 * Constructor Wortsuche creates a new Object with the given text which can
	 * be later searched through with the {@link #findFirst(String) findFirst}
	 * method
	 * 
	 * @param text
	 *            which is saved for later searches
	 */
	public Wortsuche(String text) {
		this.text = text;
	}

	// construct the lastTable of the BMS algorithm for the given pattern
	private HashMap<Character, Integer> getLastTabelle(String pattern) {
		HashMap<Character, Integer> lastTable = new HashMap<>();
		for (int i = 0; i < pattern.length(); ++i) {
			// later occurrences replace earlier ones
			lastTable.put(pattern.charAt(i), i);
		}
		return lastTable;
	}

	// check if pattern matches text starting at position pos
	private boolean fits(String pattern, int pos) {
		if (pos + pattern.length() > this.text.length()) {
			return false;
		}
		for (int i = 0; i < pattern.length(); ++i) {
			if (pattern.charAt(i) != this.text.charAt(i + pos)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Find the first occurrence of the pattern in the text, which was provided
	 * in the constructor
	 * 
	 * @param pattern
	 *            pattern to search for
	 * @return position of the first occurrence in the text or -1 if pattern was
	 *         not found
	 */
	public int findFirst(String pattern) {
		HashMap<Character, Integer> lastTable = getLastTabelle(pattern);
		int shift;
		for (int i = 0; i < this.text.length() - pattern.length() + 1; i += shift) {
			if (fits(pattern, i)) {
				return i;
			}
			// calculate shift for the BMS algorithm
			Integer lastTableEntry = lastTable.get(this.text.charAt(i + pattern.length()));
			if (lastTableEntry == null) {
				lastTableEntry = -1;
			}
			shift = pattern.length() - lastTableEntry;
		}
		return -1;
	}
}
