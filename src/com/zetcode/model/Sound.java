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
        this.nameOfFile[0] = "src/sound/Ailatrieuphu.wav";
        this.nameOfFile[1] = "src/sound/BusyCityTrackTribe.wav";
        this.nameOfFile[2] = "src/sound/canhbao.wav";
        this.nameOfFile[3] = "src/sound/Sai.wav";
        this.nameOfFile[4] = "src/sound/pap.wav";
        this.nameOfFile[5] = "src/sound/LocCoc.wav";
        this.nameOfFile[6] = "src/sound/BlueBoyAdventure.wav";
        this.nameOfFile[7] = "src/sound/coin.wav";
        this.nameOfFile[8] = "src/sound/unlock.wav";
        this.nameOfFile[9] = "src/sound/powerup.wav";

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