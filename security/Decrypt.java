package security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * Provides file decryption functionality using AES-256 in GCM mode.
 * This class handles the decryption of files that were previously encrypted
 * by the Encrypt class, using the same password that was used for encryption.
 */
public class Decrypt {
    // Secret key for decryption
    private SecretKey secretKey;

    /**
     * Initializes a new Decrypt instance with the provided password.
     * 
     * @param password The password that was used for encryption (must be 8 characters)
     * @throws Exception if there's an error initializing the decryption parameters
     */
    public Decrypt(String password) throws Exception {
        byte[] key = Arrays.copyOf(password.getBytes("UTF-8"), 16);
        this.secretKey = new SecretKeySpec(key, "AES");
    }

    /**
     * Decrypts the specified encrypted file using AES-256 in GCM mode.
     * The decrypted file will be saved with the original filename (without .enc extension).
     * 
     * @param filePath The path to the encrypted file (should have .enc extension)
     * @throws Exception if there's an error during file operations or decryption
     */
    public void decryptFile(String filePath) throws Exception {
        // Read the IV from the beginning of the encrypted file
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);
        // GCM recommends 12-byte IV
        byte[] ivBytes = new byte[12];
        inputStream.read(ivBytes);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, ivBytes);

        byte[] inputBytes = new byte[(int) inputFile.length() - 12];
        inputStream.read(inputBytes);

        // Initialize cipher in decryption mode with GCM
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmSpec);
        byte[] outputBytes = cipher.doFinal(inputBytes);

        // Remove .enc extension from output filename
        String outputFilePath = filePath.replace(".enc", "");
        FileOutputStream outputStream = new FileOutputStream(outputFilePath);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        System.out.println("File decrypted and saved as " + outputFilePath);
    }
}
