/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom_2_gameManager;

import java.awt.Color;
import java.awt.Font;
import nhom_2_setup.gameSetUp;
import nhom_2_bullets.Bullet;
import nhom_2_enemies.Enemy;
import nhom_2_entity.Player;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import nhom_2_Display.Display;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Administrator
 */
public class gameManager implements KeyListener {

    private Player player;
    public static ArrayList<Bullet> bullet;
    private ArrayList<Enemy> enemies;

    private long current;
    private long delay;
    private int health;
    private int score,t ;
    private boolean start;

    public gameManager() {

    }

    public void init() {
        Display.frame.addKeyListener(this);
        player = new Player((gameSetUp.gameWidth / 2) + 50, (gameSetUp.gameHeight - 30) + 50);
        player.init();
        bullet = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        current = System.nanoTime();
        delay = 2000;
        health = player.getHealth();
        score = 0;
    }

    public void tick() {
        if (start) {
            player.tick();

            for (int i = 0; i < bullet.size(); i++) {
                bullet.get(i).tick();
            }

            long breaks = (System.nanoTime() - current) / 1000000;
            if (breaks > delay) {

                for (int i = 0; i < 2; i++) {
                    Random rand = new Random();
                    int randX = rand.nextInt(450);
                    int randY = rand.nextInt(450);
                    if (health > 0) {
                        enemies.add(new Enemy(randX, -randY));
                    }
                }
                current = System.nanoTime();
            }

            //kẻ địch
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).tick();

            }

        }
    }

    public void render(Graphics g) {
        if (start) {
            player.render(g);

            // đạn
            for (int i = 0; i < bullet.size(); i++) {
                bullet.get(i).render(g);
            }

            for (int i = 0; i < bullet.size(); i++) {
                if (bullet.get(i).getY() <= 50) {
                    bullet.remove(i);
                    i--;

                }
            }

            //địch
            for (int i = 0; i < enemies.size(); i++) {
                if (!(enemies.get(i).getX() <= 50 || enemies.get(i).getX() >= 450 - 50 || enemies.get(i).getY() >= 450 - 20)) {

                    if (enemies.get(i).getY() >= 50) {
                        enemies.get(i).render(g);
                    }
                }
            }

            //va chạm giữa người chơi và địch
            for (int i = 0; i < enemies.size(); i++) {
                int ex = enemies.get(i).getX();
                int ey = enemies.get(i).getY();

                int px = player.getX();
                int py = player.getY();

                if (px < ex + 20 && px + 20 > ex && py < ey + 30 && py + 30 > ey) {
                    enemies.remove(i);
                    i--;
                    health--;
                    playmusic("sfx_sounds_damage1.wav");
                    System.out.println(health);
                    if (health <= 0) {
                        playmusic("Explosion.wav");
                        enemies.removeAll(enemies);
                        player.setHealth(0);
                        start = false;
                        
                    }
                }
                // va chạm kẻ địch và đạn
                for (int j = 0; j < bullet.size(); j++) {
                    int bx = bullet.get(j).getX();
                    int by = bullet.get(j).getY();

                    if (ex < bx + 6 && ex + 50 > bx && ey < by + 6 && ey + 50 > by) {
                        playmusic("sfx_deathscream_android2.wav");
                        enemies.remove(i);
                        i--;
                        bullet.remove(j);
                        j--;
                        
                      score +=1;
                       
                    }
                }
                g.setColor(Color.blue);
                g.setFont(new Font("FVF Fernando 08", Font.BOLD, 20));
                g.drawString("Điểm : " + score, 70, 500);
                
                g.setColor(Color.blue);
                g.setFont(new Font("FVF Fernando 08", Font.BOLD, 15));
                g.drawString("Máu : " + health, 70, 550);
                
                g.setColor(Color.blue);
                g.setFont(new Font("FVF Fernando 08", Font.BOLD, 15));
                g.drawString("Bấm B để bắn" , 200, 550);
                
                g.setColor(Color.blue);
                g.setFont(new Font("FVF Fernando 08", Font.BOLD, 15));
                g.drawString("Mũi tên trái phải để di chuyển" , 100, 590);
            }
        }
        if(!start)
        {
            g.setColor(Color.BLACK);
            g.setFont(new Font("FVF Fernando 08", Font.BOLD, 25));
            g.drawString("Bấm Enter Để Chơi", 95, 300);
            
             g.setColor(Color.BLACK);
                g.setFont(new Font("FVF Fernando 08", Font.BOLD, 20));
                g.drawString("Điểm Cao : " + score, 90, 200);
        }

    }

    public void keyPressed(KeyEvent e) {
        int source = e.getKeyCode();
        if (source == KeyEvent.VK_ENTER) {
            playmusic("sfx_sounds_error8.wav");
            start = true;
            init();
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

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
    static int diem (int count)
    {
        int n1 = 0;
        int n2 = 10;
        int n3 = 0;
        if (count > 20) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
            diem(count - 1);
        }
            return n3;
    }
    
   


}
