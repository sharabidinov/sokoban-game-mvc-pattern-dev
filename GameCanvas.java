import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.HashMap;

@SuppressWarnings("serial")
public class GameCanvas extends JPanel {
    private Model model;
    public HashMap<String, Image> imageHashMap;
    private int cellWidthHeight;
    private Image currentImage;

    private JLabel levelName;
    private JLabel countSteps;

    public GameCanvas(Model model) {
        this.model = model;
        setLayout(null);
        setOpaque(false);
        imageHashMap = new HashMap<>();
        cellWidthHeight = 50;
        loadImages();

        countSteps = new JLabel("Steps: 0");
        countSteps.setSize(100, 50);
        countSteps.setForeground(Color.white);
        countSteps.setFont(new Font("Arial", Font.BOLD, 20));

        levelName = new JLabel("Level " + model.getLevelName());
        levelName.setSize(100, 50);
        levelName.setForeground(Color.white);
        levelName.setFont(new Font("Arial", Font.BOLD, 20));

        Timer timer = model.getTimer();
        LevelName levelName = model.getLevelName();
        CountSteps countSteps = model.getCountSteps();

        timer.setLocation(100, 0);
        levelName.setLocation(250, 0);
        countSteps.setLocation(400, 0);

        add(timer);
        add(levelName);
        add(countSteps);
    }

    private void loadImages() {

        Image boxImage = createImage("res/images/boy_box.png");
        Image errorImage = createImage("res/images/error.png");
        Image gamerDownImage = createImage("res/images/boy_front.png");
        Image gamerLeftImage = createImage("res/images/boy_left.png");
        Image gamerRightImage = createImage("res/images/boy_right.png");
        Image gamerUpImage = createImage("res/images/boy_back.png");
        Image groundImage = createImage("res/images/boy_cell.png");
        Image targetImage = createImage("res/images/boy_target.png");
        Image wallImage = createImage("res/images/boy_wall.png");
        Image boxOnTargetImage = createImage("res/images/boy_box_on_target.png");
        Image backgroundImage = createImage("res/images/kyrgyz_background.png");
        imageHashMap.put("boxImage", boxImage);
        imageHashMap.put("errorImage", errorImage);
        imageHashMap.put("gamerDownImage", gamerDownImage);
        imageHashMap.put("gamerLeftImage", gamerLeftImage);
        imageHashMap.put("gamerRightImage", gamerRightImage);
        imageHashMap.put("gamerUpImage", gamerUpImage);
        imageHashMap.put("groundImage", groundImage);
        imageHashMap.put("targetImage", targetImage);
        imageHashMap.put("wallImage", wallImage);
        imageHashMap.put("boxOnTargetImage", boxOnTargetImage);
        imageHashMap.put("backgroundImage", backgroundImage);
        currentImage = imageHashMap.get("gamerDownImage");

    }

    private Image createImage(String path) {
        ImageIcon icon = createImageIcon(path);
        if (icon != null) {
            return icon.getImage();
        }
        return null;
    }

    protected static ImageIcon createImageIcon(String path) {
        URL imgURL = GameCanvas.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        boolean stateGame = model.getState();
        if (stateGame) {
            rotateGamer();
            drawDesktop(g);
        } else {
            drawError(g);
        }
    }

    public void drawDesktop(Graphics g) {
        int start = 50;
        int x = start;
        int y = start;
        int offset = 0;
        int[][] desktop = model.getDesktop();
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    g.drawImage(currentImage, x, y, cellWidthHeight, cellWidthHeight, this);
                } else if (desktop[i][j] == 2) {
                    g.drawImage(imageHashMap.get("wallImage"), x, y, cellWidthHeight, cellWidthHeight, this);
                } else if (desktop[i][j] == 3) {
                    if (isTargetCell(i, j)) {
                        g.drawImage(imageHashMap.get("boxOnTargetImage"), x, y, cellWidthHeight, cellWidthHeight, this);
                    } else {
                        g.drawImage(imageHashMap.get("boxImage"), x, y, cellWidthHeight, cellWidthHeight, this);
                    }
                } else if (desktop[i][j] == 4) {
                    g.drawImage(imageHashMap.get("targetImage"), x, y, cellWidthHeight, cellWidthHeight, this);
                } else if (desktop[i][j] == 0) {
                    g.drawImage(imageHashMap.get("groundImage"), x, y, cellWidthHeight, cellWidthHeight, this);
                }
                x = x + cellWidthHeight + offset;
            }
            x = start;
            y = y + cellWidthHeight + offset;
        }
    }

    public void rotateGamer() {
        String move = model.getDirection();
        if (move == null) {
            return;
        }
        switch (move) {
            case "Left":
                currentImage = imageHashMap.get("gamerLeftImage");
                break;
            case "Right":
                currentImage = imageHashMap.get("gamerRightImage");
                break;
            case "Up":
                currentImage = imageHashMap.get("gamerUpImage");
                break;
            case "Down":
                currentImage = imageHashMap.get("gamerDownImage");
                break;
        }
    }

    public void drawError(Graphics g) {
        Font font = new Font("Arial", Font.BOLD, 50);
        g.drawImage(imageHashMap.get("errorImage"), 300, 170, null);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("Initialization Error!", 200, 100);
    }

    public void setTheme(PlayerTheme themeType) {
        Image newBoxImage = themeType.getBoxImage();
        Image newGamerDownImage = themeType.getFrontPlayerImage();
        Image newGamerLeftImage = themeType.getLeftPlayerImage();
        Image newGamerRightImage = themeType.getRightPlayerImage();
        Image newGamerUpImage = themeType.getBackPlayerImage();
        Image newGroundImage = themeType.getGroundImage();
        Image newTargetImage = themeType.getTargetImage();
        Image newWallImage = themeType.getWallImage();
        Image newBoxOnTargetImage = themeType.getBoxOnTargetImage();
        imageHashMap.put("boxImage", newBoxImage);
        imageHashMap.put("gamerDownImage", newGamerDownImage);
        imageHashMap.put("gamerLeftImage", newGamerLeftImage);
        imageHashMap.put("gamerRightImage", newGamerRightImage);
        imageHashMap.put("gamerUpImage", newGamerUpImage);
        imageHashMap.put("groundImage", newGroundImage);
        imageHashMap.put("targetImage", newTargetImage);
        imageHashMap.put("wallImage", newWallImage);
        imageHashMap.put("boxOnTargetImage", newBoxOnTargetImage);
        repaint();
    }

    public int getCellWidthHeight() {
        return cellWidthHeight;
    }

    public Image getBackgroundImage() {
        return imageHashMap.get("backgroundImage");
    }

    public boolean isTargetCell(int x, int y) {
        int[][] arrayIndexes = model.getArrayIndexes();
        for (int z = 0; z < arrayIndexes[0].length; z++) {
            if (arrayIndexes[0][z] == x && arrayIndexes[1][z] == y) {
                return true;
            }
        }
        return false;
    }
}
