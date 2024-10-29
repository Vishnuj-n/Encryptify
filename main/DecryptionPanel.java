package main;

import javax.swing.*;
import java.awt.*;

public class DecryptionPanel extends JPanel {
    private JButton decryptButton;

    public DecryptionPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        decryptButton = new JButton("Decrypt");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(decryptButton, gbc);
    }

    public JButton getDecryptButton() {
        return decryptButton;
    }
}