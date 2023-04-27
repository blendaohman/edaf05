import java.util.Arrays;
import java.util.LinkedList;

public class Solution {
    static int n;
    static int[][] companyPrefs;
    static int[][] studentPrefs;
    static int[] matches;
    static LinkedList<Integer> students = new LinkedList<>();

    public static void main(String[] args) {
        Parse parser = new Parse();
        companyPrefs = parser.getcompanyPrefs();
        studentPrefs = parser.getStudentPrefs();

        int m = studentPrefs.length;
        matches = new int[m];
        Arrays.fill(matches, -1); // Fyll en array med -1 för att symbolisera ingen match

        match();
        for (int i = 1; i< matches.length; i++) {
            System.out.println(String.valueOf(matches[i]));
        }
    }
    public static void match() {
        for (int s = 1; s < studentPrefs.length; s++) { //Lägger till studenterna i en array för att hålla koll på vilka som inte matchat
            students.add(s);
        }
        while (!(students.isEmpty())) {
            int student = students.getFirst();
            students.removeFirst();
            int priority = studentPrefs[student][0]+1; // Set the priority to 1
            int perferredCompany = studentPrefs[student][priority];
            /*
            int perferredCompany = 1; //Start from company 1
            for(int i = 1; studentPrefs[student][i] != priority; i++) { // Check which company has the matching priority
                perferredCompany++;
            }
            studentPrefs[student][0] = studentPrefs[student][0]+1; //Update priority if the student should have to match again
            */

            if (matches[perferredCompany] == -1) {
                matches[perferredCompany] = student; //If the company has no match, insert student there
            } else if (companyPrefs[perferredCompany][matches[perferredCompany]] > companyPrefs[perferredCompany][student]) {
                //Check the priority for the current student and compare with the one you have
                //If current student is more preffered change the match and add the other student back to the array
                students.addLast(matches[perferredCompany]);
                matches[perferredCompany] = student;
            } else {
                students.addLast(student);
            }
            perferredCompany = 1; //Set the prefferedCompany back to one before doing another student
        }
    }
}