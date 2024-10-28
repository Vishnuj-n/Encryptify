package main;

import javax.swing.*;
import java.awt.event.*;

public class About extends JFrame {
    private JButton aboutButton;

    public About() {
        aboutButton = new JButton("About");
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Encrypt/Decrypt Application\nVersion 1.0", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public JButton getAboutButton() {
        return aboutButton;
    }
}
