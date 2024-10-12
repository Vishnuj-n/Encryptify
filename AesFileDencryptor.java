import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class AesFileDencryptor {

    private static final String PASSWORD = "@34sfgrvxs";
    private static final String SALT = "some_random_salt";  // A static salt or generate dynamically
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int KEY_LENGTH = 256; // AES key length

    public static void main(String[] args) {
        String inputFilePath = System.getProperty("user.home") + "/Downloads/123.pdf.me";
        String outputFilePath = System.getProperty("user.home") + "/Downloads/passbook_decrypted.pdf";

        try {
            decryptFile(inputFilePath, outputFilePath, PASSWORD);
            System.out.println("Decryption completed: " + outputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decryptFile(String inputFilePath, String outputFilePath, String password) throws Exception {
        // Generate AES key from password
        SecretKeySpec secretKey = getSecretKey(password);

        // Generate the same initialization vector (IV) used during encryption
        byte[] iv = new byte[16]; // 16 bytes IV for AES (128 bits block size)
        Arrays.fill(iv, (byte) 0);  // Using the same IV as in the encryption
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Initialize Cipher for decryption
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // Open file streams
        try (FileInputStream fis = new FileInputStream(new File(inputFilePath));
             FileOutputStream fos = new FileOutputStream(new File(outputFilePath));
             CipherInputStream cis = new CipherInputStream(fis, cipher)) {

            byte[] buffer = new byte[1024];
            int read;
            while ((read = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
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
