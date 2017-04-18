package src.Hausaufgaben03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Manuel on 18.04.2017.
 */
public class SpieleHash {

    private int size = 53;
    private String[] table = new String[53];


    private int hashIndex(String spiel){
        return Math.abs(spiel.hashCode()) % 100;
    }

    private int hashIncrement(String spiel){
        return (Math.abs(spiel.hashCode()/1000) % 100) +1;
    }

    public void add(String spiel){
        int h0 = hashIndex(spiel) % size;
        int i = hashIncrement(spiel) % (size -1) + 1;
        for (int j = 0; j< size; j++){
            if(table[(h0 + i*j) % size] == null){
                table[(h0 + i*j) % size] = spiel;
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean contains(String spiel){
        int h0 = hashIndex(spiel) % size;
        int i = hashIncrement(spiel) % (size -1) + 1;
        for (int j = 0; j< size; j++){
            if(table[(h0 + i*j) % size] == null){
                return false;
            }
            if(table[(h0 + i*j) % size].equals(spiel)){
                return true;
            }

        }
        return false;
    }

    public static void main(String[] args){
        SpieleHash spieleHash = new SpieleHash();
        File f = new File("games50.txt");
        try {
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                spieleHash.add(sc.nextLine().split("\t")[1].trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(spieleHash.contains("FIFA 16"));
        System.out.println(spieleHash.contains("Star Wars: Battlefront"));
        System.out.println(spieleHash.contains("WOW"));
    }
}
