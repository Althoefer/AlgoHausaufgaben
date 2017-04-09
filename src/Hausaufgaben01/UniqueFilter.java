import java.util.Arrays;

/**
 * Created by Tom on 04.04.2017.
 */
public class UniqueFilter implements Filter {
	/**
	 * Returns the given list with all numbers only once left in it
	 * @param list is the list, that has to be filtered
	 * @return the filtered list
	 */
	@Override
	public int[] filter(int[] list) {
		return Arrays.stream(list).distinct().toArray();
	}
}
