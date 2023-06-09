package SuzumeGUI;

//import TicTacToe.NewJFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import page.Exit;


public class Player extends AbstractCharacter
{

    private final static int PLAYER_START_X = 12;
    private final static int PLAYER_START_Y = 12;
    private final static int PLAYER_PIXELS_BY_STEP = 4;
    private SuzumeFloor floor;
    private SuzumeFrame frame;
    
    public Player(SuzumeComponent SuzumeComponent, SuzumeFloor floor) {
	super(PLAYER_START_X, PLAYER_START_Y, PLAYER_PIXELS_BY_STEP);
	this.floor = floor;
	setPlayerButtons(SuzumeComponent);
    }

    public Action up = new AbstractAction() {
	public void actionPerformed(ActionEvent e) {
	    movePlayer(Move.UP);

	}
    };
 
    public Action right = new AbstractAction() {
	public void actionPerformed(ActionEvent e) {
	    movePlayer(Move.RIGHT);

	}
    };
   
    public Action down = new AbstractAction() {
	public void actionPerformed(ActionEvent e) {
	    movePlayer(Move.DOWN);

	}
    };
  
    public Action left = new AbstractAction() {
	public void actionPerformed(ActionEvent e) {
	    movePlayer(Move.LEFT);

	}
    };
    
    public void setPlayerButtons(SuzumeComponent bombermanComponent){
        bombermanComponent.getActionMap().put("moveRight", right);
	bombermanComponent.getActionMap().put("moveLeft", left);
	bombermanComponent.getActionMap().put("moveUp", up);
	bombermanComponent.getActionMap().put("moveDown", down);
	bombermanComponent.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
	bombermanComponent.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
	bombermanComponent.getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveUp");
	bombermanComponent.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
	
//        bombermanComponent.getInputMap().put(keyStroke, this);
    }
    
    

    

    

    private void movePlayer(Move move) {
	move(move);
        if(floor.collisionWithObstacle(this)){
	    moveBack(move);
	}
	if(floor.collisionWithFInal(this)){
            floor.setIsGameOver(true);
        
////	    moveBack(move);
	}
	if(floor.collisionWithStation(this)){
            
            floor.setGetTTT(true);
            
//	    moveBack(move);
	}
//	if(floor.collisionWithEnemies()){
//	    floor.setIsGameOver(true);
//	}

	floor.notifyListeners();
    }

}
