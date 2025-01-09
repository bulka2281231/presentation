package com.example;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

class LevelComponent extends JComponent {
    private JPanel levelPanel;
    private MainFrame mainFrame;

    public LevelComponent(MainFrame frame) {
        this.mainFrame = frame;
        setLayout(new BorderLayout());
        
        levelPanel = new JPanel();  
        levelPanel.setLayout(new GridLayout(1,3));  // создаю панель и разбиваю на 3 части
        
        /*
         * из json файла получаю сложность уровня и создаю для каждого уровня свою кнопку.
         * добавляю ее в панель.
         */

        List<String> diffLevels =  JsonDataHandler.getLevel_diff();
        List<MyButton> buttonsLevel = new ArrayList<>();

        for(String diff : diffLevels) {
            List<Level> levels = JsonDataHandler.getLevels(diff); // Получаем уровни по сложности
            JButton levelButton = new MyButton(mainFrame, diff, levels);
            levelPanel.add(levelButton);
            // JButton levelButton = createLevelButton(levels);
            // levelPanel.add(levelButton);
        }        

        add(levelPanel, BorderLayout.CENTER);

    }


}

class MyButton extends JButton {
    private MainFrame mainFrame;
    private List<Level> listLevel;

    public MyButton(MainFrame frame, String diff, List<Level> level) {
        super(diff); // Используем название сложности для кнопки
        this.mainFrame = frame;
        this.listLevel = level;
        
        setBackground(Color.lightGray);  // сама кнопка серая
        setBorder(BorderFactory.createLineBorder(Color.BLACK));  // обводка - черная
        
        // Добавляем слушателя на нажатие кнопки
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(); // Вызываем переопределенный метод
            }
        });
    }

    // Переопределенный метод для обработки нажатия кнопки
    protected void handleButtonClick() {
        if(listLevel.size() > 0) {
            GameComponent gameComponent = new GameComponent(mainFrame, listLevel);
            mainFrame.add(gameComponent, "Game");
            mainFrame.showStartGame();
        }  // если клавиша нажата, появится новое окно.
    }

    
}