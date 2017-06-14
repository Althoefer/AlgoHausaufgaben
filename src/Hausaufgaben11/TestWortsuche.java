public class TestWortsuche {

	public static void main(String[] args) {
		Wortsuche meinWort = new Wortsuche("DieguteBabanane123Lecker");
		int ergebnis = meinWort.findFirst("banane123");
		if (ergebnis == -1) {
			System.out.println("Pattern wurde nicht gefunden.");
		} else {
			System.out.println("Gefunden an Position " + ergebnis);
		}
	}
}
