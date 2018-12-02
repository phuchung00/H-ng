/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhom_2_main;

import nhom_2_setup.gameSetUp;
import nhom_2_Display.Display;

/**
 *
 * @author Administrator
 */
public class Main {
    public static void main(String[] args )
    {
        gameSetUp game= new gameSetUp("Sky Force",500,600);
        game.start();
    }
}
