import java.security.SecureRandom;
import java.util.Scanner;

public class OneTimePadEncryptionAndDecrypt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the text to encrypt:");
        String plaintext = scanner.nextLine();
        
        // Generate a random key of the same length as the plaintext
        String key = generateRandomKey(plaintext.length());

        // Encrypt the plaintexts
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + ciphertext);

        // Decrypt the ciphertext
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    private static String generateRandomKey(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder key = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            key.append((char) random.nextInt(256)); // Generate a random character
        }
        return key.toString();
    }

    private static String encrypt(String plaintext, String key) {
        if (plaintext.length() != key.length()) {
            throw new IllegalArgumentException("Plaintext and key must have the same length.");
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(i);
            char encryptedChar = (char) (plainChar ^ keyChar);
            ciphertext.append(encryptedChar);
        }

        return ciphertext.toString();
    }

    private static String decrypt(String ciphertext, String key) {
        return encrypt(ciphertext, key); // XORing twice with the same key yields the original plaintext
    }
}
