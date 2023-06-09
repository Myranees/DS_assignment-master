/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TTT;

/**
 *
 * @author baest
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TTT5x5 extends JFrame implements ActionListener {
    private char[][] gameBoard;
    private static final int BOARD_SIZE = 5;
    private static final char PLAYER_SYMBOL = 'X';
    private static final char OPPONENT_SYMBOL = 'O';
    private static final int WINNING_LENGTH = 3;
    private int playerScore;
    private int opponentScore;
    private int movesCount;
    private char currentPlayer;
    private static final int MAX_DEPTH = 5;
    private JButton[][] buttons;
    private JLabel titleLabel;
    private JLabel playerScoreLabel;
    private JLabel opponentScoreLabel;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private String difficultyLevel;
    private int scoreBoard = 0;
    private int difficulty;

    
    private void resetGame() {
    int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);

    if (option == JOptionPane.YES_OPTION) {
        initializeGameBoard();
        movesCount = 0;
        currentPlayer = PLAYER_SYMBOL;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Thanks for playing!");

        // Print the score
        JOptionPane.showMessageDialog(this, "Score: " + scoreBoard);

        // Exit the game
        System.exit(0);
    }
}


    
    public TTT5x5() {
        this.setSize(400, 400);
        this.
        gameBoard = new char[BOARD_SIZE][BOARD_SIZE];
        initializeGameBoard();
        playerScore = 0;
        opponentScore = 0;
        movesCount = 0;
        currentPlayer = PLAYER_SYMBOL;
        
        
        
        JPanel difficultyPanel = new JPanel(new GridLayout(1, 3));
    easyButton = new JButton("Easy");
    easyButton.addActionListener(this);
    mediumButton = new JButton("Medium");
    mediumButton.addActionListener(this);
    hardButton = new JButton("Hard");
    hardButton.addActionListener(this);
    difficultyPanel.add(easyButton);
    difficultyPanel.add(mediumButton);
    difficultyPanel.add(hardButton);
    JOptionPane.showMessageDialog(this, difficultyPanel, "Choose Difficulty", JOptionPane.INFORMATION_MESSAGE);

    //add(difficultyPanel, BorderLayout.SOUTH);

    setTitle("Tic-Tac-Toe");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());

        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }

        titleLabel = new JLabel("Tic-Tac-Toe");
        titleLabel.setFont(new Font("Ink Free", Font.BOLD, 75));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        playerScoreLabel = new JLabel("Player: " + playerScore);
        opponentScoreLabel = new JLabel("Opponent: " + opponentScore);
        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.add(playerScoreLabel, BorderLayout.WEST);
        scorePanel.add(opponentScoreLabel, BorderLayout.EAST);

        add(titleLabel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.SOUTH);

        


    pack();
    setVisible(true);
    }
    
    //try add difficulty here
    public TTT5x5(int difficulty) {
        gameBoard = new char[BOARD_SIZE][BOARD_SIZE];
        initializeGameBoard();
        playerScore = 0;
        opponentScore = 0;
        movesCount = 0;
        currentPlayer = PLAYER_SYMBOL;
        this.difficulty = difficulty;
        
        
        
//        JPanel difficultyPanel = new JPanel(new GridLayout(1, 3));
//    easyButton = new JButton("Easy");
//    easyButton.addActionListener(this);
//    mediumButton = new JButton("Medium");
//    mediumButton.addActionListener(this);
//    hardButton = new JButton("Hard");
//    hardButton.addActionListener(this);
//    difficultyPanel.add(easyButton);
//    difficultyPanel.add(mediumButton);
//    difficultyPanel.add(hardButton);
//    JOptionPane.showMessageDialog(this, difficultyPanel, "Choose Difficulty", JOptionPane.INFORMATION_MESSAGE);

    //add(difficultyPanel, BorderLayout.SOUTH);

    setTitle("Tic-Tac-Toe");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());

        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }

        titleLabel = new JLabel("Tic-Tac-Toe");
        titleLabel.setFont(new Font("Ink Free", Font.BOLD, 75));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        playerScoreLabel = new JLabel("Player: " + playerScore);
        opponentScoreLabel = new JLabel("Opponent: " + opponentScore);
        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.add(playerScoreLabel, BorderLayout.WEST);
        scorePanel.add(opponentScoreLabel, BorderLayout.EAST);

        add(titleLabel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.SOUTH);

        


    pack();
    setVisible(true);
    }

    public void initializeGameBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                gameBoard[i][j] = ' ';
            }
        }
    }

    public void makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            gameBoard[row][col] = currentPlayer;
            movesCount++;
            buttons[row][col].setText(String.valueOf(currentPlayer));
            switchPlayer();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move. Please try again.");
        }
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }

        if (gameBoard[row][col] != ' ') {
            return false;
        }

        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_SYMBOL) ? OPPONENT_SYMBOL : PLAYER_SYMBOL;
    }

    private boolean checkWin(char symbol) {
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            int count = 0;
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (gameBoard[i][j] == symbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == WINNING_LENGTH) {
                    return true;
                }
            }
        }

        // Check columns
        for (int j = 0; j < BOARD_SIZE; j++) {
            int count = 0;
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (gameBoard[i][j] == symbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == WINNING_LENGTH) {
                    return true;
                }
            }
        }

        // Check diagonals
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                // Check diagonals starting at (i, j)
                if (i + WINNING_LENGTH <= BOARD_SIZE && j + WINNING_LENGTH <= BOARD_SIZE) {
                    int count = 0;
                    for (int k = 0; k < WINNING_LENGTH; k++) {
                        if (gameBoard[i + k][j + k] == symbol) {
                            count++;
                        } else {
                            break;
                        }
                        if (count == WINNING_LENGTH) {
                            return true;
                        }
                    }
                }

                // Check diagonals starting at (i, j)
                if (i - WINNING_LENGTH + 1 >= 0 && j + WINNING_LENGTH <= BOARD_SIZE) {
                    int count = 0;
                    for (int k = 0; k < WINNING_LENGTH; k++) {
                        if (gameBoard[i - k][j + k] == symbol) {
                            count++;
                        } else {
                            break;
                        }
                        if (count == WINNING_LENGTH) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean isBoardFull() {
        return movesCount == BOARD_SIZE * BOARD_SIZE;
    }

   // private void resetGame() {
        //initializeGameBoard();
        //movesCount = 0;
        //currentPlayer = PLAYER_SYMBOL;
        //for (int i = 0; i < BOARD_SIZE; i++) {
            //for (int j = 0; j < BOARD_SIZE; j++) {
                //buttons[i][j].setText("");
            //}
        //}
    //}

    

    @Override
public void actionPerformed(ActionEvent e) {
    JButton button = (JButton) e.getSource();

//    if (button == easyButton) {
//        difficultyLevel = "Easy";
//        makeEasyMove(); // Add code to make an easy move immediately
//    } else if (button == mediumButton) {
//        difficultyLevel = "Medium";
//    } else if (button == hardButton) {
//        difficultyLevel = "Hard";
//    } else {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (button == buttons[i][j]) {
                    makeMove(i, j);

                    if (checkWin(PLAYER_SYMBOL)) {
                        JOptionPane.showMessageDialog(TTT5x5.this, "Player wins!");
                        playerScore++;
                        scoreBoard++; // Increment the score
                        playerScoreLabel.setText("Player: " + playerScore);
//                        resetGame();
                    } else if (isBoardFull()) {
                        JOptionPane.showMessageDialog(TTT5x5.this, "It's a draw!");
//                        resetGame();
                    } else {
                        // Opponent's turn
                        if (difficulty ==1) {
                            makeEasyMove(); // Add code to make a medium move
                        } else if (difficulty ==2) {
                            makeMediumMove(); // Add code to make a medium move
                        } else if (difficulty==3) {
                            makeHardMove(); // Add code to make a hard move
                        }
                        if (checkWin(OPPONENT_SYMBOL)) {
                            JOptionPane.showMessageDialog(TTT5x5.this, "Opponent wins!");
                            opponentScore++;
                            opponentScoreLabel.setText("Opponent: " + opponentScore);
//                            resetGame();
                        } else if (isBoardFull()) {
                            JOptionPane.showMessageDialog(TTT5x5.this, "It's a draw!");
//                            resetGame();
                        }
                    }
                    return;
                }
            }
//        }
    }
}

    
    private void makeEasyMove() {
    Random random = new Random();
    int row;
    int col;

    do {
        row = random.nextInt(BOARD_SIZE);
        col = random.nextInt(BOARD_SIZE);
    } while (gameBoard[row][col] != ' ');

    makeMove(row, col);
    
}


    private void makeMediumMove() {
    // Check if AI can win in the next move
    if (makeOptimalMove(OPPONENT_SYMBOL)) {
        return;
    }

    // Check if AI needs to block the player from winning
    if (makeOptimalMove(PLAYER_SYMBOL)) {
        return;
    }

    // If no immediate winning or blocking move, make a random move
    makeEasyMove();
}

    private boolean makeOptimalMove(char symbol) {
    // Check for winning moves or blocking moves
    for (int row = 0; row < BOARD_SIZE; row++) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (gameBoard[row][col] == ' ') {
                if (checkWinningMove(row, col, symbol)) {
                    makeMove(row, col);
                    
                    return true;
                }
            }
        }
    }
    return false;
}

    private boolean checkWinningMove(int row, int col, char symbol) {
    gameBoard[row][col] = symbol; // Try the move

    if (checkWin(symbol)) {
        gameBoard[row][col] = ' '; // Revert the move
        return true;
    }

    gameBoard[row][col] = ' '; // Revert the move
    return false;
}

    private void makeHardMove() {
    int[] bestMove = minimax(0, currentPlayer, Integer.MIN_VALUE, Integer.MAX_VALUE);
    int row = bestMove[0];
    int col = bestMove[1];

    makeMove(row, col);
    
}


    private int[] minimax(int depth, char player, int alpha, int beta) {
    // Base cases: check if the game is over or reached the maximum depth
    if (checkWin(PLAYER_SYMBOL)) {
        return new int[] { -1, -1, depth - 10 };
    } else if (checkWin(OPPONENT_SYMBOL)) {
        return new int[] { -1, -1, 10 - depth };
    } else if (isBoardFull() || depth == MAX_DEPTH) {
        return new int[] { -1, -1, 0 };
    }

    int bestRow = -1;
    int bestCol = -1;
    int bestScore;

    java.util.List<int[]> availableMoves = new ArrayList<>();

    for (int row = 0; row < BOARD_SIZE; row++) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (gameBoard[row][col] == ' ') {
                availableMoves.add(new int[] { row, col });
            }
        }
    }

    if (player == OPPONENT_SYMBOL) {
        bestScore = Integer.MIN_VALUE;
        Collections.shuffle(availableMoves); // Randomize the order of available moves
        for (int[] move : availableMoves) {
            int row = move[0];
            int col = move[1];
            gameBoard[row][col] = player;
            int score = minimax(depth + 1, PLAYER_SYMBOL, alpha, beta)[2];
            gameBoard[row][col] = ' ';

            if (score > bestScore) {
                bestScore = score;
                bestRow = row;
                bestCol = col;
            }

            alpha = Math.max(alpha, bestScore);
            if (alpha >= beta) {
                break; // Beta pruning
            }
        }
    } else {
        bestScore = Integer.MAX_VALUE;
        Collections.shuffle(availableMoves); // Randomize the order of available moves
        for (int[] move : availableMoves) {
            int row = move[0];
            int col = move[1];
            gameBoard[row][col] = player;
            int score = minimax(depth + 1, OPPONENT_SYMBOL, alpha, beta)[2];
            gameBoard[row][col] = ' ';

            if (score < bestScore) {
                bestScore = score;
                bestRow = row;
                bestCol = col;
            }

            beta = Math.min(beta, bestScore);
            if (alpha >= beta) {
                break; // Alpha pruning
            }
        }
    }

    return new int[] { bestRow, bestCol, bestScore };
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TTT5x5();
            }
        });
    }
}


