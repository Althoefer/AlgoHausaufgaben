import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class Taschenrechner interprets UPN expression from the command line. In
 * those expressions variables can be declared and used in future expression
 * 
 * @author C.Wassermann
 *
 */
public class Taschenrechner {

	// HashMap which contains previous entered variables
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

	// check if given string can be interpreted as a number
	private static boolean isNumber(String num) {
		for (int i = 0; i < num.length(); ++i) {
			if (num.charAt(i) < '0' || num.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}

	// check if given string resolves to a known variable
	private static boolean isVariable(String var) {
		if (!mapVars.containsKey(var)) {
			return false;
		}
		return true;
	}

	// check if given string can be resolved to + / - / * / / and if enough
	// numbers are existent
	private static boolean isAllowedOperand(String op, ArrayList<Double> numberStack) {
		if (op.length() > 1
				|| (op.charAt(0) != '+' && op.charAt(0) != '-' && op.charAt(0) != '*' && op.charAt(0) != '/')
				|| numberStack.size() < 2) {
			return false;
		}
		return true;
	}

	// execute given operand on last two numbers in numberStack
	private static double executeOperand(String operand, ArrayList<Double> numberStack) {
		if (operand.equals("+")) {
			return numberStack.remove(numberStack.size() - 2) + numberStack.remove(numberStack.size() - 1);
		} else if (operand.equals("-")) {
			return numberStack.remove(numberStack.size() - 2) - numberStack.remove(numberStack.size() - 1);
		} else if (operand.equals("*")) {
			return numberStack.remove(numberStack.size() - 2) * numberStack.remove(numberStack.size() - 1);
		} else if (operand.equals("/")) {
			return numberStack.remove(numberStack.size() - 2) / numberStack.remove(numberStack.size() - 1);
		}
		return Double.NaN;
	}

	// check if status from numberStack is a valid end status (just 1 element)
	private static double checkList(ArrayList<Double> numberStack) {
		if (numberStack.size() != 1) {
			return Double.NaN;
		} else {
			return numberStack.get(0);
		}
	}

	/**
	 * Evaluate an UPN expression and return its result
	 * 
	 * @param exprUPN
	 *            the UPN expressions which is evaluated
	 * @return result of given UPN expression (is NaN if parsing error occurs)
	 */
	private static double eval(String exprUPN) {
		ArrayList<Double> numberStack = new ArrayList<>();
		String[] exprParts = exprUPN.split(" +");
		for (int i = 0; i < exprParts.length; ++i) {
			if (exprParts[i].equals("")) {
				return Double.NaN;
			} else if (isNumber(exprParts[i])) { // check if number
				numberStack.add(Double.parseDouble(exprParts[i]));
			} else if (isVariable(exprParts[i])) { // check if variable
				numberStack.add(mapVars.get(exprParts[i]));
			} else if (isAllowedOperand(exprParts[i], numberStack)) { // check
																		// possible
																		// execution
																		// of
																		// operand
				numberStack.add(executeOperand(exprParts[i], numberStack));
			} else { // parsing error (non of the above)
				return Double.NaN;
			}
		}
		return checkList(numberStack);
	}

	/**
	 * Take given input and interpret variable declarations as well as normal
	 * UPN statements. Usage of variable declarations: "VarName = UPN
	 * expression"
	 * 
	 * @param expr
	 *            expression which is interpreted
	 * @return result of expression
	 */
	public static String analyze(String expr) {
		if (expr.contains("=")) { // UPN with variable assignment
			String[] exprParts = expr.split("=");
			for (int i = 0; i < exprParts.length; ++i) {
				exprParts[i] = exprParts[i].trim();
			}
			if (exprParts.length != 2) { // catch cases with more / less than
											// one '='
				return "Error: Unallowed Expression";
			}
			if (!checkCorrectVar(exprParts[0])) { // catch unallowed variable
													// names
				return "Error: Unallowed variable name";
			}
			mapVars.put(exprParts[0], eval(exprParts[1]));
			return "result: " + exprParts[0] + "=" + mapVars.get(exprParts[0]);
		} else { // normal UPN without variable assignment
			return "result: " + eval(expr);
		}
	}

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			String z = "";
			while (true) {
				z = in.nextLine();
				if (z.equals("quit")) {
					System.out.println("exiting now");
					break;
				} else if (z.equals("clear")) {
					mapVars.clear();
					System.out.println("variable declarations cleared");
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
