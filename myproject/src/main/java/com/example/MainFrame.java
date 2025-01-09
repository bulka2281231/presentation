package com.example;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;

class MainFrame extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    private CardLayout cardLayout;

    public MainFrame(String nameApp) {
        super(nameApp);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout(); // Инициализируем CardLayout
        setLayout(cardLayout); 

        WindowComponent windowComponent = new WindowComponent(this);
        LevelComponent levelComponent = new LevelComponent(this);

        add(windowComponent, "Welcome");
        add(levelComponent, "Level");

        showMainMenu();
    }
    public void showLevelSelection() {  // новое окно с игрой
        cardLayout.show(getContentPane(), "Level");
    }

    public void showStartGame() {
        cardLayout.show(getContentPane(), "Game");
    }

    public void showMainMenu() {
        cardLayout.show(getContentPane(), "Welcome!");
    }

    public void showSettings(){
        cardLayout.show(getContentPane(), "Settings");
        setPreferredSize(new Dimension(600, 500));
        pack();
        // System.out.printf("%d %d", getWidth(), getHeight());
        // SettingFrame settingFrame = new SettingFrame(this, "Игра 'Угадай картинку'");
        // settingFrame.setSize(WIDTH, HEIGHT);
        // settingFrame.setLocationRelativeTo(null);
    }
}

        // settingFrame.setSize(WIDTH, HEIGHT);
        // settingFrame.setLocationRelativeTo(null);
        // settingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // SettingComponent settingComponent = new SettingComponent();
        // settingFrame.add(settingComponent);

        // settingFrame.setVisible(true);