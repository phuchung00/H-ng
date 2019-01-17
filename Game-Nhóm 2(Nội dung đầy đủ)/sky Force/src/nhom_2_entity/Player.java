/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom_2_entity;

import nhom_2_bullets.Bullet;
import nhom_2_Display.Display;
import nhom_2_gameManager.gameManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import nhom_2_graphics.LoadImage;
import nhom_2_main.musicStuff;
import nhom_2_setup.gameSetUp;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Administrator
 */
public class Player implements KeyListener {
private int x;
       private int y;
       private boolean left ,right;
       private boolean fire;
      
       private long current ;
       private long delay;
       private int health;

      
    public Player(int x,int y){
       this.x = x;
        this.y = y;
        
    }
    public void init(){
    Display.frame.addKeyListener(this);
    current = System.nanoTime();
    delay = 70;
    health = 3;
    }
    public void tick(){
       if(!(health <=0)){
           if(left){
     
           if(x>= 50){
           x  -=1;
       }
        }
       if(right){
           if(x<= 450-20){
           x += 1;}
       }
       if(fire){
           long breaks = (System.nanoTime()-current)/1000000;
           if(breaks > delay){
           gameManager.bullet.add(new Bullet(x+10,y));
       }
       current = System.nanoTime();
       }
       }
     }
    
     public void render(Graphics g){
      if(!(health <= 0)){
        

        g.drawImage(LoadImage.player, x, y, 20, 30, null);
        
        
      }
      }
    public void keyPressed(KeyEvent e) {
       int source = e.getKeyCode();
       if(source == KeyEvent.VK_LEFT){
           left = true;
           playmusic("sfx_menu_move4.wav");
       }
       if(source == KeyEvent.VK_RIGHT){
           right= true;
           playmusic("sfx_menu_move4.wav");
       }
        if(source == KeyEvent.VK_B){
           fire = true;
           String path = ("pewpew.wav");
            musicBullet ms = new musicBullet();
            ms.playMusic(path);
        }
    }
    
     
    public void keyReleased(KeyEvent e) {
       int source = e.getKeyCode();
       if(source == KeyEvent.VK_LEFT){
           left = false;
       }
       if(source == KeyEvent.VK_RIGHT){
           right= false;
         }
       if(source == KeyEvent.VK_B){
           fire = false;
       }
    }
     
    public void keyTyped(KeyEvent e) {
      
    }
    public int getX(){
       return x;
    }
    public int getY()
    {
       return y;
    }
    public int getHealth(){
       return health;
    }
    public void setHealth(int health){
       this.health = health;
    }
    public static void playmusic(String filepath)
    {
        InputStream music;
        try
        {
            music = new FileInputStream(new File(filepath));
            AudioStream audio =  new AudioStream(music);
            AudioPlayer.player.start(audio);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"ERROR");
        }
    }
}

