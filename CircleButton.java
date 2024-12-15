import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

public class CircleButton {
    private int radius;
    private Image image;
    private int x, y;
    private Color color;
    private String label;

    public CircleButton(int x, int y, int radius, Color color, Image image, String label) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.radius = radius;
        this.image = image;
        this.label = label;

    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, radius * 2, radius * 2);

//        g.setColor(Color.WHITE);
//        g.drawOval(x, y, radius * 2, radius * 2);

        if (image != null) {
            g.drawImage(image, x, y, radius * 2, radius * 2, null);
        }
    }

    public boolean contains(int mouseX, int mouseY) {
        int centerX = x + radius;
        int centerY = y + radius;
        return Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2) <= Math.pow(radius, 2);
    }

    public String getLabel() {
        return label;
    }
}
