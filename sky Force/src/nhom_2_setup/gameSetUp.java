/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom_2_setup;

import nhom_2_Display.Display;
import nhom_2_gameManager.gameManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class gameSetUp implements Runnable{
     private String title;
    private int width;
    private int height;
    private Thread thread;
    private boolean running;
    private Display display;
    private BufferStrategy buffer;
    private Graphics g;
    private int y;
    private gameManager manager;
    public static final int gameWidth = 400;
    public static final int gameHeight = 400;
    
    public gameSetUp(String title , int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;
    }
    public void  init()
    {
        display = new Display(title, width, height);
        manager =  new gameManager();
        manager.init();
    }
    public synchronized void start()
    {
        if(running)
            return;
            running = true;
        if(thread == null)
        {
     thread = new Thread(this);   
     thread.start();
        }
        
    }
    public synchronized void stop()
    {
        if(!(running))
            return;
        running =false;
         try {
             thread.join();
         } catch (InterruptedException ex) {
             Logger.getLogger(gameSetUp.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public void tick()
    {
        manager.tick();
    }
    public void render()
    {
        buffer = display.getCanvas().getBufferStrategy();
        if(buffer == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = buffer.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        
        //draw
            
            g.setColor(Color.WHITE);
            g.fillRect(50, 50, gameWidth, gameHeight);
            manager.render(g);
            
        ///
        
        buffer.show();
        g.dispose();
    }
    @Override
    public void run() {
        init();
        
        int  fps =50;
        double TimePerTick = 1000000000/fps;
        double delta = 0;  
        long current = System.nanoTime();
        while (running )
        {
            delta = delta + (System.nanoTime()- current/TimePerTick);
            current = System.nanoTime();
            if(delta >= 1)
            {
                tick();
                render();
                delta --;
    
            }
    
        }

    }
}
