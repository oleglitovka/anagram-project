package com.litovka.anagram.cli;

import com.litovka.anagram.service.AnagramService;
import com.litovka.anagram.storage.DataStorage;
import com.litovka.anagram.storage.FileDataStorage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class AnagramCLITest2 {
    private static final String TEST_FILE_PATH = "data/anagram_data_test.txt";
    private static final String TEST_FRASE = "I am a weakish speller";
    private static final String SIMULATED_INPUT = "2\n"+TEST_FRASE+"\n3\n";
    private static final String[] INITIAL_TEST_DATA = {
            "I am a weakish speller",
            "William Shakespeare",
            "I am Lord Voldemort",
            "Tom Marvolo Riddle"
    };

    public static void main(String[] args) {
        System.out.println("Starting anagram check test...");
        setupSimulatedInput(SIMULATED_INPUT);
        File testFile = setupTestFile();

        runAnagramCheckTest();

        deleteTestFile(testFile);
    }

    private static void setupSimulatedInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }

    private static File setupTestFile() {
        File testFile = new File(TEST_FILE_PATH);
        if (!testFile.exists()) {
            try {
                testFile.getParentFile().mkdirs();
                try (PrintWriter writer = new PrintWriter(new FileWriter(testFile))) {
                    for (String line : INITIAL_TEST_DATA) {
                        writer.println(line);
                    }
                }
            } catch (IOException e) {
                System.err.println("Failed to create test data file.");
                throw new RuntimeException(e);
            }
        }
        return testFile;
    }

    private static void runAnagramCheckTest() {
        DataStorage dataStorage = new FileDataStorage(TEST_FILE_PATH);
        AnagramService anagramService = new AnagramService(dataStorage);
        AnagramCLI cli = new AnagramCLI(anagramService);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        cli.start();

        System.out.flush();
        System.setOut(originalOut);

        String output = outputStream.toString(StandardCharsets.UTF_8);
        if (output.contains("William Shakespeare")) {
            System.out.println("\nTest passed! Anagram found: '" + TEST_FRASE + "' and  'William Shakespeare'");
        } else {
            System.out.println("Test failed! Output was:\n" + output);
        }
    }

    private static void deleteTestFile(File file) {
        if (file.exists() && !file.delete()) {
            System.out.println("Failed to delete test file.");
        } else {
            System.out.println("\nTest file deleted.");
        }
    }
}
