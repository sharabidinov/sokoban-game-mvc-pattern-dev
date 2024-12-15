import java.awt.Image;
import javax.swing.JPanel;
import java.awt.Graphics;
@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel {

    private Image backgroundImage;

    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void setBackgroundImage(Image image) {
        backgroundImage = image;
    }
}