package com.example;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class LevelComponent extends JComponent {
    private JPanel levelPanel;
    private MainFrame mainFrame;

    public LevelComponent(MainFrame frame, AudioPlayer audioPlayer) {
        this.mainFrame = frame;
        setLayout(new BorderLayout());

        levelPanel = new JPanel();
        levelPanel.setLayout(new GridLayout(0, 3));

        /*
         * Из JSON файла получаю сложность уровня и создаю для каждого уровня свою кнопку.
         * Добавляю ее в панель.
         */
        
        MyButton.audioPlayer = audioPlayer;

        List<String> diffLevels = JsonDataHandler.getLevel_diff();
        for (String diff : diffLevels) {
            List<Level> levels = JsonDataHandler.getLevels(diff);
            JButton levelButton = new MyButton(mainFrame, diff, levels);
            levelButton.setPreferredSize(new Dimension(150, 50));
            levelPanel.add(levelButton);
        }
  
        JButton backButton = new JButton("Назад");
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showMainMenu();
            }
        });
        
        add(backButton, BorderLayout.SOUTH);
        add(levelPanel, BorderLayout.CENTER);
    }
    public void update(){
        levelPanel.removeAll();
        List<String> diffLevels =  JsonDataHandler.getLevel_diff();

        for(String diff : diffLevels) {
            List<Level> levels = JsonDataHandler.getLevels(diff);
            JButton levelButton = new MyButton(mainFrame, diff, levels);
            levelPanel.add(levelButton);
        }    
    }
}

class MyButton extends JButton {
    public static AudioPlayer audioPlayer;
    private MainFrame mainFrame;
    private List<Level> listLevel;

    public MyButton(MainFrame frame, String diff, List<Level> level) {
        super(diff);
        this.mainFrame = frame;
        this.listLevel = level;
        
        setBackground(Color.lightGray);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(audioPlayer);
            }
        });
    }

    protected void handleButtonClick(AudioPlayer audioPlayer) {
        if(listLevel.size() > 0) {
            GameComponent gameComponent = new GameComponent(mainFrame, listLevel,audioPlayer);
            mainFrame.add(gameComponent, "Game");
            mainFrame.showStartGame();
        }
    }

    
}