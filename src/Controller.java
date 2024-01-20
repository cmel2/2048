import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Controller implements KeyListener {
    private View v;
    private Model m;

    public Controller(View v, Model m) {
        this.v = v;
        this.m = m;
        new Frame(v);

        v.addActionListener(e -> reset());
        v.addKeyListener(this);

        updateView();
        m.printIntArray();
    }

    private void reset() {
        Arrays.stream(m.getBoard()).forEach(row -> Arrays.fill(row, 0));
        m.addRandomCell();
        m.addRandomCell();
        m.setScore(0);

        updateView();
        v.requestFocus();
        m.printIntArray();
        System.out.println("Reset");
    }

    private void updateView() {
        v.setBoard(m.getBoard());
        v.setScore(m.getScore());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println();
        switch (e.getKeyCode()) {
            case (37) -> m.move(Direction.LEFT);
            case (38) -> m.move(Direction.UP);
            case (39) -> m.move(Direction.RIGHT);
            case (40) -> m.move(Direction.DOWN);
        }
        m.printIntArray();

        updateView();

        switch (m.getGameStatus()) {
            case RUNNING -> {}
            case WIN -> endGame(true);
            case LOSS -> endGame(false);
        }
    }

    private void endGame(boolean win) {
        int choice = v.showEndScreen(win, m.getScore());

        if(choice == 0)
            reset();

        if(choice == 1)
            System.exit(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}
}
