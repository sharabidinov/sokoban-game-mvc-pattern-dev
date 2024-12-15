import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class CountSteps extends JLabel {

    private Model model;
    private int steps;

    public CountSteps(Model model) {
        this.model = model;
        setText("Steps: 0");
        setSize(100, 50);
        setForeground(Color.white);
        setFont(new Font("Arial", Font.BOLD, 20));
    }

    public void setSteps() {
        steps = model.getSteps();
        setText("Steps: " + steps);
    }
}
