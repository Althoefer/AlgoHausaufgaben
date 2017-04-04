package src.Hausaufgaben01;

/**
 * Created by Tom on 04.04.2017.
 */
public class TwoDigitsFilter implements Filter{
    @Override
    public int[] filter(int[] list) {
        int[] filtered = new int[list.length];
        int twoDigitsFound = 0;
        for(int number : list){
            if(Math.abs(number) > 9 && Math.abs(number) < 100){
                filtered[twoDigitsFound] = number;
                twoDigitsFound++;
            }
        }

        int[] filteredFinal = new int[twoDigitsFound];
        System.arraycopy(filtered, 0, filteredFinal, 0, twoDigitsFound);
        return filteredFinal;
    }
}
