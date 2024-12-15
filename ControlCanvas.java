import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.util.HashMap;

@SuppressWarnings("serial")
public class ControlCanvas extends JPanel {

    public HashMap<String, RectangularButton> rectangularButtonHashMap;
    public HashMap<String, CircleButton> circleButtonHashMap;
    public HashMap<String, Image> imageHashMap;
    private Color buttonColor;

    public ControlCanvas(Model model) {
        setLayout(null);
        setOpaque(false);
        buttonColor = new Color(0, 0, 0, 110);
        imageHashMap = new HashMap<>();
        rectangularButtonHashMap = new HashMap<>();
        circleButtonHashMap = new HashMap<>();
        loadImages();
    }

    private void loadImages() {
        Image moveBackwardImage = createImage("res/images/move_backward.png");
        Image moveForwardImage = createImage("res/images/move_forward.png");
        Image startOverImage = createImage("res/images/start_over.png");
        Image soundImage = createImage("res/images/sound.png");
        Image volumeImage = createImage("res/images/volume.png");
        Image settingsImage = createImage("res/images/volume.png");

        imageHashMap.put("moveBackwardImage", moveBackwardImage);
        imageHashMap.put("moveForwardImage", moveForwardImage);
        imageHashMap.put("startOverImage", startOverImage);
        imageHashMap.put("soundImage", soundImage);
        imageHashMap.put("volumeImage", volumeImage);
        imageHashMap.put("settingsImage", settingsImage);
    }

    private Image createImage(String path) {
        ImageIcon icon = GameCanvas.createImageIcon(path);
        if (icon != null) {
            return icon.getImage();
        }
        return null;
    }

    private void initializeButtons(int panelWidth, int panelHeight) {

        int buttonWidth = 290;
        int buttonHeight = 60;
        int largeButtonHeight = 80;
        int buttonSpacing = 20;

        int xCenter = (panelWidth - buttonWidth) / 2;

        RectangularButton startButton = new RectangularButton(xCenter, 50, buttonWidth, buttonHeight, buttonColor,
                "Start");
        RectangularButton exitButton = new RectangularButton(xCenter, 50 + buttonHeight + buttonSpacing, buttonWidth,
                buttonHeight, buttonColor, "Exit");

        int prevNextYPosition = 50 + 2 * (buttonHeight + buttonSpacing);
        int smallButtonWidth = 120;
        int smallButtonSpacing = (panelWidth - 2 * smallButtonWidth) / 3;

        RectangularButton prevLevelButton = new RectangularButton(
                smallButtonSpacing,
                prevNextYPosition,
                smallButtonWidth,
                buttonHeight,
                buttonColor,
                "Prev");

        RectangularButton nextLevelButton = new RectangularButton(
                smallButtonSpacing * 2 + smallButtonWidth,
                prevNextYPosition,
                smallButtonWidth,
                buttonHeight,
                buttonColor,
                "Next");

        int themeYPosition = prevNextYPosition + buttonHeight + buttonSpacing;

        RectangularButton themeButton = new RectangularButton(
                xCenter,
                themeYPosition,
                buttonWidth,
                largeButtonHeight,
                buttonColor,
                "Theme");

        int circleButtonRadius = 37;
        int circleButtonSpacing = 30;
        int circleYStart = themeYPosition + largeButtonHeight + buttonSpacing;

        CircleButton moveBackButton = new CircleButton(
                20,
                circleYStart,
                circleButtonRadius,
                buttonColor,
                imageHashMap.get("moveBackwardImage"),
                "Undo");
        CircleButton moveForwardButton = new CircleButton(
                20 + circleButtonRadius * 2 + circleButtonSpacing,
                circleYStart, circleButtonRadius,
                buttonColor,
                imageHashMap.get("moveForwardImage"),
                "Forward");
        CircleButton startOverButton = new CircleButton(
                20 + 2 * (circleButtonRadius * 2 + circleButtonSpacing),
                circleYStart,
                circleButtonRadius,
                buttonColor,
                imageHashMap.get("startOverImage"),
                "Redo");

        int secondRowYOffset = circleButtonRadius * 2 + circleButtonSpacing;

        CircleButton soundButton = new CircleButton(20,
                circleYStart + secondRowYOffset,
                circleButtonRadius,
                buttonColor,
                imageHashMap.get("soundImage"),
                "Sound");

        CircleButton volumeButton = new CircleButton(20 + circleButtonRadius * 2 + circleButtonSpacing,
                circleYStart + secondRowYOffset,
                circleButtonRadius,
                buttonColor,
                imageHashMap.get("volumeImage"),
                "Volume");

        CircleButton settingsButton = new CircleButton(20 + 2 * (circleButtonRadius * 2 + circleButtonSpacing),
                circleYStart + secondRowYOffset,
                circleButtonRadius,
                buttonColor,
                imageHashMap.get("settingsImage"),
                "Settings");

        rectangularButtonHashMap.put("startButton", startButton);
        rectangularButtonHashMap.put("exitButton", exitButton);
        rectangularButtonHashMap.put("prevLevelButton", prevLevelButton);
        rectangularButtonHashMap.put("nextLevelButton", nextLevelButton);
        rectangularButtonHashMap.put("themeButton", themeButton);

        circleButtonHashMap.put("moveBackButton", moveBackButton);
        circleButtonHashMap.put("moveForwardButton", moveForwardButton);
        circleButtonHashMap.put("startOverButton", startOverButton);
        circleButtonHashMap.put("soundButton", soundButton);
        circleButtonHashMap.put("volumeButton", volumeButton);
        circleButtonHashMap.put("settingsButton", settingsButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        initializeButtons(panelWidth, panelHeight);
        drawButtons(g);
    }

    public void drawButtons(Graphics g) {
        for (RectangularButton button : rectangularButtonHashMap.values()) {
            button.draw(g);
        }
        for (CircleButton button : circleButtonHashMap.values()) {
            button.draw(g);
        }
    }

    public boolean isStartButtonClicked(int x, int y) {
        return rectangularButtonHashMap.get("startButton").contains(x, y);
    }

    public boolean isExitButtonClicked(int x, int y) {
        return rectangularButtonHashMap.get("exitButton").contains(x, y);
    }

    public boolean isPrevLevelButtonClicked(int x, int y) {
        return rectangularButtonHashMap.get("prevLevelButton").contains(x, y);
    }

    public boolean isNextLevelButtonClicked(int x, int y) {
        return rectangularButtonHashMap.get("nextLevelButton").contains(x, y);
    }

    public boolean isThemeButtonClicked(int x, int y) {
        return rectangularButtonHashMap.get("themeButton").contains(x, y);
    }

    public boolean isMoveBackButtonClicked(int x, int y) {
        return circleButtonHashMap.get("moveBackButton").contains(x, y);
    }

    public boolean isMoveForwardButtonClicked(int x, int y) {
        return circleButtonHashMap.get("moveForwardButton").contains(x, y);
    }

    public boolean isStartOverButtonClicked(int x, int y) {
        return circleButtonHashMap.get("startOverButton").contains(x, y);
    }

    public boolean isSoundButtonClicked(int x, int y) {
        return circleButtonHashMap.get("soundButton").contains(x, y);
    }

    public boolean isVolumeButtonClicked(int x, int y) {
        return circleButtonHashMap.get("volumeButton").contains(x, y);
    }

    public boolean isSettingsButtonClicked(int x, int y) {
        return circleButtonHashMap.get("settingsButton").contains(x, y);
    }
}
