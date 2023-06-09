/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TTT;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 *
 * @author HP
 */
public class TTTnormalbot extends TTTnormal{

    private final Difficulty difficulty;
    Font tryfont = new Font("MV Boli",Font.BOLD,0);
    Font normfont = new Font("MV Boli",Font.BOLD,120);
    int counter =0;

    enum Difficulty {
        EASY, MEDIUM, HARD
    }
    
    public TTTnormalbot() {
        super();
        difficulty=Difficulty.EASY;
    }
    
    @Override
    public void firstTurn() {
        
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
        
        if(rand.nextInt(2)==0){
            textField.setText("X turn");
            setButtons(true);
        }
        else{
//            setButtons(false);
            
            BotMove();
//            textField.setText("O turn");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<tiles; i++){
            if(e.getSource()==buttons[i]){
                if(buttons[i].getText()==""){
                    buttons[i].setForeground(new Color(255,0,0));
                    buttons[i].setText("X");
                    counter++;
                    if(checkDraw()){
                        Draw();
                        break;
                    }
                    check();
                    if(checkXwin())break;
                    player1_turn=false;
                    textField.setText("O turn");
                   
                    BotMove();
                    if(checkDraw()){
                        Draw();
                        break;
                    }
                    if(checkOwin())break;
                    player1_turn=true;
                    textField.setText("X turn");
                }
            }
        }
    }
    
    public void BotMove(){
        setButtons(false);
        counter++;
        int move;
        if (this.difficulty == Difficulty.EASY)move = BotMoveEasy();
        else if (this.difficulty == Difficulty.HARD)move = BotMoveHard();
        else move = BotMoveMedium();
        
        buttons[move].setForeground(new Color(0,0,255));
        buttons[move].setText("O");
        
        
        setButtons(true);
        check();

    }
    
    public int BotMoveEasy(){
        int move;
        
        do{
             move = rand.nextInt(0,tiles);
        }while (buttons[move].getText()!="");
        
        return move;
    }
    
    public int BotMoveMedium(){
        return makeOptimumMove1();
    }
    
    public int BotMoveHard(){
        int move = makeOptimumMove2(); 
        if(move < 0 ){
            move = makeOptimumMove1();
        }
        return move;
    }
    

    public int makeOptimumMove1(){
        
        for(int i =0;i<tiles;i++){
            if(buttons[i].getText()==""){
                buttons[i].setFont(tryfont);
                buttons[i].setText("finding");
                if(checkOwin()){
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
            move = rand.nextInt(0,tiles);
        }while (buttons[move].getText()!="");
        return move;
    }
    
    public int makeOptimumMove2(){
        
        for(int i =0;i<tiles;i++){
            if(buttons[i].getText()==""){
                buttons[i].setFont(tryfont);
                buttons[i].setText("trying 2");
                if(checkXwin()){
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
    
//    public void undoMove(int i){
//        if(buttons[i].getText()=="X" || buttons[i].getText()=="O"){
//            buttons[i].setForeground(null);
//            buttons[i].setText("");
//        }
//    }
    
    public boolean checkDraw(){
        if(counter<tiles)return false;
        return true;
    }
    
    public void Draw(){
        for(int i=0;i<tiles;i++){
            buttons[i].setBackground(Color.green);
        }
        setButtons(false);
        textField.setText("Draw");
    }
    
    public boolean checkOwin(){
        if(
                (buttons[0].getText()=="O")&&
                (buttons[1].getText()=="O")&&
                (buttons[2].getText()=="O")
                )return true;
        
        if(
                (buttons[3].getText()=="O")&&
                (buttons[4].getText()=="O")&&
                (buttons[5].getText()=="O")
                )return true;
        if(
                (buttons[6].getText()=="O")&&
                (buttons[7].getText()=="O")&&
                (buttons[8].getText()=="O")
                )return true;
        if(
                (buttons[0].getText()=="O")&&
                (buttons[3].getText()=="O")&&
                (buttons[6].getText()=="O")
                )return true;
        if(
                (buttons[1].getText()=="O")&&
                (buttons[4].getText()=="O")&&
                (buttons[7].getText()=="O")
                )return true;
        if(
                (buttons[2].getText()=="O")&&
                (buttons[5].getText()=="O")&&
                (buttons[8].getText()=="O")
                )return true;
        if(
                (buttons[0].getText()=="O")&&
                (buttons[4].getText()=="O")&&
                (buttons[8].getText()=="O")
                )return true;
        if(
                (buttons[2].getText()=="O")&&
                (buttons[4].getText()=="O")&&
                (buttons[6].getText()=="O")
                )return true;
        return false;
    }
    
    public boolean checkXwin(){
        if(
                (buttons[0].getText()=="X")&&
                (buttons[1].getText()=="X")&&
                (buttons[2].getText()=="X")
                )return true;
        
        if(
                (buttons[3].getText()=="X")&&
                (buttons[4].getText()=="X")&&
                (buttons[5].getText()=="X")
                )return true;
        if(
                (buttons[6].getText()=="X")&&
                (buttons[7].getText()=="X")&&
                (buttons[8].getText()=="X")
                )return true;
        if(
                (buttons[0].getText()=="X")&&
                (buttons[3].getText()=="X")&&
                (buttons[6].getText()=="X")
                )return true;
        if(
                (buttons[1].getText()=="X")&&
                (buttons[4].getText()=="X")&&
                (buttons[7].getText()=="X")
                )return true;
        if(
                (buttons[2].getText()=="X")&&
                (buttons[5].getText()=="X")&&
                (buttons[8].getText()=="X")
                )return true;
        if(
                (buttons[0].getText()=="X")&&
                (buttons[4].getText()=="X")&&
                (buttons[8].getText()=="X")
                )return true;
        if(
                (buttons[2].getText()=="X")&&
                (buttons[4].getText()=="X")&&
                (buttons[6].getText()=="X")
                )return true;
        return false;
    }
    
    
    
    public void setButtons(boolean set){
        for(JButton button : buttons){
                button.setEnabled(set);
            }
    }
}
