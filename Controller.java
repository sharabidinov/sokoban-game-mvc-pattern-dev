import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Controller extends MouseAdapter implements KeyListener, MouseWheelListener {
    private Model model;

    public Controller(Viewer viewer) {
        model = new Model(viewer);
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
            } else if (sourceCanvas.isNextLevelButtonClicked(x, y)) {
                model.nextLevel();
            } else if (sourceCanvas.isPrevLevelButtonClicked(x, y)) {
                model.previousLevel();
            } else if (sourceCanvas.isThemeButtonClicked(x, y)) {
                model.changeTheme();
            } else if (sourceCanvas.isSoundButtonClicked(x, y)) {
                model.toggleMusic();
            } else if (sourceCanvas.isStartOverButtonClicked(x, y)) {
                model.startOver();
            }
        } else if (event.getSource() instanceof GameCanvas) {
            int cellWidthHeight = ((GameCanvas) event.getSource()).getCellWidthHeight();
            int desktopIndexX = (x / cellWidthHeight - 1);
            int desktopIndexY = (y / cellWidthHeight - 1);
            model.moveTo(x, y, desktopIndexX, desktopIndexY);
        }
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotation = e.getWheelRotation();
        model.doMouseWheelAction(rotation);
    }

    public Model getModel() {
        return model;

    }
}
