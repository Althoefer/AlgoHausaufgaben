package src.Hausaufgaben01;

/**
 * Created by Tom on 04.04.2017.
 */
public class PrimeFilter implements Filter{
    @Override
    public int[] filter(int[] list) {
        int[] filtered = new int[list.length];
        int primesFound = 0;
        for (int number : list){
            if(isPrime(number)){
                filtered[primesFound] = number;
                primesFound++;
            }
        }
        int[] filteredFinal = new int[primesFound];
        System.arraycopy(filtered, 0, filteredFinal, 0, primesFound);
        return filteredFinal;
    }

    private static boolean isPrime(int number){
        for(int i=1; i<=(number+1)/2; i++){
            for(int j=1; j<=i; j++) {
                if (j * i == number) {
                    return false;
                }
            }
        }
        return true;
    }
}
