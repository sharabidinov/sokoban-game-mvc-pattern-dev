import java.io.FileInputStream;
import java.io.IOException;

public class Levels {
    private int level;

    public Levels() {
        level = 1;
    }

    public int[][] nextLevel() {
        int[][] array = null;
        switch (level) {
            case 1:
                array = getFirstLevel();
                break;
            case 2:
                array = getSecondLevel();
                break;
            case 3:
                array = getThirdLevel();
                break;
            case 4:
                array = getFourthLevel();
                break;
            case 5:
                array = getLevelFromFile("res/levels/level5.sok");
                break;
            case 6:
                array = getLevelFromFile("res/levels/level6.sok");
                break;
            case 7:
                array = getLevelFromFile("res/levels/level7.sok");
                break;
            case 8:
                array = getLevelFromFile("res/levels/level8.sok");
                break;
            case 9:
                array = getLevelFromServer("server/levels/9.txt");
                break;
            case 10:
                array = getLevelFromServer("server/levels/10.txt");
                break;
            default:
                level = 1;
                array = getFirstLevel();
        }
        if (level < 10) {
            level = level + 1;
        } else {
            level = 1;
        }
        return array;
    }

    private int[][] getFirstLevel() {
        return new int[][] {
                { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
                { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
                { 2, 0, 0, 1, 0, 3, 0, 3, 4, 2 },
                { 2, 0, 3, 0, 0, 0, 0, 0, 4, 2 },
                { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
                { 2, 0, 0, 2, 2, 0, 0, 0, 0, 2 },
                { 2, 0, 0, 2, 2, 2, 2, 2, 2, 2 },
                { 2, 0, 0, 3, 0, 0, 0, 0, 4, 2 },
                { 2, 0, 4, 0, 0, 0, 0, 0, 0, 2 },
                { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
        };
    }

    private int[][] getSecondLevel() {
        return new int[][] {
                { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
                { 2, 0, 0, 3, 0, 0, 0, 4, 0, 2 },
                { 2, 0, 2, 2, 2, 0, 2, 2, 0, 2 },
                { 2, 0, 0, 0, 0, 0, 0, 0, 4, 2 },
                { 2, 0, 3, 1, 0, 3, 0, 0, 0, 2 },
                { 2, 0, 2, 2, 2, 0, 2, 2, 2, 2 },
                { 2, 0, 0, 4, 0, 3, 0, 0, 0, 2 },
                { 2, 0, 0, 0, 0, 0, 0, 4, 0, 2 },
                { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
        };
    }

    private int[][] getThirdLevel() {
        return new int[][] {
                { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
                { 2, 0, 0, 1, 0, 0, 0, 3, 4, 2 },
                { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
        };
    }

    private int[][] getFourthLevel() {
        return new int[][] {
                { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
                { 2, 0, 2, 4, 0, 0, 2, 4, 0, 2 },
                { 2, 0, 0, 2, 0, 3, 2, 0, 0, 2 },
                { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
                { 2, 0, 0, 1, 0, 0, 0, 0, 0, 2 },
                { 2, 0, 2, 0, 0, 0, 3, 0, 0, 2 },
                { 2, 0, 3, 0, 0, 2, 0, 0, 0, 2 },
                { 2, 0, 0, 0, 2, 0, 0, 0, 0, 2 },
                { 2, 0, 0, 0, 2, 4, 0, 0, 0, 2 },
                { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }
        };
    }

    private int[][] getLevelFromServer(String filePath) {
        ClientForBackendServerSokoban client = new ClientForBackendServerSokoban("localhost", 8080);
        return client.loadLevelFromServer(filePath);
    }

    private int[][] getLevelFromFile(String fileName) {

        FileInputStream in = null;
        StringBuilder container = null;

        try {
            in = new FileInputStream(fileName);
            container = new StringBuilder();

            int unicode;

            while ((unicode = in.read()) != -1) {
                char symbol = (char) unicode;
                if (('0' <= symbol && symbol <= '4') || symbol == '\n' || symbol == '-') {
                    container.append(symbol);
                }
            }
        } catch (IOException ioe) {
            System.out.println("ioe" + ioe);
            container = null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioe) {
                System.out.println("ioe" + ioe);
            }
        }
        int[][] array = null;
        if (container != null) {
            array = convertToArray(container);
        }

        return array;
    }

    private int[][] convertToArray(StringBuilder container) {
        int rowCount = calculateRowCount(container);
        int[][] array = buildArray(container, rowCount);

        fillArray(container, array);

        return array;
    }

    private int calculateRowCount(StringBuilder container) {
        int rowCount = 1;

        for (int i = 0; i < container.length(); i++) {
            if (container.charAt(i) == '\n') {
                rowCount++;
            }
        }

        if (container.charAt(container.length() - 1) == '\n') {
            container.deleteCharAt(container.length() - 1);
            rowCount--;
        }

        return rowCount;
    }

    private int[][] buildArray(StringBuilder container, int rowCount) {
        int[][] array = new int[rowCount][];

        int currentRowLength = 0;
        int currentRow = 0;

        for (int i = 0; i < container.length(); i++) {
            char element = container.charAt(i);

            if (element == '\n' || i == container.length() - 1) {
                if (i == container.length() - 1) {
                    currentRowLength = currentRowLength + 1;
                }
                array[currentRow] = new int[currentRowLength];
                currentRow = currentRow + 1;
                currentRowLength = 0;
            } else {
                currentRowLength = currentRowLength + 1;
            }
        }
        return array;
    }

    private void fillArray(StringBuilder container, int[][] array) {
        int row = 0;
        int column = 0;

        for (int i = 0; i < container.length(); i++) {
            char element = container.charAt(i);

            if (element == '\n') {
                row = row + 1;
                column = 0;
                continue;
            }

            if (element == '-') {
                array[row][column] = -1;
                column = column + 1;
                continue;
            }

            int value = Character.getNumericValue(element);
            array[row][column] = value;
            column = column + 1;
        }
    }

    public int getLevelNumber() {
        return level;
    }

    public int[][] getLevel() {
        int[][] array = null;
        switch (level) {
            case 1:
                array = getFirstLevel();
                break;
            case 2:
                array = getSecondLevel();
                break;
            case 3:
                array = getThirdLevel();
                break;
            case 4:
                array = getFourthLevel();
                break;
            case 5:
                array = getLevelFromFile("res/levels/level5.sok");
                break;
            case 6:
                array = getLevelFromFile("res/levels/level6.sok");
                break;
            case 7:
                array = getLevelFromFile("res/levels/level7.sok");
                break;
            case 8:
                array = getLevelFromFile("res/levels/level8.sok");
                break;
            case 9:
                array = getLevelFromServer("server/levels/9.txt");
                break;
            case 10:
                array = getLevelFromServer("server/levels/10.txt");
                break;
            default:
                level = 1;
                array = getFirstLevel();
        }
        return array;
    }

    public int getRowCount(String levelNumber) {
        int[][] level = getLevel();
        int count = 0;
        for (int i = 0; i < level.length; i++) {
            count = count + 1;
        }
        return count;
    }

    public void setCurrentLevel(int levelNumber) {
        level = levelNumber;
    }
}
