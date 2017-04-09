import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by Tom on 04.04.2017.
 * 
 * PrimeFilter only lets prime numbers through
 */
public class PrimeFilter implements Filter {
	/**
	 * Filter out all non prime numbers from the given list
	 * 
	 * @param list
	 *            is the list, that has to be filtered
	 * @return the filtered list with only prime numbers
	 */
	@Override
	public int[] filter(int[] list) {
		return Arrays.stream(list).filter(num -> !IntStream.rangeClosed(2, (int) Math.sqrt(num))
				.filter(i -> num % i == 0).findFirst().isPresent()).toArray();
	}
}
