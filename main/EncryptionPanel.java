package main;

import javax.swing.*;
import java.awt.*;

public class EncryptionPanel extends JPanel {
    private JButton encryptButton;

    public EncryptionPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        encryptButton = new JButton("Encrypt");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(encryptButton, gbc);
    }

    public JButton getEncryptButton() {
        return encryptButton;
    }
}