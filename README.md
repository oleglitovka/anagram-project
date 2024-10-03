# Anagram Project

The Anagram Project is a command-line Java application that checks if two input texts are anagrams and finds all anagrams in a saved list. The application also saves anagrams to a file for future reference.

## Features

- Check if two texts are anagrams.
- Find all saved anagrams for a given input text.
- Save and load anagrams from a file.

## Usage

### Running the Application

To run the application, use the following commands:

```bash
# Navigate to the project's root directory
cd anagram-project

# Compile the project (if needed)
javac -d out/production/anagram-project src/main/java/com/litovka/anagram/AnagramProjectApplication.java

# Run the project
java -classpath out/production/anagram-project com.litovka.anagram.AnagramProjectApplication
```

### Example of Application in Use
Once the application starts, you will be prompted to choose an action:

- Check if two texts are anagrams.
- Find all anagrams for a given input from previous inputs.
- Exit the application.

#### Example input:
```
1
Enter first text: Listen
Enter second text: Silent
```

#### Output:
```
'Listen' and 'Silent' are anagrams.
```

#### Example input:
```
2
Enter the text to find its anagrams:
New York Times
```

#### Output:
```
Anagrams found: [New York Times, monkeys write]
```

## Running Tests
To run the tests, use the following commands:

### Compile the Tests
```bash
javac -d out/test/anagram-project src/test/java/com/litovka/anagram/cli/AnagramCLITest1.java
javac -d out/test/anagram-project src/test/java/com/litovka/anagram/cli/AnagramCLITest2.java
```
### Run the Tests
```bash
# Run AnagramCLITest1
java -classpath out/test/anagram-project com.litovka.anagram.cli.AnagramCLITest1

# Run AnagramCLITest2
java -classpath out/test/anagram-project com.litovka.anagram.cli.AnagramCLITest2
```
Note: This command will run the test with predefined input values to verify the application's functionality.

## Project Structure
- `src/main/java/com/litovka/anagram/`: Main directory with source code.
  - `cli/`: Classes for the command-line interface.
  - `service/`: Services for anagram checking and management.
  - `storage/`: Classes for saving and loading data.
- `src/test/java/com/litovka/anagram/`: Tests for verifying the functionality of the application.

## Dependencies
This project requires:
- Java 17 or higher.

## Installation
1. Clone the repository:
```bash
git clone https://github.com/oleglitovka/anagram-project.git
```
2. Navigate to the project directory:
```bash
cd anagram-project
```
3. Compile and run the project by following the instructions in the Running the Application section.
