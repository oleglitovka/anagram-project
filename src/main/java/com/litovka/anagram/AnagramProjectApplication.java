package com.litovka.anagram;


import com.litovka.anagram.cli.AnagramCLI;
import com.litovka.anagram.service.AnagramService;
import com.litovka.anagram.storage.DataStorage;
import com.litovka.anagram.storage.FileDataStorage;

public class AnagramProjectApplication {
    public static void main(String[] args) {
        DataStorage dataStorage = new FileDataStorage("data/anagram_data.txt");
        AnagramService anagramService = new AnagramService(dataStorage);
        AnagramCLI cli = new AnagramCLI(anagramService);
        cli.start();
    }
}
