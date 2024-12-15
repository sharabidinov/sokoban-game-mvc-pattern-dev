import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;

public class RectangularButton{
    private int width, height;
    private String label;
    private int x, y;
    private Color color;

    public RectangularButton(int x, int y, int width, int height, Color color, String label) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = width;
        this.height = height;
        this.label = label;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);

        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);

        if (label != null) {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            FontMetrics metrics = g.getFontMetrics();
            int textX = x + (width - metrics.stringWidth(label)) / 2;
            int textY = y + (height - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(label, textX, textY);
        }
    }

    public boolean contains(int mouseX, int mouseY) {
        return x <= mouseX && mouseX <= x + width && y <= mouseY && mouseY <= y + height;
    }

    public String getLabel() {
        return label;
    }
}
