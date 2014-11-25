/**
 * Created by sergey on 26.11.14.
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int n = 0;
        List<Integer> array = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            String strValue = sc.nextLine();
            n = Integer.parseInt(strValue);
        }
        while (array.size() < n && sc.hasNextLine()) {
            array.add(Integer.parseInt(sc.nextLine()));
        }
        List<Integer> lis = solve(array);
        System.out.print(lis.size());
    }

    public static List<Integer> solve(List<Integer> array) {
        if (array == null) {
            return new ArrayList<Integer>(0);
        }
        if (array.size() < 2) {
            return array;
        }
        ArrayList<ArrayList<Integer>> lVector = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> firstRow = new ArrayList<Integer>(1);
        firstRow.add(array.get(0));
        lVector.add(firstRow);
        for (int i = 1; i < array.size(); i++) {
            ArrayList<Integer> row = new ArrayList<Integer>(findRowInL(lVector, array.get(i), i - 1));
            row.add(array.get(i));
            lVector.add(row);
        }
        int maxSize = -1;
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (ArrayList<Integer> row : lVector) {
            if (row.size() > maxSize) {
                maxSize = row.size();
                result = row;
            }
        }
        return result;
    }

    private static ArrayList<Integer> findRowInL(ArrayList<ArrayList<Integer>> lVector, int arrayValue, int lIndex) {
        ArrayList<Integer> result = new ArrayList<Integer>(0);
        int maxRowLength = -1;

        for (int i = lIndex; i >= 0; i--) {
            ArrayList<Integer> row = lVector.get(i);
            Integer lastRowElement = row.get(row.size() - 1);
            if (lastRowElement < arrayValue && maxRowLength < row.size()) {
                result = row;
                maxRowLength = row.size();
            }
        }
        return result;
    }
}
