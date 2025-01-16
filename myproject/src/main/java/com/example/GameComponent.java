package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class GameComponent extends JComponent {
    private MainFrame mainFrame;
    private JTextField answerField;
    private JButton hintButton;
    private JButton sendButton;
    private JLabel imageLabel;
    private List<Level>levelsList;
    private int currentLevelIndex;
    private AudioPlayer audioPlayer;

    public GameComponent(MainFrame frame, List<Level> level, AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        this.mainFrame = frame;
        levelsList = level;
        currentLevelIndex = 0;
        
        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        answerField = new JTextField(20);
        hintButton = new JButton("Подсказка");
        sendButton = new JButton("Проверить");

        loadImage(levelsList.get(currentLevelIndex).getIllustrative_image());

        JPanel inputPanel = new JPanel();
        inputPanel.add(answerField);
        inputPanel.add(sendButton);
        inputPanel.add(hintButton);

        add(imageLabel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHint();
            }
        });
    }

    private void loadImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        imageLabel.setIcon(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void loadNextImage() {
        currentLevelIndex++;
        if(currentLevelIndex < levelsList.size()) {
            loadImage(levelsList.get(currentLevelIndex).getIllustrative_image());
            answerField.setText("");
        }
        else {
            JOptionPane.showMessageDialog(this, "Поздравляем! Вы прошли уровень " + levelsList.get(currentLevelIndex - 1).getDifficult() + "!");
            if(currentLevelIndex == levelsList.size() ) {
                mainFrame.remove(this);
            }
        }
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim();
        boolean isCorrectAnswer = userAnswer.equalsIgnoreCase(levelsList.get(currentLevelIndex).getName());

        audioPlayer.stop();

        if(isCorrectAnswer) {
            audioPlayer.setVolume(audioPlayer.getCurrentVolume());
            audioPlayer.play("/home/karim/presentation/myproject/src/main/resources/music/correct.wav");
        }
        else {
            audioPlayer.setVolume(audioPlayer.getCurrentVolume());
            audioPlayer.play("/home/karim/presentation/myproject/src/main/resources/music/incorrect.wav");
        }

        try {
            if(isCorrectAnswer) {
                Thread.sleep(1000);
                audioPlayer.stop();
                loadImage(levelsList.get(currentLevelIndex).getOrig_image());
                JOptionPane.showMessageDialog(this, "Правильный ответ!");
                loadNextImage();
                audioPlayer.setVolume(audioPlayer.getCurrentVolume());
                audioPlayer.play("/home/karim/presentation/myproject/src/main/resources/music/music.wav");
                
            }
            else {
                Thread.sleep(1000);
                audioPlayer.stop();
                JOptionPane.showMessageDialog(this, "Неправильный ответ. Попробуйте снова.");
                answerField.setText(""); // Очищаем поле ввода
                audioPlayer.setVolume(audioPlayer.getCurrentVolume());
                audioPlayer.play("/home/karim/presentation/myproject/src/main/resources/music/music.wav");
            }
        }
        catch(InterruptedException e ){
            e.printStackTrace();
        }
       
    }

    private void showHint() {
        String[] options = {"Заблюренное изображение", "Словесная подсказка"};
        int choice = JOptionPane.showOptionDialog(this, 
                "Выберите тип подсказки:", 
                "Подсказка", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                options, 
                options[0]);

        if (choice == 0) {
            loadImage(levelsList.get(currentLevelIndex).getBlur_image());
        } else if (choice == 1) {
            JOptionPane.showMessageDialog(this, "Подсказка: " + levelsList.get(currentLevelIndex).getText_hint());
        }
    }
}

/*
 *  if(userAnswer.equalsIgnoreCase(levelsList.get(currentLevelIndex).getName())) {

            audioPlayer.stop();
            audioPlayer.play("/home/karim/presentation/myproject/src/main/resources/music/correct.wav");
            boolean flag = true;
            while(flag) {
                try {
                    Thread.sleep(1000);
                    audioPlayer.stop();
                    loadImage(levelsList.get(currentLevelIndex).getOrig_image());
                    JOptionPane.showMessageDialog(this, "Правильный ответ!");
                    loadNextImage();
                    flag = false;
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    audioPlayer.play("/home/karim/presentation/myproject/src/main/resources/music/music.wav");
                }
            }
        }
        else {
            audioPlayer.stop();
            audioPlayer.play("/home/karim/presentation/myproject/src/main/resources/music/incorrect.wav");
            boolean flag = true;
            while(flag) {
                try{
                    Thread.sleep(1000);
                    audioPlayer.stop();
                    JOptionPane.showMessageDialog(this, "Неправильный ответ. Попробуйте снова.");
                    answerField.setText(""); // Очищаем поле ввода
                    flag = false;
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    audioPlayer.play("/home/karim/presentation/myproject/src/main/resources/music/music.wav");
                }
            }
        }
 */