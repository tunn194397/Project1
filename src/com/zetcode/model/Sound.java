package com.zetcode.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    public Clip clip ;
    public String nameOfFile[] = new String[20];
    
    public Sound(){
        this.nameOfFile[0] = "src/com/sound/Ailatrieuphu.wav";
        this.nameOfFile[1] = "src/com/sound/TayDuKy.wav";
        this.nameOfFile[2] = "src/com/sound/canhbao.wav";
        this.nameOfFile[3] = "src/com/sound/Sai.wav";
        this.nameOfFile[4] = "src/com/sound/pap.wav";
        this.nameOfFile[5] = "src/com/sound/LocCoc.wav";
        this.nameOfFile[6] = "src/com/sound/BlueBoyAdventure.wav";
        this.nameOfFile[7] = "src/com/sound/coin.wav";
        this.nameOfFile[8] = "src/com/sound/unlock.wav";
        this.nameOfFile[9] = "src/com/sound/powerup.wav";

    }

    public void setFile(int i){
        try {
            File file = new File(nameOfFile[i]);
            System.out.println("1: phan tu thu "+i);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            this.clip = AudioSystem.getClip();
            System.out.println("phan tu 2 thu "+i);
            this.clip.open(ais);
            System.out.println("phan tu 3 thu "+i);        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSound(){
        this.clip.start();
        System.out.println("turn on music ");
    }

    public void loopSound(){
        this.clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stopSound(){
        this.clip.stop();
        System.out.println("stop music");
    }
}
