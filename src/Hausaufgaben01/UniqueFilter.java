package src.Hausaufgaben01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 04.04.2017.
 */
public class UniqueFilter implements Filter{
    @Override
    public int[] filter(int[] list) {
        List<Integer> filtered = new ArrayList<>();
        for (int number : list) {
            if (filtered.indexOf(number) == -1) {
                filtered.add(number);
            }
        }
        return  filtered.stream().mapToInt(i->i).toArray();
    }
}
