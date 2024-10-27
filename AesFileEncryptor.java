import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class AesFileEncryptor {

    private static final String PASSWORD = "@34sfgrvxs";
    private static final String SALT = "some_random_salt";  // A static salt or generate dynamically
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int KEY_LENGTH = 256; // AES key length

    public static void main(String[] args) {
        String inputFilePath = System.getProperty("user.home") + "/Downloads/123.pdf";
        String outputFilePath = System.getProperty("user.home") + "/Downloads/123.pdf.me";

        try {
            encryptFile(inputFilePath, outputFilePath, PASSWORD);
            System.out.println("Encryption completed: " + outputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void encryptFile(String inputFilePath, String outputFilePath, String password) throws Exception {
        // Generate AES key from password
        SecretKeySpec secretKey = getSecretKey(password);

        // Generate a random initialization vector (IV)
        byte[] iv = new byte[12]; // 12 bytes IV for GCM mode
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv); // 128-bit authentication tag length

        // Initialize Cipher for encryption
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmSpec);

        // Open file streams
        try (FileInputStream fis = new FileInputStream(new File(inputFilePath));
             FileOutputStream fos = new FileOutputStream(new File(outputFilePath));
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {

            byte[] buffer = new byte[1024];
            int read;
            while ((read = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, read);
            }
        }
    }

    private static SecretKeySpec getSecretKey(String password) throws Exception {
        byte[] salt = SALT.getBytes();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] key = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(key, "AES");
    }
}
