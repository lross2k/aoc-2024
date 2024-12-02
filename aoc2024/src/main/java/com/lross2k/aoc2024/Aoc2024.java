/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.lross2k.aoc2024;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

/**
 *
 * @author lross2k
 */
public class Aoc2024 {

    public static void main(String[] args) {
         try {
            FileLoader fileLoader = new FileLoader("puzzle-input-1.txt");

            // Load as a single string
            /*
            String content = fileLoader.loadAsString();
            System.out.println("File content as a string:");
            System.out.println(content);
            */

            // Load as lines
            List<String> lines = fileLoader.loadAsLines();
            //System.out.println("\nFile content as lines:");
            //lines.forEach(System.out::println);
            
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
            int diff = 0;
            for (int i = 0; i < leftList.size(); i++) {
                diff = leftList.get(i) - rightList.get(i);
                if (diff < 0)
                    diff *= -1;
                result += diff;
            }
            
            System.out.println(result);

        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }
}
