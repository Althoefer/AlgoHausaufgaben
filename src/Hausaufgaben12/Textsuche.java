package src.Hausaufgaben12;

import java.util.ArrayList;

/**
 * Created by Manuel on 20.06.2017.
 */
public class Textsuche {


    /**Diese Funktion gibt eine ArrayList mit Positionen zurueck, an denen das Pattern im Text gefunden wurde
     *
     * @param text Text, der durchsucht wird
     * @param pattern Pattern, nach dem gesucht wird
     * @return Liste mit Positionen, an denen das Pattern zu finden ist.
     */
    public static ArrayList<Integer> textSearch(String text, String pattern) {
        ArrayList<Integer> ret = new ArrayList<>();

        text:
        for (int i = 0; i < text.length(); i++) {
            boolean isMasked = false;
            boolean isGroup = false;
            ArrayList<Character> group = new ArrayList<>();
            int patternPos = i;
            int j;
            for (j = 0; j < pattern.length(); j++) {
                if (patternPos >= text.length()) break;
                if (isMasked) {
                    if (pattern.charAt(j) == text.charAt(patternPos)) {
                        patternPos++;
                        isMasked = false;
                    }else{
                        continue text;
                    }
                } else if (isGroup) {
                    if (pattern.charAt(j) == ']') {
                        boolean found = false;
                        for(char c : group){
                            if(c == text.charAt(patternPos)){
                                found = true;
                            }
                        }
                        isGroup = false;
                        group.clear();
                        if(found){
                            patternPos++;
                        }else{
                            continue text;
                        }
                    } else {
                        group.add(pattern.charAt(j));
                    }

                } else {
                    if (pattern.charAt(j) == '\\') {
                        isMasked = true;
                    } else if (pattern.charAt(j) == '.') {
                        patternPos++;
                    }else if(pattern.charAt(j) == '['){
                       isGroup= true;
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
            if(j == pattern.length())ret.add(i);
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
