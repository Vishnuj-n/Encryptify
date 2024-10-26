import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCopyExample {
    public static void main(String[] args) {
        Path sourcePath = Paths.get("path/to/source/file.txt");
        Path destinationPath = Paths.get("path/to/destination/file.txt");
        if (Files.notExists(sourcePath)) {
            System.err.println("Source file does not exist: " + sourcePath);
            return;
        }

        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully to: " + destinationPath);
        } catch (IOException e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
    }
}