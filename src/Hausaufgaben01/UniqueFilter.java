import java.util.Arrays;

/**
 * Created by Tom on 04.04.2017.
 */
public class UniqueFilter implements Filter {
	@Override
	public int[] filter(int[] list) {
		return Arrays.stream(list).distinct().toArray();
	}
}
