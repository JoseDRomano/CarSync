package tools; /**
 * How to use:
 * 1. Import the class: import tools.Encryptor;
 * 2. Call the method: tools.Encryptor.encryptSHA256(String input);
 * 2.1 The method returns a String with the encrypted input.
 * 3. Use this method to encrypt passwords before storing them in the database.
 * 4.Done!
 */

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
    // Method to encrypt a string using SHA-256
    public static String encryptSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
        
        // Convert the byte array to a hexadecimal representation
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = String.format("%02x", b);
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /* Test
    public static void main(String[] args) {
        try {
            String originalString = "Hello, world!";
            String encryptedString = encryptSHA256(originalString);
            String test = encryptSHA256("Hello, world!");
            
            System.out.println("Original String: " + originalString);
            System.out.println("SHA-256 Encrypted String: " + encryptedString);
            System.out.println(test.equals(encryptedString));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not available.");
            e.printStackTrace();
        }
    }
    */
}
