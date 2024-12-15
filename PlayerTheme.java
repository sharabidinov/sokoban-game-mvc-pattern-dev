import java.awt.Image;

public interface PlayerTheme {
    String getType();

    Image getFrontPlayerImage();

    Image getBackPlayerImage();

    Image getLeftPlayerImage();

    Image getRightPlayerImage();

    Image getWallImage();

    Image getBoxImage();

    Image getTargetImage();

    Image getGroundImage();

    Image getBoxOnTargetImage();
}
