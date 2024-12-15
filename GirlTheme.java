import javax.swing.ImageIcon;
import java.awt.Image;

public class GirlTheme implements PlayerTheme {
    private String type;
    private Image frontGirlImage;
    private Image backDefalutImage;
    private Image leftGirlImage;
    private Image rightGirlImage;
    private Image wallImage;
    private Image boxImage;
    private Image targetImage;
    private Image groundImage;
    private Image boxOnTarget;

    public GirlTheme() {
        type = "Girl Theme";
        frontGirlImage = new ImageIcon("res/images/girl_front.png").getImage();
        backDefalutImage = new ImageIcon("res/images/girl_back.png").getImage();
        leftGirlImage = new ImageIcon("res/images/girl_left.png").getImage();
        rightGirlImage = new ImageIcon("res/images/girl_right.png").getImage();
        wallImage = new ImageIcon("res/images/girl_wall.png").getImage();
        boxImage = new ImageIcon("res/images/girl_box.png").getImage();
        targetImage = new ImageIcon("res/images/girl_target.png").getImage();
        groundImage = new ImageIcon("res/images/girl_cell.png").getImage();
        boxOnTarget = new ImageIcon("res/images/girl_box_on_target.png").getImage();
    }

    public String getType() {
        return type;
    }

    public Image getFrontPlayerImage() {
        return frontGirlImage;
    }

    public Image getBackPlayerImage() {
        return backDefalutImage;
    }

    public Image getLeftPlayerImage() {
        return leftGirlImage;
    }

    public Image getRightPlayerImage() {
        return rightGirlImage;
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
