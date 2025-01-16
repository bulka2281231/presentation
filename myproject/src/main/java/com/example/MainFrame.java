package com.example;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Dimension;

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

        cardLayout = new CardLayout(); // Инициализируем CardLayout
        setLayout(cardLayout); 

        audioPlayer = new AudioPlayer();
        WindowComponent windowComponent = new WindowComponent(this, audioPlayer);
        audioPlayer.play("/home/karim/presentation/myproject/src/main/resources/music/music.wav");

        add(windowComponent, "Welcome");

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

    public void showSettings(){
        cardLayout.show(getContentPane(), "Settings");
        setPreferredSize(new Dimension(600, 500));
        pack();
    }
}
