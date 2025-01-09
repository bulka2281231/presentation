package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

class SettingFrame extends JFrame {
    private String IMAGEPATH = "src/main/resources/images/";
    SettingFrame(String name) {
        super(name);

        setTitle("Настройки громкости");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Используем GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Заполнение по горизонтали

        // Настройка компонентов
        JLabel volumeLabel = new JLabel("Громкость:");
        JSlider volumeSlider = new JSlider(0, 100, 50);
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setPreferredSize(new Dimension(300, 50));

        List<String> optionsList = JsonDataHandler.getLevel_diff();
        JComboBox<String> comboBox = new JComboBox<>(optionsList.toArray(new String[0]));
        comboBox.setEditable(true);

        JLabel textFieldLabel1 = new JLabel("Поле для ответа:");
        JTextField textField1 = new JTextField(15);

        JLabel textFieldLabel2 = new JLabel("Поле для подсказки:");
        JTextField textField2 = new JTextField(15);

        // Поля для ввода пути к файлам
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

        JButton readButton = new JButton("Добавить");
        readButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(volumeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(volumeSlider, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(textFieldLabel1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(textField1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(textFieldLabel2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(textField2, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(filePathLabel1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(filePathField1, gbc);
        gbc.gridx = 2; // Позиция для кнопки
        add(fileChooserButton1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(filePathLabel2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(filePathField2, gbc);
        gbc.gridx = 2; // Позиция для кнопки
        add(fileChooserButton2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(filePathLabel3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(filePathField3, gbc);
        gbc.gridx = 2; // Позиция для кнопки
        add(fileChooserButton3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(comboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(readButton, gbc);

        // Добавление обработчика событий для кнопки
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

        // Установка видимости окна
        pack(); // Упаковываем компоненты
        setVisible(true);
    }
    private String[] copyFile(String[] sourcePath){
        boolean flag = true;
        String[] result_paths = new String[3];
        for(String file: sourcePath){
            File sourceFile = new File(file);
            if(!(sourceFile.exists() && sourceFile.isFile() && sourceFile.getName().endsWith(".png"))) flag = false;
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
            textField.setText(selectedFile.getAbsolutePath()); // Устанавливаем путь в текстовое поле
        }
    }
}