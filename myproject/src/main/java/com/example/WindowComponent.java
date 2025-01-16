package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WindowComponent extends JComponent {
    private MainFrame mainFrame;
    private JButton startGameButton;
    private JButton settingButton;

    public WindowComponent(MainFrame frame, AudioPlayer audioPlayer) {
        this.mainFrame = frame;
        setLayout(new BorderLayout());  // менеджер компановки для компонента MainComponent для размещения компонента внутри контейнера

        // // приветствие
        // JLabel welcomeLabel = new JLabel("Добро пожаловать в игру 'Угадай картинку' !", SwingConstants.CENTER);  // создание надписи
        // welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18 ));  // установка шрифта

        JLabel welcomeLabel = new JLabel();

        ImagesComponent welcomeImage = new ImagesComponent("src/main/resources/images/fon.png");  // здесь использую Component, так как мне нужно изменять разрешение.

        JPanel welcomePanel = new JPanel();  // создается панель (нужна для группировки элементов в контейнере)
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);
        welcomePanel.add(welcomeImage, BorderLayout.CENTER);

        // кнопка для начала игры
        startGameButton = new JButton("Начать игру");
        startGameButton.setFont(new Font("Arial", Font.BOLD, 14));
        startGameButton.addActionListener(new ActionListener() {  // слушатель для обработки действия пользователя, если кнопка нажата.
            @Override
            public void actionPerformed(ActionEvent e) {
                LevelComponent levelComponent = new LevelComponent(mainFrame, audioPlayer);
                mainFrame.add(levelComponent, "Level");
                mainFrame.showLevelSelection();  // если нажата клавиша начать игру, то создается новое окно с игрой.
            }
        });

        // кнопка для настроек
        settingButton = new JButton("Настройки");
        settingButton.setFont(new Font("Arial", Font.BOLD, 14));
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingComponent settingComponent = new SettingComponent(mainFrame, audioPlayer);
                mainFrame.add(settingComponent, "Settings");
                mainFrame.showSettings();
            }
        });


        welcomePanel.add(settingButton, BorderLayout.NORTH);
        welcomePanel.add(startGameButton, BorderLayout.SOUTH);

        add(welcomePanel, BorderLayout.CENTER);  // добавляю кнопку в контейнер.
    }
}