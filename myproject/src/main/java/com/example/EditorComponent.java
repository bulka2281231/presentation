package com.example;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class EditorComponent extends JComponent{
    private String IMAGEPATH = "src/main/resources/images/";
    private JTabbedPane tabbedPane;
    private MainFrame mainFrame;
    EditorComponent(MainFrame frame){
        mainFrame = frame;
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        JPanel addPanel = createAddPanel();
        tabbedPane.addTab("Добавить", addPanel);
        JPanel removePanel = createRemovePanel();
        tabbedPane.addTab("Удалить", removePanel);

        add(tabbedPane, BorderLayout.CENTER);
    }
    private JPanel createAddPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        JLabel textFieldLabel1 = new JLabel("Поле для ответа:");
        JTextField textField1 = new JTextField(15);
    
        JLabel textFieldLabel2 = new JLabel("Поле для подсказки:");
        JTextField textField2 = new JTextField(15);
    
        JLabel filePathLabel1 = new JLabel("Путь к оригинальному изображению:");
        JTextField filePathField1 = new JTextField(15);
        JButton fileChooserButton1 = new JButton("Выбрать");
        fileChooserButton1.addActionListener(e -> openFileChooser(filePathField1));
    
        JLabel filePathLabel2 = new JLabel("Путь к заблюренному изображению:");
        JTextField filePathField2 = new JTextField(15);
        JButton fileChooserButton2 = new JButton("Выбрать");
        fileChooserButton2.addActionListener(e -> openFileChooser(filePathField2));
        
        JLabel filePathLabel3 = new JLabel("Путь к основному изображению:");
        JTextField filePathField3 = new JTextField(15);
        JButton fileChooserButton3 = new JButton("Выбрать");
        fileChooserButton3.addActionListener(e -> openFileChooser(filePathField3));
    
        List<String> optionsList = JsonDataHandler.getLevel_diff();
        JComboBox<String> comboBox = new JComboBox<>(optionsList.toArray(new String[0]));
        comboBox.setEditable(true);

        JButton readButton = new JButton("Добавить");
        readButton.setFont(new Font("Arial", Font.BOLD, 14));
        readButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Считываем данные из полей
                String answer = textField1.getText();
                String hint = textField2.getText();
                String difficult = (String)comboBox.getSelectedItem();
                String[] paths = {filePathField1.getText(), filePathField2.getText(), filePathField3.getText()};                
                paths = copyFile(paths);
                for(int i = 0; i < 3; i++){
                    System.out.println(paths[i]);
                }
                if(paths[2] != null){
                    
                    Level newLevel = new Level();
                    newLevel.setDifficult(difficult);
                    newLevel.setName(answer);
                    newLevel.setText_hint(hint);
                    newLevel.setOrig_image(paths[0]);
                    newLevel.setBlur_image(paths[1]);
                    newLevel.setIllustrative_image(paths[2]);
                    JsonDataHandler.writeData(newLevel);
                }
                textField1.setText("");
                textField2.setText("");
                filePathField1.setText("");
                filePathField2.setText("");
                filePathField3.setText("");
            }
        });

        JButton backButton = new JButton("Назад");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showMainMenu();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(textFieldLabel1, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(textField1, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(textFieldLabel2, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(textField2, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(filePathLabel1, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(filePathField1, gbc);
        gbc.gridx = 2; 
        panel.add(fileChooserButton1, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(filePathLabel2, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(filePathField2, gbc);
        gbc.gridx = 2;
        panel.add(fileChooserButton2, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(filePathLabel3, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(filePathField3, gbc);
        gbc.gridx = 2;
        panel.add(fileChooserButton3, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(comboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(readButton, gbc);
        
        gbc.gridx = 1;
        panel.add(backButton, gbc);
        return panel;
    }
    private JPanel createRemovePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
        // Панель для ComboBox и изображения
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
    
        JLabel removeLabel = new JLabel("Выберите уровень для удаления:");
        JComboBox<String> removeComboBox = new JComboBox<>();
    
        updateRemoveComboBox(removeComboBox);
    
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(550, 300));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
    
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout());
    
        JButton refreshButton = new JButton("Обновить");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRemoveComboBox(removeComboBox);
            }
        });
    
        comboPanel.add(removeComboBox);
        comboPanel.add(refreshButton);
    
        topPanel.add(removeLabel);
        topPanel.add(comboPanel);
        topPanel.add(imageLabel);
    
        panel.add(topPanel);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
    
        JButton removeButton = new JButton("Удалить");
        removeButton.setFont(new Font("Arial", Font.BOLD, 14));
    
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) removeComboBox.getSelectedItem();
                if (selectedItem != null) {
                    removeComboBox.removeItem(selectedItem);
                    Level deleteLevel = null;
                    for (String diff : JsonDataHandler.getLevel_diff()) {
                        List<Level> levels = JsonDataHandler.getLevels(diff);
                        for (Level level : levels) {
                            if (level.getName().equals(selectedItem)) deleteLevel = level;
                        }
                    }
                    JsonDataHandler.deleteLevel(deleteLevel);
                    JOptionPane.showMessageDialog(mainFrame, "Элемент удален: " + selectedItem);
                }
            }
        });
    
        JButton backButton = new JButton("Назад");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showMainMenu();
            }
        });
    
        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);
    
        panel.add(buttonPanel);
    
        removeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) removeComboBox.getSelectedItem();
                String imagePath = getPathFromName(selectedItem);
                if (imagePath != null) {
                    ImageIcon imageIcon = new ImageIcon(imagePath);
                    imageLabel.setIcon(imageIcon);
                } else {
                    imageLabel.setIcon(null);
                }
            }
        });
    
        return panel;
    }
    
    private void updateRemoveComboBox(JComboBox<String> removeComboBox) {
        List<String> names = new ArrayList<>();
        List<String> diffs = JsonDataHandler.getLevel_diff();
        for (String diff : diffs) {
            List<Level> levels = JsonDataHandler.getLevels(diff);
            for (Level level : levels) {
                names.add(level.getName());
            }
        }
        removeComboBox.setModel(new DefaultComboBoxModel<>(names.toArray(new String[0])));
    }
    
    

     private String[] copyFile(String[] sourcePath){
        boolean flag = true;
        String[] result_paths = new String[3];
        for(String file: sourcePath){
            File sourceFile = new File(file);
            if(!(sourceFile.exists() && sourceFile.isFile() && (sourceFile.getName().endsWith(".png") || sourceFile.getName().endsWith(".jpeg") || sourceFile.getName().endsWith(".jpg")))) flag = false;
        }
        if(flag){
        try {
            for(int i = 0; i < 3; i ++){
                File sourceFile = new File(sourcePath[i]);
                Path destinationPath = Path.of(IMAGEPATH, sourceFile.getName());
                    Files.copy(sourceFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                result_paths[i] = destinationPath.toString();
            }
        }
        catch (Exception ex){
        }
        }
        return result_paths;
    }
    private void openFileChooser(JTextField textField) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            textField.setText(selectedFile.getAbsolutePath());
        }
    }
    private String getPathFromName(String name){
        List<String> diffs = JsonDataHandler.getLevel_diff();
        for(String diff: diffs){
            List<Level> levels = JsonDataHandler.getLevels(diff);
            for(Level level: levels){
                if(level.getName().equals(name)) return level.getOrig_image();
            }
        }
        return new String();

    }
}
