import java.util.Arrays;
import java.util.LinkedList;

public class Solution2 {

    static int n;
    static int[][] companyPrefs;
    static int[][] studentPrefs;
    static int[] matching;
    // int[] matching
    static LinkedList<Integer> students = new LinkedList<>();
    public static void main(String[] args) {

        //Read and sort data
        Parse parser = new Parse();
        parser.sortData();

        companyPrefs = parser.getcompanyPrefs();
        studentPrefs = parser.getStudentPrefs();
        n = studentPrefs.length;
        matching = new int[n];
        Arrays.fill(matching, -1);

/*        for(int[] student : studentPrefs) {
            for(int pref: student) {
                System.out.print(pref + " ");
            }
            System.out.println();
        }*/

        //Match
        match();

        //Print
        for (int match: matching) {
            System.out.println(String.valueOf(match));
        }

    }


    public static void match() {
        //Adds all student to a list
        for(int student = 1; student<studentPrefs.length; student++) {
            students.add(student);
            System.out.println(student);
        }

        while (!(students.isEmpty())) {
            int student = students.getFirst();
            students.removeFirst();
            int prefNbr = student-1;


            System.out.println("student: " + student);
            System.out.println("studentPrefs[student][0]: " + studentPrefs[student][0] );

            //tror det inte funkar för att vår matris är n +1 ggr n så man kan inte använda samma index för rad och kolumn
            int perferedCompany = studentPrefs[student][studentPrefs[student][0]]; //most prefered that has not yet applied to
            studentPrefs[student][0] = studentPrefs[student][0] + 1;

            if (matching[perferedCompany] == -1) {
                matching[perferedCompany] = student;
            } else if (companyPrefs[perferedCompany][matching[perferedCompany]] > companyPrefs[perferedCompany][student]) {

                students.addLast(matching[perferedCompany]);
                matching[perferedCompany] = student;
            } else {
                students.addLast(student);
            }


        }

    }

}
