package com.lross2k.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lross2k
 */
public class DayOne {
    private List<String> lines;

    public DayOne(String fileName) {
        
        try {    
            FileLoader fileLoader = new FileLoader(fileName);

            // Load as lines
            lines = fileLoader.loadAsLines();
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }
    
    public void firstPart() {
        // Iniatilize the lists for both sides
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        // Read contents of file and separate into two different lists
        for (String line : lines) {
            var string = line.split("   ");
            leftList.add(Integer.valueOf(string[0]));
            rightList.add(Integer.valueOf(string[1]));
        }

        // Lazyly sort both lists
        Collections.sort(leftList);
        Collections.sort(rightList);

        // Add up the distances
        int result = 0;
        int diff;
        for (int i = 0; i < leftList.size(); i++) {
            diff = leftList.get(i) - rightList.get(i);
            result += diff < 0 ? diff * -1 : diff;
        }

        System.out.println(result);
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
