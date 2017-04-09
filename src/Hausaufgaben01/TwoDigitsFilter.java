import java.util.Arrays;

/**
 * Created by Tom on 04.04.2017.
 * 
 * TwoDigitsFilter only lets two digit numbers through
 */
public class TwoDigitsFilter implements Filter {
	/**
	 * Filter out all non two digit numbers from the given list
	 * 
	 * @param list
	 *            is the list, that has to be filtered
	 * @return the filtered list with only two digit numbers
	 */
	@Override
	public int[] filter(int[] list) {
		return Arrays.stream(list).filter(i -> (Math.abs(i) > 9) && (Math.abs(i) < 100)).toArray();
	}
}
