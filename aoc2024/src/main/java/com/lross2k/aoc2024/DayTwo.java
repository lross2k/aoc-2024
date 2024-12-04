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
    
    public int firstPart() {
        // Read contents of file and separate into two different lists
        int safeReports = 0;
        boolean badReport = false;
        for (String line : lines) {
            String[] levels = line.split(" ");
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

                    if (difference == 0) {
                        badReport = true;
                        break;
                    } else if (difference > 3) {
                        badReport = true;
                        break;     
                    } else if (difference > 0 && difference <= 3 && !increasing) {
                        badReport = true;
                        break;
                    } else if (difference < 0 && difference >= -3 && increasing) {
                        badReport = true;
                        break;
                    } else if (difference < -3) {
                        badReport = true;
                        break;
                    }
                previousLevel = Integer.parseInt(level);
            }
            if (!badReport)
                safeReports++;
            badReport = false;
        }

        return(safeReports);
    }

    public void secondPart() {
        // Iniatilize the lists for both sides
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        HashMap<Integer, Integer> similarities = new HashMap<>();

        // Read contents of file and separate into two different lists
        for (String line : lines) {
            var string = line.split("   ");
            leftList.add(Integer.valueOf(string[0]));
            rightList.add(Integer.valueOf(string[1]));
        }

        // Add up the distances
        int result = 0;
        int similarityIncrease;
        int frequency;
        for (int i = 0; i < leftList.size(); i++) {
            int key = leftList.get(i);
            if (similarities.containsKey(key)) {
                similarityIncrease = key * similarities.get(key);
            } else {
                frequency = Collections.frequency(rightList, key);
                similarityIncrease = key * frequency;
                similarities.put(key, frequency);
            }
            result += similarityIncrease;
        }

        System.out.println(result);
    } 
}
