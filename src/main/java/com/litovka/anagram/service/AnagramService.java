package com.litovka.anagram.service;

import com.litovka.anagram.storage.DataStorage;

import java.util.List;
import java.util.stream.Collectors;

public class AnagramService {
    private final DataStorage dataStorage;

    public AnagramService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public boolean areAnagrams(String s1, String s2) {
        return isAnagram(s1, s2, true);
    }

    public List<String> findAllAnagrams(String input) {
        return dataStorage.getAllStrings().stream()
                .filter(storedStr -> isAnagram(storedStr, input, false))
                .collect(Collectors.toList());
    }

    private boolean isAnagram(String s1, String s2, boolean isEqual) {
        String normalizedS1 = normalizeString(s1);
        String normalizedS2 = normalizeString(s2);

        if (isEqual && normalizedS1.equals(normalizedS2)) {
            return false;
        }
        return sortString(normalizedS1).equals(sortString(normalizedS2));
    }

    public void storeAnagrams(String text1, String text2) {
        addInput(text1);
        addInput(text2);
    }

    private void addInput(String input) {
        String normalizedInput = normalizeString(input);

        if (isInputInStorage(normalizedInput)) {
            return;
        }

        dataStorage.addString(input);
        dataStorage.saveData();
    }

    private boolean isInputInStorage(String normalizedInput) {
        return dataStorage.getAllStrings().stream()
                .map(this::normalizeString)
                .anyMatch(str -> str.equals(normalizedInput));
    }

    private String normalizeString(String input) {
        return input.toLowerCase().replaceAll("[^\\p{L}\\p{N}]", "");
    }

    private String sortString(String s) {
        char[] charArray = s.toCharArray();
        java.util.Arrays.sort(charArray);
        return new String(charArray);
    }
}
