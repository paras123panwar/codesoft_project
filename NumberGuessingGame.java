import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        final int MAX_ATTEMPTS = 7;
        final int MAX_NUMBER = 100;

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int score = 0;
        boolean playAgain = true;

        System.out.println("🎲 Welcome to the Number Guessing Game!");

        while (playAgain) {
            int numberToGuess = random.nextInt(MAX_NUMBER) + 1;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;

            System.out.println("\nI have chosen a number between 1 and " + MAX_NUMBER + ".");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts. Good luck!");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");

                // ✅ Input validation
                if (!scanner.hasNextInt()) {
                    System.out.println("⚠️ Please enter a valid number!");
                    scanner.next(); // clear invalid input
                    continue;
                }

                int userGuess = scanner.nextInt();

                if (userGuess == numberToGuess) {
                    System.out.println("🎉 Correct! You guessed the number!");
                    guessedCorrectly = true;
                    score += attemptsLeft;
                    break;
                } else if (userGuess > numberToGuess) {
                    System.out.println("📉 Too high! Try again.");
                } else {
                    System.out.println("📈 Too low! Try again.");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!guessedCorrectly) {
                System.out.println("❌ Out of attempts! The number was: " + numberToGuess);
            }

            System.out.println("🏆 Your current score: " + score);

            System.out.print("\nDo you want to play again? (yes/no): ");
            String choice = scanner.next().toLowerCase();
            playAgain = choice.equals("yes");
        }

        System.out.println("\nGame Over! Your final score is: " + score);
        scanner.close();
    }
}
