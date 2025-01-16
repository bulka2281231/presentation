package com.game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class AudioPlayer {
    private Clip clip;
    private int currentVolume = 80;
    private FloatControl volumeControl;

    public void play(String filePath) {
        try {
            if (clip != null && clip.isOpen()) {
                clip.close();
            }

            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);    

            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(currentVolume);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
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

    public void setVolume(int volume) {

        if (clip != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float range = max - min;

            float volumeValue = min + (volume / 100f) * range;

            volumeControl.setValue(volumeValue);
        }
        currentVolume = volume;
    }

    public int getCurrentVolume() { 
        return currentVolume;
    }

}