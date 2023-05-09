import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Gorilla {
    private Map<String, LinkedList<String>> mapping = new HashMap<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int row = 0;
        while(scan.hasNextLine()){
            String line = scan.nextLine();

            if(row == 0) { //Första raden. detta är alla noder
               String[] letters = line.split(" ");

            }


        }


    }
}
