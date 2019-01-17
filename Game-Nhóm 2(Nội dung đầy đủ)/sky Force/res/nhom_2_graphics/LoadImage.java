/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom_2_graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;



/**
 *
 * @author tiger
 */
public class LoadImage {
    public static BufferedImage image;
    public static BufferedImage entities;
    public static BufferedImage player, enemy;
    
    public static void init () {
        image = imageLoader("/sky3.png");
        entities = imageLoader("/airplane.png");
        crop();
    }
    
    public static BufferedImage imageLoader(String path) {
        try {
            return ImageIO.read(LoadImage.class.getResource(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    
    public static void crop()
    {
        enemy = entities.getSubimage(0, 0, 115, 95);
        player = entities.getSubimage(115, 0, 115, 95);
    }
}
