/**
 * Main class for the Encryptify application.
 * Provides a command-line interface for file encryption and decryption.
 * Users can encrypt files with a password and later decrypt them using the same password.
 */
public class Main {
    /**
     * Main entry point for the Encryptify application.
     * Presents a menu to the user for file encryption/decryption operations.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        

        while (true) {
            System.out.println("1. Encrypt a file");
            System.out.println("2. Decrypt a file");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            try {
                // Handle file encryption
            if (choice.equals("1")) {
                    System.out.print("Enter the file path to encrypt: ");
                    String filePath = scanner.nextLine();
                    char[] passwordArray = console.readPassword("Enter password for encryption (8 characters): ");
                    String password = new String(passwordArray);
                    Encrypt encryptor = new Encrypt(password);
                    encryptor.encryptFile(filePath);
                // Handle file decryption
            } else if (choice.equals("2")) {
                    System.out.print("Enter the file path to decrypt: ");
                    String filePath = scanner.nextLine();
                    char[] passwordArray = console.readPassword("Enter password for decryption: ");
                    String password = new String(passwordArray);
                    Decrypt decryptor = new Decrypt(password);
                    decryptor.decryptFile(filePath);
                // Exit the application
            } else if (choice.equals("3")) {
                    break;
                // Handle invalid input
            } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}