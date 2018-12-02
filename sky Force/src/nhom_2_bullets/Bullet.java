/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom_2_bullets;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Administrator
 */
public class Bullet {
    
    private int x;
    private int y;
    private int speed;
    
    public Bullet(int x, int y)
    {
        this.x =x;
        this.y =y;
        speed =1;
    }
    public void tick()
    {
        y -= speed;
    }
    
    public int getY()
    {
        return y;
    }
    public int getX()
    {
        return x;
    
    }
    
    public void render(Graphics g)
    {
        g.setColor(Color.blue); 
        g.fillRect(x, y, 6, 10);
    }
}
