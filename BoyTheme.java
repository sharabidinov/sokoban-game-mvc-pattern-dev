import javax.swing.ImageIcon;
import java.awt.Image;

public class BoyTheme implements PlayerTheme {
    private String type;
    private Image frontKyrgyzImage;
    private Image backKyrgyzImage;
    private Image leftKyrgyzImage;
    private Image rightKyrgyzImage;
    private Image wallImage;
    private Image boxImage;
    private Image targetImage;
    private Image groundImage;
    private Image boxOnTarget;
    public BoyTheme() {
        type = "Boy Theme";
        frontKyrgyzImage = new ImageIcon("res/images/boy_front.png").getImage();
        backKyrgyzImage = new ImageIcon("res/images/boy_back.png").getImage();
        leftKyrgyzImage = new ImageIcon("res/images/boy_left.png").getImage();
        rightKyrgyzImage = new ImageIcon("res/images/boy_right.png").getImage();
        wallImage = new ImageIcon("res/images/boy_wall.png").getImage();
        boxImage = new ImageIcon("res/images/boy_box.png").getImage();
        targetImage = new ImageIcon("res/images/boy_target.png").getImage();
        groundImage = new ImageIcon("res/images/boy_cell.png").getImage();
        boxOnTarget = new ImageIcon("res/images/boy_box_on_target.png").getImage();
    }
    public String getType() {
            return type;
    }

    public Image getFrontPlayerImage() {
        return frontKyrgyzImage;
    }

    public Image getBackPlayerImage() {
        return backKyrgyzImage;
    }

    public Image getLeftPlayerImage() {
        return leftKyrgyzImage;
    }

    public Image getRightPlayerImage() {
        return rightKyrgyzImage;
    }

    public Image getWallImage() {
        return wallImage;
    }

    public Image getBoxImage() {
        return boxImage;
    }

    public Image getTargetImage() {
        return targetImage;
    }

    public Image getGroundImage() {
        return groundImage;
    }

    public Image getBoxOnTargetImage() {
        return boxOnTarget;
    }
}
