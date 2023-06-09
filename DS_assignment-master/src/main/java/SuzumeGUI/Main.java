/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SuzumeGUI;

import Map.FinalMap;
import TTT.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.Random;
import javax.sound.sampled.ReverbType;
import page.Exit;
/**
 *
 * @author HP
 */
public class Main {
    static FinalMap map = new FinalMap();
    private static final int TIME_STEP = 30;
    private static Timer clockTimer = null;
    
    
    public static void main(String[] args) {
        startGame(1);
    }
    
    public static void startGame(int difficulty) {
	SuzumeFloor floor = new SuzumeFloor(map.getFin());
	SuzumeFrame frame = new SuzumeFrame("Suzume", floor);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	floor.addFloorListener(frame.getSuzumeComponent());
        

	Action doOneStep = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e) {
		tick(frame, floor,difficulty);
	    }
	};
	clockTimer = new Timer(TIME_STEP, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }
    
      private static void gameOver(SuzumeFrame frame, SuzumeFloor floor) {
	clockTimer.stop();
	frame.dispose();
	Exit launch = new Exit();
        launch.setVisible(true);
    }

    private static void tick(SuzumeFrame frame, SuzumeFloor floor,int difficulty) {
	if (floor.getIsGameOver()) {
	    gameOver(frame, floor);
	} 
        else if(floor.isGetTTT()){
            floor.setGetTTT(false);
            
            int temp = new Random().nextInt(3);
            if(temp ==0) new TTT5x5(difficulty);
            else if (temp==1)new TTTtreble(13);
            else {
                SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
            ReversedTTT game = new ReversedTTT(3);
        });
            }
//            new TTTtreble(13);
//            TTT5x5 ttt5x5 = new TTT5x5(difficulty);
//            TTTReverse reverse = new TTTReverse();
//            TTTnormalbot tictactoe = new TTTnormalbot();
//            TTT.setVisible(true);
//            frame.add(TTT);
         
        }
        else {
	    floor.notifyListeners();
	}
    }
}
