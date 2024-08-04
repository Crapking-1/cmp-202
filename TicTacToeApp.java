import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

class TicTacToeGame {
    private char[][] board;
    private char currentPlayer;
    private boolean gameWon;
    private boolean gameDraw;

    public TicTacToeGame() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameWon = false;
        gameDraw = false;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public boolean placeMark(int row, int col) {
        if (row >= 0 && col >= 0 && row < 3 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRow(i) || checkColumn(i)) return true;
        }
        return checkDiagonals();
    }

    private boolean checkRow(int row) {
        return board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer;
    }

    private boolean checkColumn(int col) {
        return board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer;
    }

    private boolean checkDiagonals() {
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    public boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') return false;
            }
        }
        return !gameWon;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public boolean isGameDraw() {
        return gameDraw;
    }

    public void setGameDraw(boolean gameDraw) {
        this.gameDraw = gameDraw;
    }

    public void resetGame() {
        currentPlayer = 'X';
        gameWon = false;
        gameDraw = false;
        initializeBoard();
    }
}

public class TicTacToeApp {
    private TicTacToeGame game;
    private JFrame frame;
    private JButton[][] buttons;
    private JTextArea resultArea;

    public TicTacToeApp() {
        game = new TicTacToeGame();
        frame = new JFrame("Tic-Tac-Toe");
        buttons = new JButton[3][3];
        resultArea = new JTextArea();
    }

    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(row, col);
                    }
                });
                boardPanel.add(buttons[i][j]);
            }
        }

        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton replayButton = new JButton("Replay");
        replayButton.setFont(new Font("Arial", Font.PLAIN, 18));
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(resultArea, BorderLayout.CENTER);
        bottomPanel.add(replayButton, BorderLayout.EAST);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void handleButtonClick(int row, int col) {
        if (game.placeMark(row, col)) {
            buttons[row][col].setText(String.valueOf(game.getCurrentPlayer()));
            if (game.checkForWin()) {
                game.setGameWon(true);
                resultArea.setText("Player " + game.getCurrentPlayer() + " wins!");
                writeToFile("Player " + game.getCurrentPlayer() + " wins!");
                disableButtons();
            } else if (game.checkForDraw()) {
                game.setGameDraw(true);
                resultArea.setText("It's a draw!");
                writeToFile("It's a draw!");
                disableButtons();
            } else {
                game.changePlayer();
            }
        }
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        game.resetGame();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("-");
                buttons[i][j].setEnabled(true);
            }
        }
        resultArea.setText("");
    }

    private void writeToFile(String content) {
        try (FileWriter writer = new FileWriter("tic_tac_toe_results.txt", true)) {
            writer.write(content + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeApp().createAndShowGUI();
            }
        });
    }
}
