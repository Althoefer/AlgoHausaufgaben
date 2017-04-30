
public class Klammer {

	public boolean isEbenentreu(String s) {
		MyDeque stack = new MyDeque();
		for (char c : s.toCharArray()) {
			if (c == '(' || c == '{' || c == '[') {
				// if the bracket is open, it is added to the stack to be close
				stack.addLast(new Node(c + ""));
				continue;
			}
			if (c == ')' || c == '}' || c == ']') {
				// the bracket might close the last added bracket on the stack
				if (c == ')' && !stack.isEmpty() && stack.removeLast().charAt(0) == '(') {
					continue;
				}
				if (c == '}' && !stack.isEmpty() && stack.removeLast().charAt(0) == '{') {
					continue;
				}
				if (c == ']' && !stack.isEmpty() && stack.removeLast().charAt(0) == '[') {
					continue;
				}
				// the bracket does not match the last opened bracket
				// --> the String is not ebenentreu
				return false;
			}
		}
		// stack is not empty --> not all brackets were closed
		if (!stack.isEmpty()) {
			return false;
		}
		return true;
	}

	public static void main(String args[]) {
		Klammer k = new Klammer();
		System.out.println("(([[]])) should be true: " + k.isEbenentreu("(([[]]))"));
		System.out.println("([)] should be false: " + k.isEbenentreu("([)]"));
		System.out.println("([]]) should be false: " + k.isEbenentreu("([]])"));
		System.out.println("(())) should be false: " + k.isEbenentreu("(()))"));
		System.out.println("(() should be false: " + k.isEbenentreu("(()"));
		System.out.println("({[])} should be false: " + k.isEbenentreu("({[])}"));
		System.out.println("no brackets at all should be true: " + k.isEbenentreu("abc"));
		System.out.println("for(ding : dong){if(lalala()){doSomething()}} should be true: "
				+ k.isEbenentreu("for(ding : dong){if(lalala()){doSomething()}}"));
	}
}