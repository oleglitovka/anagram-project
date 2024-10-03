package com.litovka.anagram.storage;

import java.util.List;

public interface DataStorage {
    void addString(String input);
    List<String> getAllStrings();
    void loadData();
    void saveData();
}

