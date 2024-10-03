package com.litovka.anagram.cli;

import com.litovka.anagram.service.AnagramService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AnagramCLI {
    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Please enter a number (1, 2, or 3).";
    private final AnagramService anagramService;
    private final Scanner scanner;

    public AnagramCLI(AnagramService anagramService) {
        this.anagramService = anagramService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                        handleAnagramCheck();
                    break;
                case 2:
                    handleFindAnagrams();
                    break;
                case 3:
                    exitProgram();
                    return;
                default:
                    System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
    }

    private void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Check if two texts are anagrams.");
        System.out.println("2. Get all anagrams for a given input from previous inputs.");
        System.out.println("3. Exit");
    }

    private int getUserChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

    private void    handleAnagramCheck() {
        String text1 = getInput("Enter first text:");
        String text2 = getInput("Enter second text:");

        if (anagramService.areAnagrams(text1, text2)) {
            System.out.println("'" + text1 + "' and '" + text2 + "' are anagrams.");
            anagramService.storeAnagrams(text1, text2);
        } else {
            System.out.println("'" + text1 + "' and '" + text2 + "' are not anagrams.");
        }
    }

    private void handleFindAnagrams() {
        String input = getInput("Enter the text to find its anagrams:");
        List<String> anagrams = anagramService.findAllAnagrams(input);

        if (anagrams.isEmpty()) {
            System.out.println("No anagram for '" + input + "' found.");
        } else {
            System.out.println("Anagrams found: " + anagrams);
        }
    }

    private String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    private void exitProgram() {
        System.out.println("Exiting...");
        scanner.close();
    }
}
