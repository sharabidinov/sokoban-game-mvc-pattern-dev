import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class LevelName extends JLabel {

    private Levels level;

    public LevelName() {
        level = new Levels();
        setForeground(Color.white);
        setText("Level " + level.getLevelNumber());
        setSize(100, 50);
        setFont(new Font("Arial", Font.BOLD, 20));

    }

}
