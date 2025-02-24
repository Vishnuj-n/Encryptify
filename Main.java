import java.util.Scanner;

import security.Decrypt;
import security.Encrypt;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Encrypt a file");
            System.out.println("2. Decrypt a file");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            try {
                if (choice.equals("1")) {
                    System.out.print("Enter the file path to encrypt: ");
                    String filePath = scanner.nextLine();
                    System.out.print("Enter password for encryption (8 charater): ");
                    String password = scanner.nextLine();
                    Encrypt encryptor = new Encrypt(password);
                    encryptor.encryptFile(filePath);
                } else if (choice.equals("2")) {
                    System.out.print("Enter the file path to decrypt: ");
                    String filePath = scanner.nextLine();
                    System.out.print("Enter password for decryption: ");
                    String password = scanner.nextLine();
                    Decrypt decryptor = new Decrypt(password);
                    decryptor.decryptFile(filePath);
                } else if (choice.equals("3")) {
                    break;
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
