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

public class Encrypt {
    private SecretKey secretKey;
    private GCMParameterSpec gcmSpec;
    private byte[] ivBytes;

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

    public void encryptFile(String filePath) throws Exception {
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int) inputFile.length()];
        inputStream.read(inputBytes);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmSpec);
        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream outputStream = new FileOutputStream(filePath + ".enc");
        outputStream.write(ivBytes);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        System.out.println("File encrypted and saved as " + filePath + ".enc");
    }
}

