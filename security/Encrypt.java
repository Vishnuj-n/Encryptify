package security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Provides file encryption functionality using AES-128 in GCM mode.
 * This class handles the encryption of files with a user-provided password,
 * using secure cryptographic practices including random IV generation.
 * Note: Uses a 128-bit key derived from an 8-character password.
 */
public class Encrypt {
    // Secret key for encryption/decryption
    private SecretKey secretKey;
    // GCM parameter specification for encryption
    private GCMParameterSpec gcmSpec;
    // Initialization Vector for encryption
    private byte[] ivBytes;

    /**
     * Initializes a new Encrypt instance with the provided password.
     * The password must be exactly 8 characters long.
     * 
     * @param password The password to use for encryption (must be 8 characters)
     * @throws IllegalArgumentException if password length is not 8 characters
     * @throws Exception if there's an error initializing the encryption parameters
     */
    public Encrypt(String password) throws Exception {
        if (password.length() != 8) {
            throw new IllegalArgumentException("Password must be 8 characters long");
        }

        byte[] key = Arrays.copyOf(password.getBytes("UTF-8"), 16); // Ensure the key is 16 bytes long
        this.secretKey = new SecretKeySpec(key, "AES");
        // For GCM mode, use a 12-byte IV
        this.ivBytes = new byte[12];
        new SecureRandom().nextBytes(this.ivBytes);
        // GCM tag size is typically 128 bits (16 bytes)
        this.gcmSpec = new GCMParameterSpec(128, this.ivBytes);
    }

    /**
     * Encrypts the specified file using AES-256 in GCM mode.
     * The encrypted file will be saved with a .enc extension.
     * 
     * @param filePath The path to the file to be encrypted
     * @throws Exception if there's an error during file operations or encryption
     */
    public void encryptFile(String filePath) throws Exception {
        // Read the input file
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int) inputFile.length()];
        inputStream.read(inputBytes);

        // Initialize cipher in encryption mode with GCM
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmSpec);
        byte[] outputBytes = cipher.doFinal(inputBytes);

        // Write IV (needed for decryption) and encrypted data to output file
        FileOutputStream outputStream = new FileOutputStream(filePath + ".enc");
        outputStream.write(ivBytes);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        System.out.println("File encrypted and saved as " + filePath + ".enc");
    }
}

