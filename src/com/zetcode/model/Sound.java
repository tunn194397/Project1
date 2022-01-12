package com.zetcode.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    public Clip clip ;
    public URL soundURL[] = new URL[20];

    public Sound(){
        soundURL[0] = this.getClass().getClassLoader().getResource("/sound/Ailatrieuphu.wav");
        soundURL[1] = this.getClass().getResource("/sound/TayDuKy.wav");
        soundURL[2] = this.getClass().getResource("/sound/Shopee.wav");
        soundURL[3] = this.getClass().getResource("/sound/Sai.wav");
        soundURL[4] = this.getClass().getResource("/sound/pap.wav");
        soundURL[5] = this.getClass().getResource("/sound/LocCoc.wav");
        soundURL[6] = this.getClass().getResource("sound/Wooden Train Whistle.mp3");
    }

    public void setFile(int i){
        if(soundURL[0] == null)System.out.println("soundURL bi null");
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
            System.out.println("phan tu thu "+i);
        } catch (Exception e) {
            System.out.println("clip null mat roi");
        }
        System.out.println("file thu "+i);
    }

    public void playSound(){
        this.clip.start();
        System.out.println("turn on music " + clip.getFormat().toString());
    }

    public void loopSound(){
        this.clip.loop(clip.LOOP_CONTINUOUSLY);
        System.out.println("loop music " + clip.getLineInfo().toString());
    }

    public void stopSound(){
        this.clip.stop();
        System.out.println("stop music");
    }
}
