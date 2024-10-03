package com.litovka.anagram.cli;

import com.litovka.anagram.service.AnagramService;
import com.litovka.anagram.storage.DataStorage;
import com.litovka.anagram.storage.FileDataStorage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AnagramCLITest1 {
    private static final String TEST_FILE_PATH = "data/anagram_data_test.txt";
    private static final String TEST_FRASE_1 = "A man and a girl";
    private static final String TEST_FRASE_2 = "Anna Madrigal";
    private static final String SIMULATED_INPUT = "1\n" + TEST_FRASE_1 +"\n" + TEST_FRASE_2 + "\n3\n";
    private static final String[] INITIAL_TEST_DATA = {
            "I am a weakish speller",
            "William Shakespeare",
            "I am Lord Voldemort",
            "Tom Marvolo Riddle"
    };

    public static void main(String[] args) {
        System.out.println("Starting test...");
        setupSimulatedInput(SIMULATED_INPUT);
        File testFile = setupTestFile();

        System.out.println("Contents of test file before running the test:");
        printFileContents(testFile);

        runCLITest();

        System.out.println("Contents of test file after running the test:");
        printFileContents(testFile);

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

    private static void runCLITest() {
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
        String outputExpected = "'" + TEST_FRASE_1 + "' and '" + TEST_FRASE_2 + "' are anagrams.";
        if (output.contains(outputExpected)) {
            System.out.println("\nTest passed! " + outputExpected);
        } else {
            System.out.println("Test failed! Output was:\n" + output);
        }
    }

    private static void printFileContents(File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Failed to read test file contents.");
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
