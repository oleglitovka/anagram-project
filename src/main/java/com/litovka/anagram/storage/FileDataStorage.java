package com.litovka.anagram.storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDataStorage implements DataStorage {
    private final String filePath;
    private final List<String> strings;

    public FileDataStorage(String filePath) {
        this.filePath = filePath;
        this.strings = new ArrayList<>();
        loadData();
    }

    @Override
    public void addString(String input) {
        strings.add(input);
    }

    @Override
    public List<String> getAllStrings() {
        return new ArrayList<>(strings);
    }

    @Override
    public void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            System.out.println("Failed to load data. Starting with an empty list.");
        }
    }

    @Override
    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String str : strings) {
                writer.write(str);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save data.");
        }
    }
}

