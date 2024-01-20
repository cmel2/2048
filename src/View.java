import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JPanel {

    private int score = 0;
    private Cell[][] board;
    private JButton resetButton;
    private JLabel scoreText;


    public View() {
        setLayout(new BorderLayout());

        JPanel cells = new JPanel(new GridLayout(4, 4, 10, 10));
        cells.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
        cells.setBackground(Color.WHITE);

        JPanel scoreboardControls = new JPanel();
        scoreboardControls.setLayout(new BoxLayout(scoreboardControls, BoxLayout.LINE_AXIS));
        scoreboardControls.setBackground(Color.WHITE);

        resetButton = new JButton("Reset");
        scoreboardControls.add(resetButton);
        scoreText = new JLabel("Score: " + score);
        scoreText.setFont(new Font("Arial", Font.BOLD, 30));
        scoreboardControls.add(scoreText, BorderLayout.EAST);

        this.board = new Cell[4][4];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                cells.add(board[i][j] = new Cell());
            }
        }

        add(cells, BorderLayout.CENTER);
        add(scoreboardControls, BorderLayout.SOUTH);

        setFocusable(true);
    }

    public void setBoard(int[][] valueBoard) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setValue(valueBoard[i][j]);
            }
        }
    }

    public void addActionListener(ActionListener listener) {
        resetButton.addActionListener(listener);
    }

    public int showEndScreen(boolean win, int score) {
        String[] options = new String[]{"Try Again", "Exit"};
        String message = win ? "You won!" : "Game over!";
        message += "    Your score is " + score;

        return JOptionPane.showOptionDialog(this, message, "2048",
                JOptionPane.OK_CANCEL_OPTION, win ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE, null, options, options[0]);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        this.scoreText.setText("Score: " + score);
    }
}
