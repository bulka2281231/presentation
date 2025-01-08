package com.example;

import java.awt.*;
import javax.swing.*;


class GameComponent extends JComponent {
    private enum checkLevel {Easy, Medium, Hard};
    private checkLevel currentLevel;
    private JTextArea hintArea;

    public GameComponent() {
        setLayout(new BorderLayout());

        String levels[] = {"Easy", "Medium", "Hard"} ;
        JComboBox<String> levelSelector = new JComboBox<>(levels);
        // levelSelector.addActionListener(new);
    }

}