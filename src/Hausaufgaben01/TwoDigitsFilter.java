package src.Hausaufgaben01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 04.04.2017.
 */
public class TwoDigitsFilter implements Filter{
    @Override
    public int[] filter(int[] list) {
        List<Integer> filtered = new ArrayList<>();
        for(int number : list){
            if(Math.abs(number) > 9 && Math.abs(number) < 100){
                filtered.add(number);
            }
        }
        return  filtered.stream().mapToInt(i->i).toArray();
    }
}
