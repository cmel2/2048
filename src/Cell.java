import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    private Color bgColor, textColor;
    private int value;

    public Cell() {
        this(0);
    }

    public Cell(int value) {
        setValue(value);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(bgColor == null)
            bgColor = Color.GRAY;

        g.setColor(bgColor);
        g.fillRoundRect(0,0,getWidth(),getHeight(), 5, 5);

        String number = String.valueOf(value);
        Font f = new Font("Aptos", Font.BOLD, (int) ((getHeight()+getWidth())/4.5));

        FontMetrics fontMetrics = g.getFontMetrics(f);
        int numberWidth = fontMetrics.stringWidth(number);
        int numberHeight = fontMetrics.getHeight();

        g.setFont(f);
        g.setColor(textColor);
        g.drawString(number, (getWidth() - numberWidth) / 2, (getHeight() - numberHeight) / 2 + fontMetrics.getAscent());
    }

    public void setValue(int value) {
        if(value < 0)
            throw new IllegalArgumentException("Value cannot be negative!");

        switch (value) {
            case(0) -> {
                bgColor = null;
                setTextColor(Color.GRAY);
            }
            case (2) -> {
                setBgColor(new Color(238, 228, 218));
                setTextColor(new Color(119, 110, 101));
            }
            case (4) -> {
                setBgColor(new Color(238, 225, 201));
                setTextColor(new Color(119, 110, 101));
            }
            case (8) -> {
                setBgColor(new Color(232, 180, 129));
                setTextColor(Color.WHITE);
            }
            case (16) -> {
                setBgColor(new Color(232, 154, 108));
                setTextColor(Color.WHITE);
            }
            case (32) -> {
                setBgColor(new Color(230, 131, 102));
                setTextColor(Color.WHITE);
            }
            case (64) -> {
                setBgColor(new Color(228, 104, 72));
                setTextColor(Color.WHITE);
             }
            case (128) -> {
                setBgColor(new Color(232, 209, 127));
                setTextColor(Color.WHITE);
            }
            case (256) -> {
                setBgColor(new Color(232, 205, 112));
                setTextColor(Color.WHITE);
            }
            case (512) -> {
                setBgColor(new Color(230, 201, 100));
                setTextColor(Color.WHITE);
            }
            case (1024) -> {
                setBgColor(new Color(237, 197, 63));
                setTextColor(Color.WHITE);
            }
            case (2048) -> {
                setBgColor(new Color(232, 206, 8));
                setTextColor(Color.WHITE);
            }
        }
        this.value = value;

        repaint();
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
