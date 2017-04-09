import java.util.Arrays;

/**
 * Created by Tom on 04.04.2017.
 * 
 * UniqueFilter only lets one occurrence of each number through
 */
public class UniqueFilter implements Filter {
	/**
	 * Filter out all duplicates of a number from the given list, so that each
	 * number is contained only one
	 * 
	 * @param list
	 *            is the list, that has to be filtered
	 * @return the filtered list with only one occurrence of each number
	 */
	@Override
	public int[] filter(int[] list) {
		return Arrays.stream(list).distinct().toArray();
	}
}
