/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lross2k.aoc2024;

import java.io.IOException;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lross2k
 */
public class DayThree {
    private List<String> lines;
    
    public DayThree(String fileName) {
        
        try {    
            FileLoader fileLoader = new FileLoader(fileName);

            // Load as lines
            lines = fileLoader.loadAsLines();
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }
    
    private int processLine(String input) {
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        ArrayList<Integer> numbers = new ArrayList<>();
        int sum = 0;

        while (matcher.find()) {
            // Extract the captured numbers
            int num1 = Integer.parseInt(matcher.group(1));
            int num2 = Integer.parseInt(matcher.group(2));
            numbers.add(num1);
            numbers.add(num2);

            // Multiply the pair and add to the sum
            sum += num1 * num2;
        }
        return sum;
    }
    
    public int firstPart() {
        int total = 0;
        for (String line : lines) {
            total += processLine(line);
        }
        return total;
    }
}
