import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Parse {
    private int[][] studentPref;
    private int[][] companyPref;
    private int[][] hasAppered;
    private List<String> lines = new LinkedList<>();
    private int n;

    public Parse(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        studentPref = new int[n + 1][n + 1]; //Därmed kan vi behålla nummerna i sina respektive rader och kolumner
        companyPref = new int[n + 1][n + 1];

        while(scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int nbr = scanner.nextInt();
                if (companyPref[nbr][1] == 0) {
                    for (int i = 1; i < n+1; i++) { // Lägg till datan för det företaget
                        int j = scanner.nextInt();
                        companyPref[nbr][j] = i;
                    }
                }
                else if (studentPref[nbr][1] == 0) { // Annars lägg till i student
                    for (int i = 1; i < n+1; i++) {
                        int j = scanner.nextInt();
                        studentPref[nbr][i] = j;
                    }
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
