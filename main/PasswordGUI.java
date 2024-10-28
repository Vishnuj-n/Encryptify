package main;

import security.PasswordGenerator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordGUI extends JFrame implements ActionListener {

    private JTextField fileNameField;
    private JPasswordField passwordField; // Changed to JPasswordField
    private JButton generateButton;
    private JButton saveButton;
    private JButton clearButton;
    private PasswordGenerator passwordGeneration;

    public PasswordGUI() {
        passwordGeneration = new PasswordGenerator();

        setTitle("Password Generator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        fileNameField = new JTextField();
        passwordField = new JPasswordField();
        generateButton = new JButton("Generate");
        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");

        add(new JLabel("File Name:"));
        add(fileNameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(generateButton);
        add(saveButton);
        add(clearButton);

        generateButton.addActionListener(this);
        saveButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            String password = passwordGeneration.generatePassword();
            passwordField.setText(password);
        } else if (e.getSource() == saveButton) {
            String fileName = fileNameField.getText();
            String password = new String(passwordField.getPassword());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(password);
                JOptionPane.showMessageDialog(this, "Password saved to " + fileName);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving password: " + ex.getMessage());
            }
        } else if (e.getSource() == clearButton) {
            fileNameField.setText("");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PasswordGUI gui = new PasswordGUI();
            gui.setVisible(true);
        });
    }
}