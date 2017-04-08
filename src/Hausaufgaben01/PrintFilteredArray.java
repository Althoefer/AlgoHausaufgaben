import java.util.Arrays;

/**
 * Created by Tom on 04.04.2017.
 */
public class PrintFilteredArray {
	public static void print(int[] list, Filter filter) {
		System.out.println(Arrays.toString(filter.filter(list)));
	}

	public static void main(String args[]) {
		int[] test = { 5, 9, 2, 7, 2, 6, 7, 12, 5, 43, 4 };
		print(test, new PrimeFilter());
		// ergibt die Zahlen 5,2,7,2,7,5,43 (nur Primzahlen)
		print(test, new TwoDigitsFilter());
		// ergibt die Zahlen 12,43 (nur Zahlen mit zwei Ziffern)
		print(test, new UniqueFilter());
		// ergibt die Zahlen 5,9,2,7,6,12,43,4 (jede Zahl nur beim ersten
		// Vorkommen)

	}
}
