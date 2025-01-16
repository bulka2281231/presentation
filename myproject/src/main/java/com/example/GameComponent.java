package com.example;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class GameComponent extends JComponent {
    private MainFrame mainFrame;
    private JTextField answerField;
    private JButton hintButton;
    private JButton sendButton;
    private JButton backButton;
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
        backButton = new JButton("Назад");

        loadImage(levelsList.get(currentLevelIndex).getIllustrative_image());

        JPanel inputPanel = new JPanel();
        inputPanel.add(answerField);
        inputPanel.add(sendButton);
        inputPanel.add(hintButton);
        inputPanel.add(backButton);

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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showLevelSelection();
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
        boolean isInCorrectAnswer = userAnswer.equalsIgnoreCase(levelsList.get(currentLevelIndex).getName());

        AudioPlayer tempAudioPlayer = new AudioPlayer();

        if(isInCorrectAnswer) {
            tempAudioPlayer.play("src/main/resources/music/incorrect.wav");
        }
        else {
            tempAudioPlayer.play("src/main/resources/music/correct.wav");
        }

        try {
            if(isInCorrectAnswer) {
                Thread.sleep(700);
                tempAudioPlayer.stop();
                loadImage(levelsList.get(currentLevelIndex).getOrig_image());
                JOptionPane.showMessageDialog(this, "Правильный ответ!");
                loadNextImage();
            }
            else {
                Thread.sleep(700);
                tempAudioPlayer.stop();
                JOptionPane.showMessageDialog(this, "Неправильный ответ. Попробуйте снова.");
                answerField.setText("");
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