package com.example;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame("Угадай картинку");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}