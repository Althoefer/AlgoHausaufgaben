package src.Hausaufgaben01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 04.04.2017.
 */
public class PrimeFilter implements Filter{
    @Override
    public int[] filter(int[] list) {
        List<Integer> filtered = new ArrayList<>();
        for (int number : list){
            if(isPrime(number)){
                filtered.add(number);
            }
        }
        return  filtered.stream().mapToInt(i->i).toArray();
    }

    private static boolean isPrime(int number){
        for(int i=2; i< Math.sqrt(number); i++){
            if (number % i == 0){
                return false;
            }
        }
        return true;
    }
}
