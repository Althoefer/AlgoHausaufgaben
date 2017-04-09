import java.util.Arrays;

/**
 * Created by Tom on 04.04.2017.
 */
public class TwoDigitsFilter implements Filter {
	/**
	 * Returns the given list with only the numbers with two digits left in it
	 * @param list is the list, that has to be filtered
	 * @return the filtered list
	 */
	@Override
	public int[] filter(int[] list) {
		return Arrays.stream(list).filter(i -> (Math.abs(i) > 9) && (Math.abs(i) < 100)).toArray();
	}
}
