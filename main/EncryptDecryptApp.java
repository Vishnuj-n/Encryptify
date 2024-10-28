package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
public class EncryptDecryptApp extends JFrame {
    private JButton encryptButton, decryptButton, fileButton, okButton, aboutButton;
    private JLabel fileLabel;
    private JFileChooser fileChooser;
    private File selectedFile;

    public EncryptDecryptApp() {
        setTitle("Encrypt/Decrypt Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Encrypt and Decrypt buttons
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        mainPanel.add(encryptButton, gbc);
        gbc.gridx = 1;
        mainPanel.add(decryptButton, gbc);

        // File button and label
        fileButton = new JButton("Choose File");
        fileLabel = new JLabel("No file selected");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(fileButton, gbc);
        gbc.gridy = 2;
        mainPanel.add(fileLabel, gbc);

        // OK button
        okButton = new JButton("OK");
        gbc.gridy = 3;
        mainPanel.add(okButton, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // About button
        aboutButton = new JButton("About");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(aboutButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initialize file chooser
        fileChooser = new JFileChooser();

        // Add action listeners
        fileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(EncryptDecryptApp.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    fileLabel.setText("Selected file: " + selectedFile.getName());
                }
            }
        });

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    JOptionPane.showMessageDialog(EncryptDecryptApp.this, 
                        "File accepted: " + selectedFile.getName());
                } else {
                    JOptionPane.showMessageDialog(EncryptDecryptApp.this, 
                        "Please select a file first.");
                }
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(EncryptDecryptApp.this, 
                    "Encrypt/Decrypt Application\nVersion 1.0\n© 2023 Your Company");
            }
        });

        // Placeholder action listeners for encrypt and decrypt buttons
        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(EncryptDecryptApp.this, "Encrypt functionality not implemented yet.");
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(EncryptDecryptApp.this, "Decrypt functionality not implemented yet.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EncryptDecryptApp().setVisible(true);
            }
        });
    }
}