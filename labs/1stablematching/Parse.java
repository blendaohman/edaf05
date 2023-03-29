import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Parse {
    private int[][] studentPref;
    private int[][] companyPref;
    public void sortData() {
        Scanner scanner = new Scanner(System.in);
        // Read the input data from the input file
/*        if(scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine()); //Första linen är n;
            System.out.println("n: " + n);
        }*/

        List<String> lines = new LinkedList<>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }

        int n = Integer.parseInt(lines.get(0));
        studentPref = new int[n][n];
        companyPref = new int[n][n];

        for(String line : lines) {
            String[] parts = line.split(" ");
            int index = Integer.parseInt(parts[0]) -1;
            if(companyPref[index] == null) {
                //Lägg in preffar i company
                for(int i=1; i< parts.length-1; i++) {
                    companyPref[index][i-1] = Integer.parseInt(parts[i]);
                }
            } else {
                for(int i=1; i< parts.length-1; i++) {
                    studentPref[index][i-1] = Integer.parseInt(parts[i]);
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
