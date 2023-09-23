import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Maincipher {
    static Scanner sc = new Scanner(System.in);

    private static void Bruteforce(String message) {
        System.out.println("\nBrute Force Results:");
        for (int key = 1; key <= 26; key++) {
            String decryptedMessage = decrypt(message, key);
            System.out.printf("Key %2d: %s%n", key, decryptedMessage);
        }
    }

    private static String decrypt(String message, int key) {
        StringBuilder decryptedMessage = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char decryptedChar = (char) (c - key);
                if ((Character.isUpperCase(c) && decryptedChar < 'A') || (Character.isLowerCase(c) && decryptedChar < 'a')) {
                    decryptedChar += 26; // Wrap around the alphabet
                }
                decryptedMessage.append(decryptedChar);
            } else {
                decryptedMessage.append(c); // Non-letter characters remain unchanged
            }
        }

        return decryptedMessage.toString();
    }

    private static String encrypt(String message, int key) {
        StringBuilder encryptedMessage = new StringBuilder();
        boolean flag = true;

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char encryptedChar = (char) (c + key);
                if ((Character.isUpperCase(c) && encryptedChar > 'Z') || (Character.isLowerCase(c) && encryptedChar > 'z')) {
                    encryptedChar -= 26; // Wrap around the alphabet
                }
                encryptedMessage.append(encryptedChar);
            } else {
                encryptedMessage.append(c); // Non-letter characters remain unchanged
            }
        }

        return encryptedMessage.toString();
    }

    public static void main(String[] args) {
        int choice = 0, keys = 0;
        String message;
        System.out.printf("%70s", "Welcome to Rail Fence Cipher!");
        System.out.printf("\n\n%75s", "Please select one of the following options:");
        System.out.printf("\n\n%53s", "1 - Encrypt a message");
        System.out.printf("\n%53s", "2 - Decrypt a message");
        System.out.printf("\n%47s", "3 - Brute force");

        boolean valid = false;
        do {
            System.out.printf("\n\n%50s", "Enter your choice:");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) 
			{
				System.out.println(choice + "test");
                System.out.printf("%82s", "Invalid choice! Please enter a number between 1-3.");
            }

            if (choice >= 1 && choice <= 3) {
                valid = true;
            }
        } while (!valid);

        System.out.printf("%51s", "Enter your message:");
        message = sc.nextLine();

        if (choice == 3) {
            Bruteforce(message);
        } else {
            valid = false;
            do {
                System.out.printf("%60s", "Enter the key number (1-26):");
                try {
                    keys = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.printf("%82s", "Invalid choice! Please enter a number between 1-26.");
                }

                if (keys >= 1 && keys <= 26) {
                    valid = true;
                }
            } while (!valid);

            if (choice == 1) {
                String encryptedMessage = encrypt(message, keys);
                System.out.printf("\n%71s", "Your encrypted message is: " + encryptedMessage);
            } else if (choice == 2) {
                String decryptedMessage = decrypt(message, keys);
                System.out.printf("\n%73s", "Your decrypted message is: " + decryptedMessage);
            }
        }
    }
}
