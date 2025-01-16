package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class SettingComponent extends JComponent {
    private MainFrame mainFrame;
    private AudioPlayer audioPlayer;
    SettingComponent(MainFrame frame, AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        mainFrame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel volumeLabel = new JLabel("Громкость");
       
        JSlider volumeSlider = new JSlider(0, 100, audioPlayer.getCurrentVolume());
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setPreferredSize(new Dimension(300, 50));

        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int volume = volumeSlider.getValue();
                audioPlayer.setVolume(volume);

            }
        });

        JButton turnOffSoundButton = new JButton("Выключить звук");
        JButton turnOnSoundButton = new JButton("Включить звук");

        
        turnOffSoundButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                    audioPlayer.setVolume(0);
                    audioPlayer.setCurrentVolume(0);
                    audioPlayer.stop();     

                    turnOffSoundButton.setVisible(false);
                    turnOnSoundButton.setVisible(true);
            }
        });

        turnOnSoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioPlayer.setVolume(70);
                audioPlayer.setCurrentVolume(70);
                audioPlayer.play("/home/karim/presentation/myproject/src/main/resources/music/music.wav");
                
                turnOffSoundButton.setVisible(true);
                turnOnSoundButton.setVisible(false);

            }
        });



        JButton backButton = new JButton("Назад");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showMainMenu();
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(volumeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(volumeSlider, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        add(turnOffSoundButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        add(turnOnSoundButton, gbc);
        

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        add(backButton, gbc);
    }

}