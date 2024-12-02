/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.lross2k.aoc2024;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author lross2k
 */
public class Aoc2024 {

    public static void main(String[] args) {
         try {
            FileLoader fileLoader = new FileLoader("puzzle-example-1.txt");

            // Load as a single string
            String content = fileLoader.loadAsString();
            System.out.println("File content as a string:");
            System.out.println(content);

            // Load as lines
            List<String> lines = fileLoader.loadAsLines();
            System.out.println("\nFile content as lines:");
            lines.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }
}
