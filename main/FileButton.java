package main;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class FileButton {
    private JButton fileButton;
    private JFileChooser fileChooser;
    private File selectedFile;

    public FileButton() {
        fileButton = new JButton("Select File");
        fileChooser = new JFileChooser();

        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Selected file: " + selectedFile.getAbsolutePath());
                }
            }
        });
    }

    public JButton getFileButton() {
        return fileButton;
    }

    public File getSelectedFile() {
        return selectedFile;
    }
}