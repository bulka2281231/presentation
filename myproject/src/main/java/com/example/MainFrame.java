package com.example;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Dimension;

class MainFrame extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    private CardLayout cardLayout;

    public MainFrame(String nameApp) {
        super(nameApp);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        setLayout(cardLayout); 

        WindowComponent windowComponent = new WindowComponent(this);
        LevelComponent levelComponent = new LevelComponent(this);
        SettingComponent settingComponent = new SettingComponent(this);
        EditorComponent editorComponent = new EditorComponent(this);

        add(windowComponent, "Welcome");
        add(levelComponent, "Level");
        add(settingComponent, "Settings");
        add(editorComponent, "Editor");
        showMainMenu();
    }
    public void showLevelSelection() {
        cardLayout.show(getContentPane(), "Level");
    }

    public void showStartGame() {
        cardLayout.show(getContentPane(), "Game");
    }

    public void showMainMenu() {
        cardLayout.show(getContentPane(), "Welcome");
    }

    public void showEditor(){
        cardLayout.show(getContentPane(), "Editor");
    }
    public void showSettings(){
        cardLayout.show(getContentPane(), "Settings");
        setPreferredSize(new Dimension(600, 500));
        pack();
    }
}
