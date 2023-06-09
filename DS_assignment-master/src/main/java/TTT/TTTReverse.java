/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TTT;

import java.awt.Color;
import javax.swing.JButton;


/**
 *
 * @author HP
 */
public class TTTReverse extends TTTnormalbot{
    
    @Override
    public int makeOptimumMove1(){
        
        for(int i =0;i<9;i++){
            if(buttons[i].getText()==""){
                buttons[i].setFont(tryfont);
                buttons[i].setText("O");
                if(!checkOwin()){
                    buttons[i].setText("");
                    buttons[i].setFont(normfont);
                    return i;
                }
                buttons[i].setText("");
                buttons[i].setFont(normfont);
            }
        }
        int move;
        do{
            move = rand.nextInt(0,9);
        }while (buttons[move].getText()!="");
        return move;
    }
    
    @Override
    public int makeOptimumMove2(){
        
        for(int i =0;i<9;i++){
            if(buttons[i].getText()==""){
                buttons[i].setFont(tryfont);
                buttons[i].setText("X");
                if(!checkXwin()){
                    buttons[i].setText("");
                    buttons[i].setFont(normfont);
                    return i;
                }
                buttons[i].setText("");
                buttons[i].setFont(normfont);
            }
        }
        int move = -1;
        return move;
    }
    
    @Override
    public void xWins(int a,int b, int c){
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);
        
        for (JButton button : buttons){
            button.setEnabled(false);
        }
        textField.setText("O wins");
    }
    
    @Override
    public void oWins(int a,int b, int c){
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);
        
        for (JButton button : buttons){
            button.setEnabled(false);
        }
        textField.setText("X wins");
    }
    
}
