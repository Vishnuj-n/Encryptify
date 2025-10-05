# Encryptify

Encryptify is a secure file encryption/decryption tool that uses the Advanced Encryption Standard (AES) with Galois/Counter Mode (GCM) for authenticated encryption. This Java-based application allows you to protect your sensitive files with strong encryption using a simple password.

## Features

- **AES-128 Encryption**: Secure encryption using 128-bit keys (derived from an 8-character password)
- **GCM Mode**: Provides both confidentiality and authenticity of data
- **Secure Password Handling**: Uses secure password input (characters not displayed)
- **Simple CLI Interface**: Easy-to-use command-line interface
- **File Integrity**: Ensures encrypted files haven't been tampered with
- **Cross-Platform**: Works on any system with Java installed

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven (for building from source)

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/encryptify.git
   cd encryptify/Encryptify
   ```

2. **Build the project** (optional):
   ```bash
   mvn clean package
   ```

## Usage

### Running the Application

1. **Run the application**:
   ```bash
   java -cp target/classes Main
   ```
   or if you built with Maven:
   ```bash
   java -jar target/encryptify-1.0.jar
   ```

### Encrypting a File

1. Select option `1` to encrypt a file
2. Enter the path to the file you want to encrypt
3. Enter a password (must be exactly 8 characters)
4. The encrypted file will be saved with a `.enc` extension

Example:
```
1. Encrypt a file
2. Decrypt a file
3. Exit
Enter your choice: 1
Enter the file path to encrypt: /path/to/your/file.txt
Enter password for encryption (8 characters): 
File encrypted and saved as /path/to/your/file.txt.enc
```

### Decrypting a File

1. Select option `2` to decrypt a file
2. Enter the path to the encrypted file (must have .enc extension)
3. Enter the same password used for encryption
4. The decrypted file will be saved without the `.enc` extension

Example:
```
1. Encrypt a file
2. Decrypt a file
3. Exit
Enter your choice: 2
Enter the file path to decrypt: /path/to/your/file.txt.enc
Enter password for decryption: 
File decrypted and saved as /path/to/your/file.txt
```

## Security Notes

- **Password Strength**: The password must be exactly 8 characters long. For better security, use a mix of uppercase, lowercase, numbers, and special characters.
- **File Handling**: Always keep backups of your original files before encryption.
- **Password Management**: Remember or securely store your password. If you lose it, you won't be able to decrypt your files.
- **File Extensions**: The application automatically adds `.enc` to encrypted files. Don't modify this extension.

## How It Works

1. **Encryption Process**:
   - User provides an 8-character password
   - Password is used to generate a 128-bit AES key (AES-128)
   - A random 12-byte IV (Initialization Vector) is generated
   - File is encrypted using AES-256 in GCM mode
   - IV is prepended to the encrypted file

2. **Decryption Process**:
   - Encrypted file is read (first 12 bytes are the IV)
   - User provides the same password
   - Password is used to regenerate the same AES key
   - File is decrypted using the IV from the file

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
