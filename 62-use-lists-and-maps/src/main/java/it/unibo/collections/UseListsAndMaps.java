package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int ELEMS = 100_000;
    private static final int READS = 1000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> array = new ArrayList<>();
        for(int i = 1000; i < 2000; i++) {
            array.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> linked = new LinkedList<>(array);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final int temp = array.get(0);
        array.set(0 , array.get(array.size() - 1));
        array.set(array.size() - 1, temp);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (final int elem: array) {
            System.out.println(elem);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        System.out.println("Test insert:");
        long time = System.nanoTime();
        
        for( int i = 0; i < ELEMS; i++) {
            array.add(0, i);
        }

        time = System.nanoTime() - time;
        var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("ArrayList time: "+time+"ns ("+millis+"ms)");

        time = System.nanoTime();
        
        for( int i = 0; i < ELEMS; i++) {
            linked.add(0, i);
        }

        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("LinkedList time: "+time+"ns ("+millis+"ms)");

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        System.out.println("Test read:");

        time = System.nanoTime();
        for (int i = 0; i < READS; i++ ) {
            array.get(array.size()/2);
        }

        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("ArrayList time: "+time+"ns ("+millis+"ms)");

        time = System.nanoTime();
        for (int i = 0; i < READS; i++ ) {
            linked.get(array.size()/2);
        }

        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("LinkedList time: "+time+"ns ("+millis+"ms)");


        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final var world = new HashMap<String, Long>();
        world.put("Africa", 1_110_635_000L);
        world.put("Americas", 972_005_000L);
        world.put("Antarctica", 0L);
        world.put("Asia", 4_298_723_000L);
        world.put("Europe", 742_452_000L);
        world.put("Oceania", 38_304_000L);

        /*
         * 8) Compute the population of the world
         */
        long worldPopulation = 0;
        for(final long population: world.values()) {
            worldPopulation += population;
        }
        System.out.println("World population is: "+worldPopulation);
    }
}
