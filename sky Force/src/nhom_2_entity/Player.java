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
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Administrator
 */
public class Player implements KeyListener{
    private int x;
    private int y; 
    private boolean left;
    private boolean right;
    private boolean fire;
    
    private long current;
    private long delay;
        
    public Player(int x , int y)
    {
        this.x =x;
        this.y = y;
    }
    public void init()
    {
       Display.frame.addKeyListener(this);
       current = System.nanoTime();
       delay = 100;
    }
    public void tick()
    {
        if(left)
        {
            if(x>= 50){
                x -= 1;
            }
        }
        if(right)
        {
            if(x<=450-30)
            {
                x += 1;
            }
        }
        if(fire)
        {
            long breaks = (System.nanoTime()- current)/100000;
            if(breaks >= delay)
            {
            gameManager.bullet.add(new Bullet(x +15,y));
        
            }
            current = System.nanoTime();
        }
    }
    public void render(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(x, y, 30, 30);
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        int sourse = e.getKeyCode();
        if(sourse == KeyEvent.VK_LEFT)
        {
            left =  true;
        }
        if(sourse == KeyEvent.VK_RIGHT)
        {
            right = true;
        }
        if(sourse == KeyEvent.VK_B)
        {
            fire = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int  sourse = e.getKeyCode();
        if(sourse == KeyEvent.VK_LEFT)
        {
            left = false;
        }
        if(sourse == KeyEvent.VK_RIGHT)
        {
            right = false;
        }
        if(sourse == KeyEvent.VK_B)
        {
            fire = false;
        }
        
    }
}
