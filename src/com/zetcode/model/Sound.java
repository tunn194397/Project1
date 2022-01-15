package com.zetcode.model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

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
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSound(){
        this.clip.start();
    }

    public void loopSound(){
        this.clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stopSound(){
        this.clip.stop();
    }
}