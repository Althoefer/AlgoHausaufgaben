package src.Hausaufgaben12;

import java.util.ArrayList;

/**
 * Created by Manuel on 20.06.2017.
 */
public class Textsuche {

    public static ArrayList<Integer> textSearch(String text, String pattern) {
        ArrayList<Integer> ret = new ArrayList<>();

        text:
        for (int i = 0; i < text.length(); i++) {
            boolean isMasked = false;
            boolean isGroup = false;
            ArrayList<Character> group = new ArrayList<>();
            int patternPos = i;
            for (int j = 0; j < pattern.length(); j++) {
                if (patternPos >= text.length()) break;
                if (isMasked) {
                    if (pattern.charAt(j) == text.charAt(patternPos))
                        isMasked = false;
                } else if (isGroup) {
                    if (pattern.charAt(j) == ']') {
                        isMasked = false;
                    } else {
                        group.add(pattern.charAt(j));
                    }

                } else {
                    if (pattern.charAt(j) == '\\') {
                        isMasked = true;
                    } else if (pattern.charAt(j) == '.') {
                        patternPos++;
                    }else if(group.size() > 0){
                        for(char  c: group){
                            if(c == text.charAt(patternPos)){
                                group.clear();
                                continue;
                            }
                        }
                        group.clear();
                        continue text;
                    }
                    else {
                        if(pattern.charAt(j) == text.charAt(patternPos)) {
                            patternPos++;
                        }else{
                            continue text;
                        }
                    }

                }
            }
            ret.add(i);
        }


        return ret;
    }

    public static void main(String[] args) {
        System.out.println(textSearch("abcabcdababdc.", "ab")); //0, 3, 7, 9
        System.out.println(textSearch("abcabcdababdc.", "c.")); //2, 5, 12
        System.out.println(textSearch("abcabcdababdc.", "c\\.")); //12
        System.out.println(textSearch("abcabcdababdc.", "b[cd]")); //1,4,10
        System.out.println(textSearch("abcabcdababdc.", "a....c")); //0,7
        System.out.println(textSearch("a[aababa][ab]a", "a[ab]a")); //3,5
        System.out.println(textSearch("a[aababa][ab]a", "a.\\[a")); //7
    }
}
