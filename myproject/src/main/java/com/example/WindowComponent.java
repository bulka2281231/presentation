package com.example;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class WindowComponent extends JComponent {
    private MainFrame mainFrame;
    private JButton startGameButton;
    private JButton settingButton;
    private JButton editorButton;

    public WindowComponent(MainFrame frame, AudioPlayer audioPlayer) {
        this.mainFrame = frame;
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Добро пожаловать в игру 'Угадай картинку' !", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18 ));

        ImagesComponent welcomeImage = new ImagesComponent("src/main/resources/images/fon.png"); 

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);
        welcomePanel.add(welcomeImage, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        startGameButton = new JButton("Начать игру");
        startGameButton.setFont(new Font("Arial", Font.BOLD, 14));
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showLevelSelection();
            }
        });

        settingButton = new JButton("Настройки");
        settingButton.setFont(new Font("Arial", Font.BOLD, 14));
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showSettings();
            }
        });

        editorButton = new JButton("Редактор");
        editorButton.setFont(new Font("Arial", Font.BOLD, 14));
        editorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showEditor();
            }
        });

        buttonPanel.add(startGameButton);
        buttonPanel.add(settingButton);
        buttonPanel.add(editorButton);

        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);
        add(welcomePanel, BorderLayout.CENTER);
    }
}

class ImagesComponent extends JComponent {
    private Image image;

    public ImagesComponent(String path) {
        image = new ImageIcon(path).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
