import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by Tom on 04.04.2017.
 */
public class PrimeFilter implements Filter {
	@Override
	public int[] filter(int[] list) {
		return Arrays.stream(list).filter(num -> !IntStream.rangeClosed(2, (int) Math.sqrt(num))
				.filter(i -> num % i == 0).findFirst().isPresent()).toArray();
	}
}
