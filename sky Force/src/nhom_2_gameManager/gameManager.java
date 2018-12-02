/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom_2_gameManager;

import nhom_2_setup.gameSetUp;
import nhom_2_bullets.Bullet;
import nhom_2_enemies.Enemy;
import nhom_2_entity.Player;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class gameManager {
    private Player player;
    public static ArrayList<Bullet> bullet;
    private ArrayList<Enemy>enemies;
    private long current;
    private long delay;
    
    public gameManager()
    {
    
    }
    
    public void init()
    {
        player = new Player((gameSetUp.gameWidth/2)+50, (gameSetUp.gameHeight -30)+50);
        player.init();
        bullet = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        
        current = System.nanoTime();
        delay = 800;
    }
    public void tick()
    {
        player.tick();
        for(int i = 0; i < bullet.size(); i++)
        {
            bullet.get(i).tick();
        }
        
        
        long breaks = (System.nanoTime() - current) /1000000;
        if(breaks > delay)
        {
        for(int i=  0;i<2  ;i++)
        {
            Random rand=new Random();
            int randX=rand.nextInt(450);
            int randY=rand.nextInt(450);
            enemies.add(new Enemy(randX,-randY));
        }
        current = System.nanoTime();
        }
        
        for(int i = 0; i < enemies.size(); i++)
        {
            enemies.get(i).tick();
        }
    }
    
    public void  render(Graphics g)
    {
        player.render(g);
         for(int i = 0; i < bullet.size(); i++)
        {
            bullet.get(i).render(g);
        }
         
         
         for (int i = 0; i < bullet.size(); i++)
         {
             if(bullet.get(i).getY() <= 50)
             {
                 bullet.remove(i);
                 i--;
             }
         }
         
         for(int i = 0 ; i < enemies.size(); i++)
         {
             if(!(enemies.get(i).getX() <= 50 || enemies.get(i).getX() >= 450 -25 || enemies.get(i).getY() >= 450 -25))
             {
                 if(enemies.get(i).getY()>= 50)
                 {
                     enemies.get(i).render(g);
                 }
             }
         }
         for(int i = 0; i < enemies.size(); i++)
         {
             int ex = enemies.get(i).getX();
             int ey = enemies.get(i).getY();
             for(int j = 0 ; j < bullet.size(); j ++)
             {
                 int bx = bullet.get(j).getX();
                 int by = bullet.get(j).getY();
                 
                 if(ex < bx + 6 &&  ex +25 > bx && ey < by + 6  && ey + 25  > by)
                 {
                     enemies.remove(i);
                     i--;
                     
                     bullet.remove(j);
                     j--;
                 }
             }
         }
    }
}
