/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom_2_entity;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author tiger
 */
public class musicBullet {
    
    void playMusic(String musicLocation)
    {
        try
        {
            File musicPath = new File(musicLocation);
            if (musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else System.out.println("Cant found file");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
