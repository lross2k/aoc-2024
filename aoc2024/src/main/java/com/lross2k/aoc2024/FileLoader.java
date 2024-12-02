/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lross2k.aoc2024;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.List;

/**
 *
 * @author lross2k
 */
public class FileLoader {
    private final String resourceName;

    public FileLoader(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * Load the file content as a single string.
     *
     * @return The file content as a single string.
     * @throws IOException If an I/O error occurs.
     */
    public String loadAsString() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (is == null) {
                throw new IOException("Resource not found: " + resourceName);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    /**
     * Load the file content as a list of strings, one per line.
     *
     * @return A list of lines from the file.
     * @throws IOException If an I/O error occurs.
     */
    public List<String> loadAsLines() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (is == null) {
                throw new IOException("Resource not found: " + resourceName);
            }
            return new java.io.BufferedReader(
                new java.io.InputStreamReader(is, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.toList());
        }
    }
}
