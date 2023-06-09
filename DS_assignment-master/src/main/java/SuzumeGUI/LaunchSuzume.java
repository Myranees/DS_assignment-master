/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SuzumeGUI;

import Map.FinalMap;
import java.util.Arrays;
import javax.swing.JFrame;

/**
 *
 * @author HP
 */
public class LaunchSuzume {

    public LaunchSuzume() {
        FinalMap map = new FinalMap();
        
        
        SuzumeFloor floor = new SuzumeFloor(map.getFin());
        SuzumeFrame frame = new SuzumeFrame("Suzu", floor);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	floor.addFloorListener(frame.getSuzumeComponent());
    }
    
    
    
    
    
}
