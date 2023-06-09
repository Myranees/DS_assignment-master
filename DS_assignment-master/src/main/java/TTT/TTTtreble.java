/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TTT;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import javax.swing.*;
/**
 *
 * @author HP
 * 
 *  size 10, 1st 4, 2nd 1;
 */
public class TTTtreble implements ActionListener{
    
    private int tiles;
    Random rand = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons;
//    boolean player1_turn;
    List<Integer> selections = new ArrayList<Integer>();
    BoardTreble gameBoard;// = new BoardTreble(tiles);
    

//printing board
    
    public TTTtreble(int tiles){
        this.tiles = tiles;
        this.buttons = new JButton[tiles];
        gameBoard = new BoardTreble(tiles);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(100*tiles,200);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        
        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("Ink Fres",Font.BOLD,75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("TrebleCrossTTT");
        textField.setOpaque(true);
        
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,100,200*tiles);
        
        
        button_panel.setLayout(new GridLayout(1,tiles));
        button_panel.setBackground(new Color(150,150,150));
        
        for(int i=0; i<tiles; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,80));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }
        
        title_panel.add(textField);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);
        
//        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //reading input from all button
        for(int i=0; i<tiles; i++){
            
            //action when button is clicked
            if(e.getSource()==buttons[i]){
                if(buttons[i].getText()==""){
                    buttons[i].setForeground(new Color(255,0,0));
                    buttons[i].setText("X");
                    selections.add(i);
                    gameBoard.setSelected(selections);
        //			gameBoard.printBoard();


                    //check for gameover
                    if (gameBoard.gameOver()) {
                        GameOver();
                        break;
                    }

                    //could show game score here

                    // now for the bot move
                    
                    BotMove();
        //gameBoard.printBoard();

                    if (gameBoard.gameOver()) {
                        GameOver();
                        break;
                    }
                //could show score again here
                }


            }
            
        }
    }
    
    //action to do when game over
    public void GameOver(){
        for (JButton button : buttons){
            button.setEnabled(false);
        }
        textField.setText("Game End");
    }
    
    
    // bot move 
    public void BotMove(){
        
        int move = gameBoard.bestMove();
//        delay();
            buttons[move].setForeground(new Color(255,0,0));
            buttons[move].setText("X");
            selections.add(move);
            gameBoard.setSelected(selections);
    }
    
    
//    public void delay(){
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//    }
    
    
    
    
    
    
    
    
}
