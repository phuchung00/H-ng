/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom_2_enemies;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Administrator
 */
public class Enemy {
   private int x ;
    private int y;
   
    public Enemy(int x, int y){
       this.x = x;
       this.y = y;
    }
   
    public void tick(){
    y += 1;
    }
    public void render(Graphics g){
       g.setColor(Color.black);
        g.fillOval(x, y,25,25);
    }
    public int getX(){
     return x;
    }
    public int getY() {
       return y;
    }
   
   
   

}

