import java.util.*;
public class Solution {

    public static void main(String[] args) {
        Parse parser = new Parse();
        parser.sortData();

        int[] matching = match(parser.getStudentPrefs(), parser.getcompanyPrefs());
        System.out.println(Arrays.toString(matching));
    }

    public static int[] match(int[][] studentPref, int[][] companyPref) {
        int n = studentPref.length;
        int[] matches = new int[n];
        Arrays.fill(matches, -1);

        boolean[] studentFree = new boolean[n];
        Arrays.fill(studentFree, true);

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

        return matches;
    }
}