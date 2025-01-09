package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WindowComponent extends JComponent {
    private MainFrame mainFrame;
    private JButton startGameButton;
    private JButton settingButton;

    public WindowComponent(MainFrame frame) {
        this.mainFrame = frame;
        setLayout(new BorderLayout());

        // приветствие
        JLabel welcomeLabel = new JLabel("Добро пожаловать в игру 'Угадай картинку' !", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        ImagesComponent welcomeImage = new ImagesComponent("src/main/resources/images/fon.png");

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);
        welcomePanel.add(welcomeImage, BorderLayout.CENTER);

        // Создаем панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Устанавливаем FlowLayout с выравниванием по центру

        // кнопка для начала игры
        startGameButton = new JButton("Начать игру");
        startGameButton.setFont(new Font("Arial", Font.BOLD, 14));
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.startGame();
            }
        });

        // кнопка для настроек
        settingButton = new JButton("Настройки");
        settingButton.setFont(new Font("Arial", Font.BOLD, 14));
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.openSetting();
            }
        });

        // Добавляем кнопки в панель
        buttonPanel.add(startGameButton);
        buttonPanel.add(settingButton);

        // Добавляем панель с кнопками в нижнюю часть welcomePanel
        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);
        add(welcomePanel, BorderLayout.CENTER);
    }
}
