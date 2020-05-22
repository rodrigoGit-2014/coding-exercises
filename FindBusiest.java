package com.excersise.dailycoding;

import java.util.*;

public class FindBusiest {

    public static List<Entries> entriesList() {
        Entries entries_1 = new Entries(1, 12, "enter");
        Entries entries_2 = new Entries(2, 12, "exit");
        Entries entries_3 = new Entries(3, 4, "enter");
        Entries entries_4 = new Entries(4, 1, "exit");
        Entries entries_5 = new Entries(5, 1, "exit");
        Entries entries_6 = new Entries(6, 13, "enter");
        Entries entries_7 = new Entries(7, 15, "exit");

        return Arrays.asList(entries_1, entries_2, entries_3, entries_4, entries_5, entries_6, entries_7);
    }

    public static void main(String[] arg) {
        List<Entries> entries = entriesList();

        String period = findBusiestPeriod(entries);
        System.out.println(period);
    }

    public static String findBusiestPeriod(List<Entries> entries) {
        sortByTimestamp(entries);
        int peopleCounter = 0;
        int peakFound = Integer.MIN_VALUE;

        for (Entries oneEntry : entries) {
            peopleCounter += oneEntry.type.equals("enter") ? oneEntry.count : oneEntry.count * -1;

            if (peopleCounter > peakFound) {
                peakFound = peopleCounter;
                Finder.start = Finder.nextStart;
                Finder.end = Finder.currentIndex;
            }
            if (peopleCounter == 0) {
                Finder.nextStart = Finder.currentIndex + 1;
            }
            Finder.currentIndex++;
        }

        return entries.get(Finder.start).timestamp + "-" + entries.get(Finder.end).timestamp;
    }

    public static void sortByTimestamp(List<Entries> entries) {
        Collections.sort(entries, new Comparator<Entries>() {
            public int compare(Entries s1, Entries s2) {
                return s1.timestamp > s2.timestamp ? 1 : (s1.timestamp == s2.timestamp ? 0 : -1);
            }
        });
    }


    public static class Finder {
        public static int start = 0;
        public static int end = 0;
        public static int nextStart = 0;
        public static int currentIndex = 0;

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
