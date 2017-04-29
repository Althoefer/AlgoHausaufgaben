package Hausaufgaben04;

public class Klammer{

    public boolean isEbenentreu(String s) {
        MyDeque stack = new MyDeque();
        for(char c : s.toCharArray()){
            if( c == '(' || c == '{' || c == '['){ //if the bracket is open, it is added to steh stack to be close
                stack.addLast(new Node(c+""));
            }
            else if( c == ')' || c == '}' || c == ']'){ //the bracket might close the last added bracket on the stack
                if       ( c == ')' && !stack.isEmpty() && stack.removeLast().charAt(0) == '('){
                    continue;
                } else if( c == '}' && !stack.isEmpty() && stack.removeLast().charAt(0) == '{'){
                    continue;
                } else if( c == ']' && !stack.isEmpty() && stack.removeLast().charAt(0) == '['){
                    continue;
                } else{ //the bracket does not match the last opened bracket, the String is not ebenentreu
                    return false;
                }
            } else{
                continue;
            }

        }
        if(stack.isEmpty()){ //stack need to be empty, means all brackets are close
            return true;
        } else{
            return false;
        }
    }

    public static void main(String args[]){
        Klammer k = new Klammer();
        System.out.println("(([[]])) should be true: " + k.isEbenentreu("(([[]]))"));
        System.out.println("([)] should be false: " + k.isEbenentreu("([)]"));
        System.out.println("([]]) should be false: "+ k.isEbenentreu("([]])"));
        System.out.println("(())) should be false: " + k.isEbenentreu("(()))"));
        System.out.println("(() should be false: " + k.isEbenentreu("(()"));
        System.out.println("({[])} should be false: " + k.isEbenentreu("({[])}"));
        System.out.println("no brackets at all should be true: " + k.isEbenentreu("abc"));
        System.out.println("for(ding : dong){if(lalala()){doSomething()}} should be true: " + k.isEbenentreu("for(ding : dong){if(lalala()){doSomething()}}"));
    }
}