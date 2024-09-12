import java.util.Scanner;

public class HangmanGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = { "java", "hangman", "programming", "developer", "challenge" };
        String[] hints = { "Programming Language", "Game", "Coding Activity", "Job Title", "Task" };

        boolean playAgain = true;

        while (playAgain) {
            int randomIndex = (int) (Math.random() * words.length);
            String selectedWord = words[randomIndex];
            String selectedHint = hints[randomIndex];
            char[] guessedWord = new char[selectedWord.length()];
            int remainingAttempts = 6;
            String guessedLetters = "";

            for (int i = 0; i < guessedWord.length; i++) {
                guessedWord[i] = '_';
            }

            System.out.println("Welcome to Hangman!");
            System.out.println("Hint: " + selectedHint);

            while (remainingAttempts > 0 && new String(guessedWord).contains("_")) {
                System.out.println("Word: " + String.valueOf(guessedWord));
                System.out.println("Guessed Letters: " + guessedLetters);
                System.out.println("Attempts remaining: " + remainingAttempts);
                System.out.print("Enter a letter: ");

                char guess = scanner.nextLine().toLowerCase().charAt(0);

                if (guessedLetters.indexOf(guess) != -1) {
                    System.out.println("You already guessed that letter.");
                    continue;
                }

                guessedLetters += guess;

                if (selectedWord.indexOf(guess) != -1) {
                    System.out.println("Good guess!");
                    for (int i = 0; i < selectedWord.length(); i++) {
                        if (selectedWord.charAt(i) == guess) {
                            guessedWord[i] = guess;
                        }
                    }
                } else {
                    System.out.println("Wrong guess!");
                    remainingAttempts--;
                }

                // Reveal a letter option (optional)
                if (remainingAttempts > 1) {
                    System.out.print("Would you like to reveal a letter? (y/n): ");
                    char revealLetter = scanner.nextLine().toLowerCase().charAt(0);
                    if (revealLetter == 'y') {
                        for (int i = 0; i < selectedWord.length(); i++) {
                            if (guessedWord[i] == '_') {
                                guessedWord[i] = selectedWord.charAt(i);
                                remainingAttempts--; // Decrease attempts as a penalty
                                break;
                            }
                        }
                    }
                }
            }

            if (new String(guessedWord).equals(selectedWord)) {
                System.out.println("Congratulations! You guessed the word: " + selectedWord);
            } else {
                System.out.println("Sorry, you ran out of attempts. The word was: " + selectedWord);
            }

            System.out.print("Would you like to play again? (y/n): ");
            playAgain = scanner.nextLine().toLowerCase().charAt(0) == 'y';
        }
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
