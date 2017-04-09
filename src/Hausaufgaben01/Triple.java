import java.util.ArrayList;

/**
 * Created by Tom on 04.04.2017.
 * 
 * Triple class which represents a triple of three integer numbers
 */
public class Triple {

	// the three ints that form the triple
	private int a, b, c = 0;

	/**
	 * Constructor to create a Triple Object
	 * 
	 * @param a
	 *            first integer value of the triple
	 * @param b
	 *            second integer value of the triple
	 * @param c
	 *            third integer value of the triple
	 */
	public Triple(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * Returns the Triple as a beautiful formated String
	 * 
	 * @return Triple printed in the form (a/b/c)
	 */
	public String toString() {
		return "(" + a + "/" + b + "/" + c + ")";
	}

	/**
	 * Returns a list of Triples that match the following criteria:
	 * a < b < c
	 * a^2 + b^2 = c^2
	 * a + b + c = n
	 * 
	 * @param n
	 *            the number, that the rules must match
	 * @return list of all Triples that fulfill the rules given in the task
	 */
	public static ArrayList<Triple> getTriple(int n) {
		ArrayList<Triple> list = new ArrayList<>();
		for (int i = 0; i < n - 3; i++) {
			for (int j = i + 1; j < n - 2; j++) {
				int k = n - i - j;
				if (k != j) {
					if ((i * i + j * j) == k * k) {
						list.add(new Triple(i, j, k));
					}
				}
			}
		}
		return list;
	}

	public static void main(String args[]) {
		System.out.println(getTriple(30));
		System.out.println(getTriple(252));
	}
}