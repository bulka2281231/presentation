package com.game;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

class MainFrame extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    private CardLayout cardLayout;
    private AudioPlayer audioPlayer;

    public MainFrame(String nameApp) {
        super(nameApp);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        setLayout(cardLayout); 

        audioPlayer = new AudioPlayer();

        WindowComponent windowComponent = new WindowComponent(this, audioPlayer);
        LevelComponent levelComponent = new LevelComponent(this, audioPlayer);
        SettingComponent settingComponent = new SettingComponent(this, audioPlayer);
        EditorComponent editorComponent = new EditorComponent(this);

        audioPlayer.play("src/main/resources/music/music.wav");

        add(windowComponent, "Welcome");
        add(levelComponent, "Level");
        add(settingComponent, "Settings");
        add(editorComponent, "Editor");
        showMainMenu();
    }
    public void showLevelSelection() {
        LevelComponent levelComponent = (LevelComponent) getContentPane().getComponent(1);
        levelComponent.update();
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
