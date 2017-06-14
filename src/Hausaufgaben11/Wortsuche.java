import java.util.HashMap;

public class Wortsuche {
	private String text;

	public Wortsuche(String text) {
		this.text = text;
	}

	private HashMap<Character, Integer> getLastTabelle(String pattern) {
		HashMap<Character, Integer> lastTable = new HashMap<>();
		int patternLength = pattern.length();
		for (int i = 0; i < patternLength; ++i) {
			// later occurrences replace earlier ones
			lastTable.put(pattern.charAt(i), i);
		}
		return lastTable;
	}

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

	public int findFirst(String pattern) {
		HashMap<Character, Integer> lastTable = getLastTabelle(pattern);
		int shift;
		for (int i = 0; i < this.text.length() - pattern.length() + 1; i += shift) {
			if (fits(pattern, i)) {
				return i;
			}
			Integer lastTableEntry = lastTable.get(this.text.charAt(i + pattern.length()));
			if(lastTableEntry == null) {
				lastTableEntry = -1;
			}
			shift = pattern.length() - lastTableEntry;
		}
		return -1;
	}
}
