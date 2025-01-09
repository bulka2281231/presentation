package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class JsonDataHandler{
    private static String JSONPATH = "src/main/resources/data/levels.json";
    private static boolean flagForRead = true;
    private static GameData gameData;

    public static List<String> getLevel_diff(){
        if(flagForRead) readData();
        return gameData.getLevel_difficulty();
    }

    public static List<Level> getLevels(String difficult){
        if(flagForRead) readData();
        List<Level> res = new ArrayList<>();
        for(Level obj: gameData.getLevels()){
            if(obj.getDifficult().equals(difficult)) res.add(obj);
        }
        return res;
    }

    public static void writeData(Level obj){
        if(!gameData.getLevel_difficulty().contains(obj.getDifficult())){
            gameData.addLevel_difficulty(obj.getDifficult());
        }
        gameData.addLevels(obj);
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            objectMapper.writeValue(new File(JSONPATH), gameData);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    private static void readData(){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            gameData = objectMapper.readValue(new File(JSONPATH), GameData.class);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        flagForRead = false;
    }
}

class GameData{
    private List<String> level_difficulty;
    private List<Level> levels;

    public List<String> getLevel_difficulty() {
        return level_difficulty;
    }
    public void addLevel_difficulty(String obj){
        level_difficulty.add(obj);
    }
    public List<Level> getLevels() {
        return levels;
    }
    public void addLevels(Level obj){
        levels.add(obj);
    }
    public void setLevel_difficulty(List<String> level_difficulty) {
        this.level_difficulty = level_difficulty;
    }
    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

}

class Level{
    private String difficult;
    private String name;
    private String orig_image;
    private String blur_image;
    private String text_hint;
    private String illustrative_image;

    // Level(String name, String difficult, String text_hint, String orig_image, String blur_image, String illustrative_image){
    //     this.name = name;
    //     this.difficult = difficult;
    //     this.orig_image = orig_image;
    //     this.blur_image = blur_image;
    //     this.text_hint = text_hint;
    //     this.illustrative_image = illustrative_image;
    // }

    public String getBlur_image() {
        return blur_image;
    }
    public String getDifficult() {
        return difficult;
    }
    public String getIllustrative_image() {
        return illustrative_image;
    }
    public String getName() {
        return name;
    }
    public String getOrig_image() {
        return orig_image;
    }
    public String getText_hint() {
        return text_hint;
    }
    public void setBlur_image(String blur_image) {
        this.blur_image = blur_image;
    }
    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }
    public void setIllustrative_image(String illustrative_image) {
        this.illustrative_image = illustrative_image;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOrig_image(String orig_image) {
        this.orig_image = orig_image;
    }
    public void setText_hint(String text_hint) {
        this.text_hint = text_hint;
    }
}