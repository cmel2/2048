import java.util.Arrays;
import java.util.Random;

public class Model {
    private final int BOARD_SIZE = 4;
    private int[][] board;
    private int score;

    public Model() {
        board = new int[BOARD_SIZE][BOARD_SIZE];

        this.score = 0;
        addRandomCell();
        addRandomCell();
    }

    public boolean checkWin() {
        return Arrays.stream(board).anyMatch(row -> Arrays.stream(row).anyMatch(cell -> cell == 2048));
    }

    public boolean isBoardFilled() {
        return Arrays.stream(board).noneMatch(row -> Arrays.stream(row).anyMatch(cell -> cell == 0));
    }

    public boolean checkLose() {
        if (!isBoardFilled())
            return false;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE - 1; col++) {
                if (board[row][col] == board[row][col + 1] || board[col][row] == board[col + 1][row]) {
                    return false;
                }
            }
        }

        return true;
    }

    public void move(Direction direction) {
        int[][] before = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                before[i][j] = board[i][j];
            }
        }

        System.out.println(direction);
        for (int i = 0; i < BOARD_SIZE; i++) {
            int[] line = getLine(direction, i);
            line = merge(line);
            setLine(direction, i, line);
        }

        if(!Arrays.deepEquals(before, board))
            addRandomCell();
    }

    private int[] getLine(Direction direction, int index) {
        int[] line = new int[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            switch (direction) {
                case UP -> line[i] = board[i][index];
                case DOWN -> line[i] = board[BOARD_SIZE - 1 - i][index];
                case LEFT -> line[i] = board[index][i];
                case RIGHT -> line[i] = board[index][BOARD_SIZE - 1 - i];
            }
        }
        return line;
    }

    private void setLine(Direction direction, int index, int[] line) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            switch (direction) {
                case UP -> board[i][index] = line[i];
                case DOWN -> board[BOARD_SIZE - 1 - i][index] = line[i];
                case LEFT -> board[index][i] = line[i];
                case RIGHT -> board[index][BOARD_SIZE - 1 - i] = line[i];
            }
        }
    }

    private int[] merge(int[] line) {
        int[] merged = new int[BOARD_SIZE];
        int mergedIndex = 0;

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (line[i] != 0) {
                if (mergedIndex > 0 && merged[mergedIndex - 1] == line[i]) {
                    merged[mergedIndex - 1] *= 2;
                    setScore(score + merged[mergedIndex - 1]);
                } else {
                    merged[mergedIndex++] = line[i];
                }
            }
        }

        while (mergedIndex < BOARD_SIZE) {
            merged[mergedIndex++] = 0;
        }

        return merged;
    }

    public GameState getGameStatus() {
        if (checkWin())
            return GameState.WIN;

        if (checkLose())
            return GameState.LOSS;

        return GameState.RUNNING;
    }

    public void addRandomCell() {
        if (isBoardFilled())
            return;

        Random rand = new Random();

        int randomValue = rand.nextInt(10) == 9 ? 4 : 2;
        int randomPosition1 = rand.nextInt(4);
        int randomPosition2 = rand.nextInt(4);

        while (board[randomPosition1][randomPosition2] != 0) {
            randomPosition1 = rand.nextInt(4);
            randomPosition2 = rand.nextInt(4);
        }

        board[randomPosition1][randomPosition2] = randomValue;
    }

    public void printIntArray() {
        Arrays.stream(board)
                .forEach(row -> {
                    Arrays.stream(row).forEach(e -> System.out.print(e + " "));
                    System.out.println();
                });
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int[][] getBoard() {
        return board;
    }
}
