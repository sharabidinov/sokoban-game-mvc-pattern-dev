import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;

public class Controller extends MouseAdapter implements KeyListener, MouseWheelListener {
    private Model model;
    private Timer timer;
    private HashMap<String, RectangularButton> rectangularButtonHashMap;
    private HashMap<String, CircleButton> circularButtonHashMap;

    public Controller(Viewer viewer) {
        model = new Model(viewer);
        timer = model.getTimer();
        rectangularButtonHashMap = viewer.getRectangularButtonHashMap();
        circularButtonHashMap = viewer.getCircleButtonHashMap();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        String direction = "";
        switch (keyCode) {
            case 38:
                direction = "Up";
                break;
            case 40:
                direction = "Down";
                break;
            case 37:
                direction = "Left";
                break;
            case 39:
                direction = "Right";
                break;
            case 90:
                model.undoMove();
                return;
            case 89:
                model.redoMove();
                return;
            default:
                return;
        }

        model.move(direction);
    }

    public void keyReleased(KeyEvent e) {

    }

    public void mouseClicked(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();

        if (event.getSource() instanceof ControlCanvas) {

          ControlCanvas sourceCanvas = (ControlCanvas) event.getSource();
          if (sourceCanvas.isStartButtonClicked(x, y)) {
            model.startGame();
          } else if (sourceCanvas.isExitButtonClicked(x, y)) {
            System.exit(0);
          } else if (sourceCanvas.isMoveBackButtonClicked(x, y)) {
            model.undoMove();
          } else if (sourceCanvas.isMoveForwardButtonClicked(x, y)) {
            model.redoMove();
          } else if(sourceCanvas.isNextLevelButtonClicked(x, y)) {
              model.nextLevel();
          } else if(sourceCanvas.isPrevLevelButtonClicked(x, y)) {
              model.previousLevel();
          }else if (sourceCanvas.isThemeButtonClicked(x,y)){
             model.changeTheme();
          } else if (sourceCanvas.isSoundButtonClicked(x, y)) {
              model.toggleMusic();
          } else if(sourceCanvas.isStartOverButtonClicked(x, y)) {
              model.startOver();
          }
        } else if (event.getSource() instanceof GameCanvas) {
          int cellWidthHeight = ((GameCanvas) event.getSource()).getCellWidthHeight();
          int desktopIndexX = (x / cellWidthHeight - 1);
          int desktopIndexY = (y / cellWidthHeight - 1);
          model.moveTo(x, y, desktopIndexX, desktopIndexY);
        }

        // executeButton(x, y)
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotation = e.getWheelRotation();
        model.doMouseWheelAction(rotation);
    }

    private boolean isInsideCircle(int clickX, int clickY, int centerX, int centerY, int radius) {
        int dx = clickX - centerX;
        int dy = clickY - centerY;
        return (dx * dx + dy * dy) <= (radius * radius);
    }

    public Model getModel() {
        return model;

    }
    public void executeButton(int x, int y) {

        for (RectangularButton button : rectangularButtonHashMap.values()) {
            if (button.contains(x, y)) {
                String label = button.getLabel();
                if (label.equals("Start Game")) {
                    model.startGame();
                } else if (label.equals("Exit")) {
                    System.exit(0);
                } else if (label.equals("prev")) {
                    model.previousLevel();
                } else if (label.equals("next")) {
                    model.nextLevel();
                }
            }
        }

        for (CircleButton button : circularButtonHashMap.values()) {
            if (button.contains(x, y)) {
                String label = button.getLabel();
                if (label.equals("Start Over")) {
                    if (model.isChangedState()) {
                        model.startOver();
                    }
                    timer.resetTimer();
                } else if (label.equals("Pause")) {
                    timer.stopTimer();
                    timer.resetTimer();
                } else if (label.equals("Stop")) {
                    timer.stopTimer();
                    timer.resetTimer();
                } else if (label.equals("Undo")) {
                    model.undoMove();
                } else if (label.equals("Redo")) {
                    model.redoMove();
                } else if (label.equals("Sound")) {
                    // TODO: Implement sound
                    model.toggleMusic();
                } else if (label.equals("Volume")) {
                    // TODO: Implement volume
                    }
            }
        }
    }
}
