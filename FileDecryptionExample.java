import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDecryptionExample {

   
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "1234567890123456".getBytes(); 

    public static void main(String[] args) {
        String encryptedFilePath = "path/to/source/file.encrypted";
        File encryptedFile = new File(encryptedFilePath);

        
        String decryptedFilePath = encryptedFilePath.replace(".encrypted", "_decrypted.txt");
        File decryptedFile = new File(decryptedFilePath);

        try {
            decryptFile(encryptedFile, decryptedFile);
            System.out.println("File decrypted successfully! Saved as: " + decryptedFile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error decrypting file: " + e.getMessage());
        }
    }

    public static void decryptFile(File inputFile, File outputFile) throws Exception {
        
        SecretKeySpec secretKey = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

       
        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {
             
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            
            byte[] outputBytes = cipher.doFinal(inputBytes);

            
            outputStream.write(outputBytes);
        }
    }
}