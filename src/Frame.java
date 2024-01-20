import javax.swing.*;

public class Frame extends JFrame {
    public Frame(View view) {
        setTitle("2048");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        add(view);

        setVisible(true);
    }
}
