package com.excersise.dailycoding;

import java.util.*;

public class FindBusiest {

    public static List<Entries> entriesList() {
      /*  Entries entries_1 = new Entries(4, 1, "enter");
        Entries entries_2 = new Entries(2, 2, "exit");
        Entries entries_3 = new Entries(6, 2, "enter");
        Entries entries_4 = new Entries(8, 1, "enter");
        Entries entries_5 = new Entries(5, 5, "exit");
        Entries entries_6 = new Entries(7, 1, "enter");
        Entries entries_7 = new Entries(3, 4, "enter");
        Entries entries_8 = new Entries(1, 2, "enter");
        Entries entries_9 = new Entries(9, 4, "exit");*/

       /* Entries entries_1 = new Entries(1, 3, "enter");
        Entries entries_2 = new Entries(2, 3, "exit");
        Entries entries_3 = new Entries(3, 2, "enter");
        Entries entries_4 = new Entries(4, 1, "exit");
        Entries entries_5 = new Entries(5, 3, "enter");
        Entries entries_6 = new Entries(6, 4, "exit");*/

        Entries entries_1 = new Entries(1, 10, "enter");
        Entries entries_2 = new Entries(2, 10, "exit");
        Entries entries_3 = new Entries(3, 4, "exit");
        Entries entries_4 = new Entries(4, 1, "enter");
        Entries entries_5 = new Entries(5, 3, "exit");


        return Arrays.asList(entries_1, entries_2, entries_3, entries_4, entries_5);
    }

    public static void main(String[] arg) {
        List<Entries> entries = entriesList();

        String period = findBusiestPeriod(entries);
        System.out.println(period);
    }

    public static String findBusiestPeriod(List<Entries> entries) {
        sortByTimestamp(entries);

        int countPeople = 0;

        int peekPeople = Integer.MIN_VALUE;

        int start = 0;
        int end = 0;
        int currentIndex = 0;

        for (Entries oneEntry : entries) {

            if (oneEntry.type.equals("exit")) {
                countPeople -= oneEntry.count;
            } else {
                countPeople += oneEntry.count;
            }

            if (countPeople == 0 && oneEntry.count > peekPeople) {
                peekPeople = oneEntry.count;
                start = entries.get(end).type.equals("enter") ? end : end + 1;
                end = currentIndex ;

            }
            currentIndex++;

        }

        return //start == 0 ? entries.get(start).timestamp + "-" + entries.get(start).timestamp :
                entries.get(start).timestamp + "-" + entries.get(end-1).timestamp;
    }

    public static void sortByTimestamp(List<Entries> entries) {
        Collections.sort(entries, new Comparator<Entries>() {
            public int compare(Entries s1, Entries s2) {
                return s1.timestamp > s2.timestamp ? 1 : (s1.timestamp == s2.timestamp ? 0 : -1);
            }
        });
    }


    public static class Entries {
        public int timestamp;
        public int count;
        public String type;

        public Entries(int newTimeStamp, int newCount, String newType) {
            this.timestamp = newTimeStamp;
            this.count = newCount;
            this.type = newType;
        }
    }
}
