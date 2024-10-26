import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileEncryptionExample {
    
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "1234567890123456".getBytes(); 

    public static void main(String[] args) {
        Path sourcePath = Paths.get("path/to/source/file.txt");
        Path encryptedPath = Paths.get("path/to/source/file.encrypted");

        try {
            encryptFile(sourcePath.toFile(), encryptedPath.toFile());
            System.out.println("File encrypted successfully!");
        } catch (Exception e) {
            System.err.println("Error encrypting file: " + e.getMessage());
        }
    }

    public static void encryptFile(File inputFile, File outputFile) throws Exception {
        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {
             
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            outputStream.write(outputBytes);
        }
    }
}