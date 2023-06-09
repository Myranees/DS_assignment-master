package TTT;

import java.io.Serializable;

public class GameState implements Serializable {
    public char[][] board;
    public int numMoves;
    public int roundgame;
    public int player;
    public int engine;
    public PlayerAccount playerAccount;

    private static final long serialVersionUID = 1L;
    private String game;

    public GameState(String game,char[][] board,int numMoves,int roundgame,int player,int engine,PlayerAccount playerAccount) {
        this.board = board;
        this.game = game;
        this.numMoves=numMoves;
        this.roundgame=roundgame;
        this.player=player;
        this.engine=engine;
        this.playerAccount = playerAccount;
    }

    public String getGame(){
        return game;
    }
}
