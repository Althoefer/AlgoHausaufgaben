package src.Hausaufgaben01;

import java.util.ArrayList;

/**
 * Created by Tom on 04.04.2017.
 */
public class UniqueFilter implements Filter{
    @Override
    public int[] filter(int[] list) {
        ArrayList<Integer> filtered = new ArrayList<>();
        for (int number : list) {
            if (filtered.indexOf(number) == -1){
                filtered.add(number);
            }
        }
        int[] filteredFinal = new int[filtered.size()];
        for(int i=0; i<filtered.size(); i++) {
            filteredFinal[i] = filtered.get(i);
        }
        return filteredFinal;
    }
}
