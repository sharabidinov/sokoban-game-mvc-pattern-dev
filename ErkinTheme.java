import javax.swing.ImageIcon;
import java.awt.Image;

public class ErkinTheme implements PlayerTheme {
    private String type;
    private Image frontErkinImage;
    private Image backDefalutImage;
    private Image leftErkinImage;
    private Image rightErkinImage;
    private Image wallImage;
    private Image boxImage;
    private Image targetImage;
    private Image groundImage;
    private Image boxOnTarget;
    public ErkinTheme() {
        type = "Erkin Theme";
        frontErkinImage = new ImageIcon("res/images/erkin_front.png").getImage();
        backDefalutImage = new ImageIcon("res/images/erkin_back.png").getImage();
        leftErkinImage = new ImageIcon("res/images/erkin_left.png").getImage();
        rightErkinImage = new ImageIcon("res/images/erkin_right.png").getImage();
        wallImage = new ImageIcon("res/images/erkin_wall.png").getImage();
        boxImage = new ImageIcon("res/images/erkin_box.png").getImage();
        targetImage = new ImageIcon("res/images/erkin_target.png").getImage();
        groundImage = new ImageIcon("res/images/erkin_cell.png").getImage();
        boxOnTarget = new ImageIcon("res/images/erkin_box_on_target.png").getImage();
    }

    public String getType() {
        return type;
    }

    public Image getFrontPlayerImage() {
        return frontErkinImage;
    }

    public Image getBackPlayerImage() {
        return backDefalutImage;
    }

    public Image getLeftPlayerImage() {
        return leftErkinImage;
    }

    public Image getRightPlayerImage() {
        return rightErkinImage;
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
        return  groundImage;
    }
    public Image getBoxOnTargetImage(){
        return boxOnTarget;
    }
}
