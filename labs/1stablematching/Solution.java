import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Parse parser = new Parse();
        parser.sortData();

        int[] matching = match(parser.getStudentPrefs(), parser.getcompanyPrefs());
        for(int match : matching) {
            System.out.println(match);
        }

    }


    public static int[] match(int[][] studentPref, int[][] companyPref) {
        int n = studentPref.length; //Ska den va -1?
        int[] matches = new int[n];
        for(int[] student : studentPref) {
            for(int pref: student) {
                System.out.print(pref + " ");
            }
            System.out.println();
        }
        System.out.println("n: " + n);

        Arrays.fill(matches, -1);

        boolean[] studentFree = new boolean[n];
        Arrays.fill(studentFree, true);
        studentFree[0] = false;

        while (true) {
            int s = -1;
            for (int i = 0; i < n; i++) {
                if (studentFree[i]) {
                    s = i;
                    break;
                }
            }
            if (s == -1) {
                break;
            }
            for (int i = 0; i < n && studentFree[s]; i++) {
                int c = studentPref[s][i];
                if (matches[c] == -1) {
                    matches[c] = s;
                    studentFree[s] = false;
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
        //return matches;
    }

}
