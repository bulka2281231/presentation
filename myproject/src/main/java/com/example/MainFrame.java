package com.example;

import javax.swing.*;
import java.util.List;

class MainFrame extends JFrame {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    public MainFrame(String nameApp) {
        super(nameApp);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        WindowComponent component = new WindowComponent(this);
        add(component);
    }
    public void startGame() {  // новое окно с игрой
        JFrame gameFrame = new JFrame("Игра 'Угадай картинку'");
        gameFrame.setSize(WIDTH, HEIGHT);
        gameFrame.setLocationRelativeTo(null);  // располагаю окно по центру
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // деф настрйока


        // List<Level> obj = ob.getLevels();
        // List<Level> obj = JsonDataHandler.getLevels("легкий");
        // System.out.println(obj.size());
        // System.out.printf("%s %s %s %s %s", level.getBlur_image(), level.getDifficult(), level.getIllustrative_image(), level.getOrig_image(), level.getName());
        // List<String> obj = ob.getLevel_diff();
        // System.out.println(obj);
        GameComponent gameComponent = new GameComponent();
        gameFrame.add(gameComponent);  // добавляю логику

        gameFrame.setVisible(true);  // деф настройка
    }
}