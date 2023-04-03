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
        lines.remove(0); //removing the first index ince it only tells us n
        studentPref = new int[n+1][n];
        companyPref = new int[n+1][n];


    }

    public void sortData() {

        //Kör vi detta på första linen dvs den med antalet

        for(String line : lines) {

            String[] parts = line.split(" ");
            int nbr = Integer.parseInt(parts[0]);
            //[1, 2, 1, 3] -> [2, 1, 3] [1, 0, 2]
           /* System.out.println("Line: " + line);
            System.out.println("PartNbr: " + nbr);
            System.out.println("companyPref[nbr]: " + companyPref[nbr][0] + companyPref[nbr][1]);
*/
            if(companyPref[nbr][0] == 0) { //om vi inte har något i detta company nbr
                //Lägg in preffar i company
                for(int i=0; i< parts.length; i++) {

                   /* System.out.println("nbr: " + nbr); //ok
                    System.out.println("i: " + i); //ok
                    System.out.println("Integer.parseInt(parts[i]): " + Integer.parseInt(parts[i])); //inte ok*/
                    companyPref[nbr][Integer.parseInt(parts[i])-1] = i;



                }

            } else { //Annars lägg till delarna i student
                for(int i=0; i< parts.length; i++) {
                    studentPref[nbr][Integer.parseInt(parts[i])-1] = i;
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
