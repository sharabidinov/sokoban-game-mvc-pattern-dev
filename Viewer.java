import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Viewer {
    private JPanel panelCenter;
    private GameCanvas gameCanvas;
    private ControlCanvas controlCanvas;
    private JFrame frame;
    public HashMap<String, RectangularButton> rectangularButtonHashMap;
    public HashMap<String, CircleButton> circleButtonHashMap;

    public Viewer() {

        Controller controller = new Controller(this);
        Model model = controller.getModel();

        gameCanvas = new GameCanvas(model);
        gameCanvas.setBounds(50, 100, 700, 600);
        gameCanvas.setFocusable(true);
        controlCanvas = new ControlCanvas(model);
        controlCanvas.setBounds(750, 100, 350, 600);

        BackgroundPanel backgroundPanel = new BackgroundPanel(gameCanvas.getBackgroundImage());

        frame = new JFrame("Sokoban Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rectangularButtonHashMap = new HashMap<>();
        circleButtonHashMap = new HashMap<>();

        panelCenter = new JPanel(null);
        panelCenter.add(controlCanvas);
        panelCenter.add(gameCanvas);
        panelCenter.add(backgroundPanel);
        backgroundPanel.setBounds(0, 0, 1200, 800);

        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.add(panelCenter);
        frame.setVisible(true);

        gameCanvas.addKeyListener(controller);
        gameCanvas.addMouseListener(controller);
        controlCanvas.addMouseListener(controller);
    }

    public void update() {
        gameCanvas.repaint();
    }

    public void updateTheme(PlayerTheme themeType) {

        gameCanvas.setTheme(themeType);
    }

    public void showWonDialog() {
        JOptionPane.showMessageDialog(null, "Congratulations, you have successfully passed the level!!!");
    }

    public HashMap<String, RectangularButton> getRectangularButtonHashMap() {
        return rectangularButtonHashMap;
    }

    public HashMap<String, CircleButton> getCircleButtonHashMap() {
        return circleButtonHashMap;
    }
}
