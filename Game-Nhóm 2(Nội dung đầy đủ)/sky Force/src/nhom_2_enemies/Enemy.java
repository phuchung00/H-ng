/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom_2_enemies;

//import com.sun.javafx.tk.Toolkit;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import nhom_2_graphics.LoadImage;

/**
 *
 * @author Administrator
 */
public class Enemy {
   private int x ;
    private int y;
    private Image img;
   
    public Enemy(int x, int y){
       this.x = x;
       this.y = y;
    }
   
    public void tick(){
    y += 1;
    }
    public void render(Graphics g){
        
        
        g.drawImage(LoadImage.enemy,x, y,25,25, null);
        
    }
   
    public int getX(){
     return x;
    }
    public int getY() {
       return y;
    }
   
   
   

}

