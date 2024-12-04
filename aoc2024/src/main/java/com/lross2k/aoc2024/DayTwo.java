/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lross2k.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author lross2k
 */
public class DayTwo {
    private List<String> lines;
    
    public DayTwo(String fileName) {
        
        try {    
            FileLoader fileLoader = new FileLoader(fileName);

            // Load as lines
            lines = fileLoader.loadAsLines();
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }

    private int isValidReport(String[] levels) {
        int previousLevel = Integer.parseInt(levels[0]);
        int secondLevel = Integer.parseInt(levels[1]);
        boolean increasing = true;
        int difference;

        // Determine if it starts increasing or decreasing
        if ((secondLevel - previousLevel) < 0 && (secondLevel - previousLevel) >= -3)
            increasing = false;

        for (int i = 1; i < levels.length; i++) {
            String level = levels[i];
            difference = Integer.parseInt(level) - previousLevel;

            if ((difference == 0) || 
                (difference > 3) || 
                (difference > 0 && difference <= 3 && !increasing) || 
                (difference < 0 && difference >= -3 && increasing) || 
                (difference < -3)) {
                return i;
            }
            previousLevel = Integer.parseInt(level);
        }
        return -1;
    }
    
    public int firstPart() {
        // Read contents of file and separate into two different lists
        int safeReports = 0;
        for (String line : lines) {
            String[] levels = line.split(" ");
            int reportValidness = isValidReport(levels);
            if (reportValidness < 0)
                safeReports++;
        }

        return(safeReports);
    }

    private boolean tryToDampen(String[] report, boolean tryAgain) {
        System.out.println(Arrays.toString(report));
        int validness = isValidReport(report);
        boolean initialResult = validness < 0;
        if (!initialResult && tryAgain)
            initialResult = tryToDampen(removeElement(report, validness), false);
        return initialResult;
    }

    private static String[] removeElement(String[] array, int indexToRemove) {
        if (indexToRemove < 0 || indexToRemove >= array.length) {
            throw new IllegalArgumentException("Invalid index");
        }

        String[] newArray = new String[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != indexToRemove) {
                newArray[j++] = array[i];
            }
        }
        return newArray;
    }

    public int secondPart() {
        // Read contents of file and separate into two different lists
        int safeReports = 0;
        for (String line : lines) {
            String[] levels = line.split(" ");
            int reportValidness = isValidReport(levels);
            if (reportValidness >= 0) {
                System.out.println(Arrays.toString(levels));
                boolean veredict = tryToDampen(removeElement(levels, reportValidness), false);
                System.out.println(veredict);
                if (veredict)
                    safeReports++;
            } else
                safeReports++;
        }

        return(safeReports);
    } 
}
