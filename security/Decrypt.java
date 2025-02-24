package security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

public class Decrypt {
    private SecretKey secretKey;

    public Decrypt(String password) throws Exception {
        byte[] key = Arrays.copyOf(password.getBytes("UTF-8"), 16);
        this.secretKey = new SecretKeySpec(key, "AES");
    }

    public void decryptFile(String filePath) throws Exception {
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] ivBytes = new byte[16];
        inputStream.read(ivBytes);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        byte[] inputBytes = new byte[(int) inputFile.length() - 16];
        inputStream.read(inputBytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] outputBytes = cipher.doFinal(inputBytes);

        String outputFilePath = filePath.replace(".enc", "");
        FileOutputStream outputStream = new FileOutputStream(outputFilePath);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        System.out.println("File decrypted and saved as " + outputFilePath);
    }
}
