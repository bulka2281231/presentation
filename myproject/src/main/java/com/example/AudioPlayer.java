package com.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

class AudioPlayer {
    private Clip clip;
    private int currentVolume = 70;

    public void play(String filePath) {
        try {
            // Закрываем предыдущий Clip, если он существует
            if (clip != null && clip.isOpen()) {
                clip.close();
            }

            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Зацикливание
            clip.start(); // Запускаем воспроизведение
            setVolume(currentVolume);
 
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    // Метод для изменения громкости
    public void setVolume(int volume) {

        if (clip != null) {
            // Получаем FloatControl для управления громкостью
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float range = max - min;

            // Преобразуем значение слайдера (0-100) в диапазон контроля громкости
            float volumeValue = min + (volume / 100f) * range;

            volumeControl.setValue(volumeValue);
        }

        currentVolume = volume;
    }

    public int getCurrentVolume() { 
        return currentVolume;
    }

    public void setCurrentVolume(int volume) {
        currentVolume = volume;
    } 


}
