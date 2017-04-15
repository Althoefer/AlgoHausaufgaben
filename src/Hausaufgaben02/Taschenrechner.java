import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Taschenrechner {

	private static HashMap<String, Double> mapVars = new HashMap<>();

	// check if given variable name is allowed
	private static boolean checkCorrectVar(String var) {
		for (int i = 0; i < var.length(); ++i) {
			if (var.charAt(i) < 'a' || var.charAt(i) > 'z') {
				return false;
			}
		}
		return true;
	}

	private static double eval(String exprUPN) {
		ArrayList<Double> numberStack = new ArrayList<>();
		String[] exprParts = exprUPN.split(" +");
		for (int i = 0; i < exprParts.length; ++i) {
			if (exprParts[i].charAt(0) >= '0' && exprParts[i].charAt(0) <= '9') {
				// parsing numbers
				// assuming if first char is 0 < char < 9 rest of number is
				// correct
				numberStack.add(Double.parseDouble(exprParts[i]));
			} else if (exprParts[i].charAt(0) >= 'a' && exprParts[i].charAt(0) <= 'z') {
				// parsing variables
				if (mapVars.get(exprParts[i]) != null) {
					numberStack.add(mapVars.get(exprParts[i]));
				} else {
					return Double.NaN;
				}
			} else {
				// parsing operators
				Double newVal;
				if (numberStack.size() < 2) {
					return Double.NaN;
				}
				switch (exprParts[i].charAt(0)) {
				case '+':
					newVal = numberStack.remove(numberStack.size() - 2) + numberStack.remove(numberStack.size() - 1);
					numberStack.add(newVal);
					break;
				case '-':
					newVal = numberStack.remove(numberStack.size() - 2) - numberStack.remove(numberStack.size() - 1);
					numberStack.add(newVal);
					break;
				case '*':
					newVal = numberStack.remove(numberStack.size() - 2) * numberStack.remove(numberStack.size() - 1);
					numberStack.add(newVal);
					break;
				case '/':
					newVal = numberStack.remove(numberStack.size() - 2) / numberStack.remove(numberStack.size() - 1);
					numberStack.add(newVal);
					break;
				default:
					return Double.NaN;
				}
			}
		}
		if (numberStack.size() > 1) {
			return Double.NaN;
		} else {
			return numberStack.get(0);
		}
	}

	public static String analyze(String expr) {
		if (expr.contains("=")) {
			String[] exprParts = expr.split("=");
			for (int i = 0; i < exprParts.length; ++i) {
				exprParts[i] = exprParts[i].trim();
			}
			if (!checkCorrectVar(exprParts[0])) {
				return "Error: Unallowed variable name";
			}
			if (exprParts.length < 2) {
				return "Error: Unallowed Expression";
			}
			mapVars.put(exprParts[0], eval(exprParts[1]));
			return "result: " + exprParts[0] + "=" + mapVars.get(exprParts[0]);
		} else {
			return "result: " + eval(expr);
		}
	}

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			String z = "";
			while (true) {
				z = in.nextLine();
				if (z.equals("quit")) {
					break;
				} else if (z.equals("clear")) {
					mapVars.clear();
				} else if (z.equals("")) {
					continue;
				} else {
					System.out.println("task '" + z + "' => " + analyze(z));
				}
			}
		} catch (Exception e) {
			System.out.println("Error: Input failed");
		}
	}
}
