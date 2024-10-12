import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class PasswordGenerator extends JFrame implements ActionListener {

    private JTextField fileNameField;
    private JTextField passwordField;
    private JButton generateButton;
    private JButton saveButton;
    private JButton clearButton; // New button for clearing fields
    private String generatedPassword;

    public PasswordGenerator() {
        // Set up the frame
        setTitle("Password Generator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2)); // Adjusted to fit 5 rows

        // Create components
        JLabel fileNameLabel = new JLabel("File Name:");
        fileNameField = new JTextField();
        
        JLabel passwordLabel = new JLabel("Generated Password:");
        passwordField = new JTextField();
        passwordField.setEditable(false);

        generateButton = new JButton("Generate Password");
        saveButton = new JButton("Save Password");
        clearButton = new JButton("Clear"); // Initialize the clear button

        // Set button sizes
        Dimension buttonSize = new Dimension(150, 25);
        generateButton.setPreferredSize(buttonSize);
        saveButton.setPreferredSize(buttonSize);
        clearButton.setPreferredSize(buttonSize);

        // Add action listeners
        generateButton.addActionListener(this);
        saveButton.addActionListener(this);
        clearButton.addActionListener(this); // Add action listener for clear button

        // Add components to the frame
        add(fileNameLabel);
        add(fileNameField);
        add(passwordLabel);
        add(passwordField);
        add(generateButton);
        add(saveButton);
        add(clearButton); // Add the clear button to the layout
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            generatedPassword = generatePassword(12); // Generate a 12-character password
            passwordField.setText(generatedPassword);
        } else if (e.getSource() == saveButton) {
            String fileName = fileNameField.getText().trim();
            if (!fileName.isEmpty() && generatedPassword != null) {
                savePasswordToFile(fileName, generatedPassword);
                JOptionPane.showMessageDialog(this, "Password saved successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid file name and generate a password.");
            }
        } else if (e.getSource() == clearButton) { // Handle the clear button action
            fileNameField.setText("");
            passwordField.setText("");
        }
    }

    private String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    private void savePasswordToFile(String fileName, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt"))) {
            writer.write(password);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving password: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PasswordGenerator generator = new PasswordGenerator();
            generator.setVisible(true);
        });
    }
}
