import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Parse {
    private int[][] studentPref;
    private int[][] companyPref;

    private List<String> lines = new LinkedList<>();

    public Parse(){

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }

        int n = Integer.parseInt(lines.get(0));
        lines.remove(0); //removing the first index once it only tells us n
        studentPref = new int[n+1][n+1];
        companyPref = new int[n+1][n+1];
        scanner.close();
    }

    public void sortData() {
        for(String line : lines) {

            String[] parts = line.split(" ");
            int nbr = Integer.parseInt(parts[0]);
            if(companyPref[nbr][1] == 0) {
                for(int i=0; i< parts.length; i++) {
                    companyPref[nbr][Integer.parseInt(parts[i])] = i;
                }

            } else { //Annars lägg till delarna i student
                for (int i = 0; i < parts.length; i++) {
                    studentPref[nbr][Integer.parseInt(parts[i])] = i;
                }
            }
        }
    }


    public int[][] getStudentPrefs() {
        return studentPref;
    }

    public int[][] getcompanyPrefs() {
        return companyPref;
    }
}
