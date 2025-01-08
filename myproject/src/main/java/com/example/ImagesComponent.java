package com.example;

import javax.swing.*;
import java.awt.*;

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