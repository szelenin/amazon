/**
 * Created by sergey on 26.11.14.
 */

import java.io.*;
import java.util.*;

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
        ArrayList<Row> lVector = new ArrayList<Row>();
        Row firstRow = new Row().add(array.get(0));
        lVector.add(firstRow);
        for (int i = 1; i < array.size(); i++) {
            Row row = new Row(findRowInL(lVector, array.get(i), i - 1));
            row.add(array.get(i));
            lVector.add(row);
        }
        int maxSize = -1;
        Row result = null;
        for (Row row : lVector) {
            if (row.size() > maxSize) {
                maxSize = row.size();
                result = row;
            }
        }
        return result.subList();
    }

    private static Row findRowInL(ArrayList<Row> lVector, int arrayValue, int lIndex) {
        Row result = new Row();
        int maxRowLength = -1;

        for (int i = lIndex; i >= 0; i--) {
            Row row = lVector.get(i);
            int lastRowElement = row.lastElement();
            if (lastRowElement < arrayValue && maxRowLength < row.size()) {
                result = row;
                maxRowLength = row.size();
            }
        }
        return result;
    }

    private static class Row {
        private ArrayList<Integer> row;
        private int lastIndex;

        public Row(Row other) {
            this.row = other.row;
            this.lastIndex = other.size();
        }

        public Row() {
            row = new ArrayList<Integer>();
            lastIndex = -1;
        }

        public int lastElement() {
            return row.get(lastIndex);
        }

        public int size() {
            return lastIndex + 1;
        }

        public Row add(Integer element) {
            row.add(element);
            lastIndex = row.size() - 1;
            return this;
        }

        public List<Integer> subList() {
            return row.subList(0, lastIndex);
        }
    }
}
