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
      //  parser.sortData();
        companyPrefs = parser.getcompanyPrefs();
        studentPrefs = parser.getStudentPrefs();

      /*  for (int k = 0; k < studentPrefs.length; k++) {
            for (int h = 0; h < studentPrefs[k].length; h++) {
                System.out.print(studentPrefs[k][h] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < companyPrefs.length; i++) {
            for (int j = 0; j < companyPrefs[i].length; j++) {
                System.out.print(companyPrefs[i][j] + " ");
            }
            System.out.println();
        }
       */


        int m = studentPrefs.length;
        matches = new int[m];
        Arrays.fill(matches, -1);

        match();
        for (int i = 1; i< matches.length; i++) {
            System.out.println(String.valueOf(matches[i]));
        }
    }
    public static void match() {
        for (int s = 1; s < studentPrefs.length; s++) {
            students.add(s);
        }
        while (!(students.isEmpty())) {
            int student = students.getFirst();
            students.removeFirst();
            int apply = studentPrefs[student][0]+1;
        //    System.out.println("apply: " + apply);
            int perferredCompany = 1;
            for(int i = 1; studentPrefs[student][i] != apply; i++) {
                perferredCompany++;
            }
            studentPrefs[student][0] = studentPrefs[student][0]+1;
            if (matches[perferredCompany] == -1) {
                matches[perferredCompany] = student;
          //      System.out.println("matches :" + Arrays.toString(matches));
            } else if (companyPrefs[perferredCompany][matches[perferredCompany]] > companyPrefs[perferredCompany][student]) {
                students.addLast(matches[perferredCompany]);
                matches[perferredCompany] = student;
          //      System.out.println("matches :" + Arrays.toString(matches));
            } else {
                students.addLast(student);
            }
            perferredCompany = 1;
        }
    }
}

        /*while (true) {
            int s = -1;
            for (int i = 0; i < n; i++) {
                if (studentFree[i]) {
                   // System.out.println("StudentFree : " + Arrays.toString(studentFree));
                    s = i; // Lägger in i i student
                    break;
                }
            }
            if (s == -1) {
                // Alla har gåtts igenom?
                break;
            }
            for (int i = 0; i < n && studentFree[s]; i++) {
                int c = studentPref[s][i];
               // System.out.println("C: " + c);
                if (matches[c] == -1) {
                  //  System.out.println("ref till comp. : " + c);
                  //  System.out.println("input i matches 4 company: " + s);
                    matches[c] = s;
                    studentFree[s] = false;
                  //  System.out.println("matches: " + Arrays.toString(matches));
                } else {
                    int s1 = matches[c];
                    if (companyPref[c][s] < companyPref[c][s1]) {
                        matches[c] = s;
                        studentFree[s] = false;
                        studentFree[s1] = true;
                    }
                }
            }
        }
        return Arrays.copyOfRange(matches, 1, matches.length);
        //return matches;*/



