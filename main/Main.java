package main;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private About about;
    private FileButton fileButton;
    private EncryptionPanel encryptionPanel;
    private DecryptionPanel decryptionPanel;

    public Main() {
        setTitle("Encrypt/Decrypt Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Encryption panel
        encryptionPanel = new EncryptionPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(encryptionPanel, gbc);

        // Decryption panel
        decryptionPanel = new DecryptionPanel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(decryptionPanel, gbc);

        // File button
        fileButton = new FileButton();
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(fileButton.getFileButton(), gbc);

        // About button
        about = new About();
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(about.getAboutButton(), gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}