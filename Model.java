import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Objects;
import java.util.Stack;
import java.util.List;

public class Model {
    private Viewer viewer;
    private int[][] desktop;
    private int indexX;
    private int indexY;
    private int[][] arrayIndexes;
    private boolean stateDesktop;
    private Levels levels;
    private String direction;
    private Stack<int[][]> undoStack;
    private Stack<int[][]> redoStack;
    private int[][] initialState;
    private int initialIndexX;
    private int initialIndexY;
    private int steps;
    private CountSteps countSteps;
    private Timer timer;
    private LevelName levelName;
    private SoundModel soundModel;
    private String themeType;
    private boolean isMusicPlaying;
    private Clip musicClip;

    public Model(Viewer viewer) {
        isMusicPlaying = false;
        this.viewer = viewer;
        this.soundModel = new SoundModel();
        levels = new Levels();
        levelName = new LevelName();
        countSteps = new CountSteps(this);
        initialization();
        themeType = "Boy";
        direction = "Down";
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        timer = new Timer();
        playMusic(0);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    Objects.requireNonNull(getClass().getResource("res/sounds/backgroundMusic.wav")));
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        startMusic();
    }

    public void toggleMusic() {
        if (isMusicPlaying) {
            stopMusic();
        } else {
            startMusic();
        }
    }

    public void startMusic() {
        if (!isMusicPlaying) {
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            isMusicPlaying = true;
        }
    }

    public void stopMusic() {
        if (isMusicPlaying) {
            musicClip.stop();
            isMusicPlaying = false;
        }
    }

    private void initialization() {
        direction = "Down";

        steps = 0;
        countSteps.setSteps();
        levelName.setText("Level " + levels.getLevelNumber());
        desktop = levels.nextLevel();

        initialState = new int[desktop.length][];

        for (int i = 0; i < desktop.length; i++) {
            initialState[i] = new int[desktop[i].length];
            for (int j = 0; j < desktop[i].length; j++) {
                initialState[i][j] = desktop[i][j];
                if (desktop[i][j] == 1) {
                    initialIndexX = i;
                    initialIndexY = j;
                }
            }
        }

        stateDesktop = true;
        int countOne = 0;
        int countThree = 0;
        int countFour = 0;

        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    countOne = countOne + 1;
                    indexX = i;
                    indexY = j;
                } else if (desktop[i][j] == 3) {
                    countThree = countThree + 1;
                } else if (desktop[i][j] == 4) {
                    countFour = countFour + 1;
                }
            }
        }

        if ((countOne != 1) || (countThree == 0) || (countFour == 0) || (countThree != countFour)) {
            stateDesktop = false;
            return;
        }

        arrayIndexes = new int[2][countFour];

        int a = 0;
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 4) {
                    arrayIndexes[0][a] = i;
                    arrayIndexes[1][a] = j;
                    a = a + 1;
                }
            }
        }
    }

    public void move(String direction) {
        this.direction = direction;

        switch (direction) {
            case "Left":
                moveLeft();
                break;
            case "Up":
                moveUp();
                break;
            case "Right":
                moveRight();
                break;
            case "Down":
                moveDown();
                break;
            default:
                break;
        }
        checkGoal();
        viewer.update();
        won();
    }

    private void won() {
        boolean flag = true;
        for (int z = 0; z < arrayIndexes[0].length; z++) {
            int i = arrayIndexes[0][z];
            int j = arrayIndexes[1][z];
            if (desktop[i][j] != 3) {
                flag = false;
                break;
            }
        }
        if (flag) {
            viewer.showWonDialog();
            initialization();
            viewer.update();
            undoStack.clear();
            countSteps.setSteps();
            timer.resetTimer();
        }
    }

    private void checkGoal() {
        for (int t = 0; t < arrayIndexes[0].length; t++) {
            int i = arrayIndexes[0][t];
            int j = arrayIndexes[1][t];
            if (desktop[i][j] == 0) {
                desktop[i][j] = 4;
            }
        }
    }

    private void moveLeft() {
        boolean boxMoved = false;
        if (desktop[indexX][indexY - 1] == 3) {
            if (desktop[indexX][indexY - 2] == 0 || desktop[indexX][indexY - 2] == 4) {
                if (desktop[indexX][indexY - 2] == 0) {
                    playSE(2);
                } else if (desktop[indexX][indexY - 2] == 4) {
                    playSE(3);
                }
                desktop[indexX][indexY - 1] = 0;
                desktop[indexX][indexY - 2] = 3;
                boxMoved = true;
            }
        }

        if (desktop[indexX][indexY - 1] == 0 || desktop[indexX][indexY - 1] == 4) {
            saveState();
            steps++;
            countSteps.setSteps();
            desktop[indexX][indexY] = 0;
            indexY = indexY - 1;
            desktop[indexX][indexY] = 1;
            if (!boxMoved) {
                playSE(1);
            }
        }
    }

    private void moveRight() {
        boolean boxMoved = false;
        if (desktop[indexX][indexY + 1] == 3) {
            if (desktop[indexX][indexY + 2] == 0 || desktop[indexX][indexY + 2] == 4) {
                if (desktop[indexX][indexY + 2] == 0) {
                    playSE(2);
                } else if (desktop[indexX][indexY + 2] == 4) {
                    playSE(3);
                }
                desktop[indexX][indexY + 1] = 0;
                desktop[indexX][indexY + 2] = 3;
                boxMoved = true;
            }
        }

        if (desktop[indexX][indexY + 1] == 0 || desktop[indexX][indexY + 1] == 4) {
            saveState();
            steps++;
            countSteps.setSteps();
            desktop[indexX][indexY] = 0;
            indexY = indexY + 1;
            desktop[indexX][indexY] = 1;
            if (!boxMoved) {
                playSE(1);
            }
        }
    }

    private void moveDown() {
        boolean boxMoved = false;
        if (desktop[indexX + 1][indexY] == 3) {
            if (desktop[indexX + 2][indexY] == 0 || desktop[indexX + 2][indexY] == 4) {
                if (desktop[indexX + 2][indexY] == 0) {
                    playSE(2);
                } else if (desktop[indexX + 2][indexY] == 4) {
                    playSE(3);
                }
                desktop[indexX + 1][indexY] = 0;
                desktop[indexX + 2][indexY] = 3;
                boxMoved = true;
            }
        }

        if (desktop[indexX + 1][indexY] == 0 || desktop[indexX + 1][indexY] == 4) {
            saveState();
            steps++;
            countSteps.setSteps();
            desktop[indexX][indexY] = 0;
            indexX = indexX + 1;
            desktop[indexX][indexY] = 1;
            if (!boxMoved) {
                playSE(1);
            }
        }
    }

    private void moveUp() {
        boolean boxMoved = false;
        if (desktop[indexX - 1][indexY] == 3) {
            if (desktop[indexX - 2][indexY] == 0 || desktop[indexX - 2][indexY] == 4) {
                if (desktop[indexX - 2][indexY] == 0) {
                    playSE(2);
                } else if (desktop[indexX - 2][indexY] == 4) {
                    playSE(3);
                }
                desktop[indexX - 1][indexY] = 0;
                desktop[indexX - 2][indexY] = 3;
                boxMoved = true;
            }
        }

        if (desktop[indexX - 1][indexY] == 0 || desktop[indexX - 1][indexY] == 4) {
            saveState();
            steps++;
            countSteps.setSteps();
            desktop[indexX][indexY] = 0;
            indexX = indexX - 1;
            desktop[indexX][indexY] = 1;
            if (!boxMoved) {
                playSE(1);
            }
        }
    }

    public boolean getState() {
        return stateDesktop;
    }

    public int[][] getDesktop() {
        return desktop;
    }

    public String getDirection() {
        return direction;
    }

    public void undoMove() {
        if (0 < undoStack.size()) {
            redoStack.push(copyField(desktop));
            desktop = undoStack.pop();
            updatePlayerPosition();
            steps = undoStack.size();
            countSteps.setSteps();
        }
    }

    public void redoMove() {
        if (0 < redoStack.size()) {
            undoStack.push(copyField(desktop));
            desktop = redoStack.pop();
            updatePlayerPosition();
            steps = undoStack.size();
            countSteps.setSteps();
        }
    }

    private void saveState() {
        undoStack.push(copyField(desktop));
        redoStack.clear();
    }

    private int[][] copyField(int[][] field) {
        int[][] copy = new int[field.length][];
        for (int i = 0; i < field.length; i++) {
            copy[i] = new int[field[i].length];
            for (int j = 0; j < field[i].length; j++) {
                copy[i][j] = field[i][j];
            }
        }
        return copy;
    }

    private void updatePlayerPosition() {
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    indexX = i;
                    indexY = j;
                    break;
                }
            }
        }

        viewer.update();
    }

    public boolean isChangedState() {
        for (int i = 0; i < initialState.length; i++) {
            for (int j = 0; j < initialState[i].length; j++) {
                if (initialState[i][j] != desktop[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void startOver() {
        for (int i = 0; i < initialState.length; i++) {
            for (int j = 0; j < initialState[i].length; j++) {
                desktop[i][j] = initialState[i][j];
            }
        }
        undoStack.clear();
        redoStack.clear();
        indexX = initialIndexX;
        indexY = initialIndexY;
        direction = "Down";
        steps = 0;
        countSteps.setSteps();
        viewer.update();
    }

    public int getSteps() {
        return steps;
    }

    public CountSteps getCountSteps() {
        return countSteps;
    }

    public Timer getTimer() {
        return timer;
    }

    public LevelName getLevelName() {
        return levelName;

    }

    public void updateCurrentTheme(String themeType) {
        ThemeDatabase themeDatabase = new ThemeDatabase();
        PlayerTheme playerTheme = themeDatabase.getPlayerTheme(themeType);
        startOver();
        viewer.updateTheme(playerTheme);
    }

    public void playMusic(int i) {
        soundModel.play(i);
        soundModel.loop(i);
    }

    public void stopMusic(int i) {
        soundModel.stop(i);
    }

    public void playSE(int i) {
        soundModel.play(i);
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void startGame() {
        // TODO: Implement this method
        timer.startTimer();
    }

    public void nextLevel() {
        timer.resetTimer();

        undoStack.clear();
        redoStack.clear();

        initialization();

        viewer.update();
    }

    public void previousLevel() {
        timer.resetTimer();

        int currentLevel = levels.getLevelNumber();

        if (currentLevel <= 2) {
            levels.setCurrentLevel(1);
        } else {
            levels.setCurrentLevel(currentLevel - 2);
        }
        undoStack.clear();
        redoStack.clear();

        initialization();

        viewer.update();

    }

    public void moveTo(int x, int y, int targetX, int targetY) {
        // inversion of map if use target, XtargetY
        List<int[]> path = BFSPathFinder.findPath(desktop, indexX, indexY, targetY, targetX);

        if (path == null || path.size() < 2) {
            return;
        }

        for (int i = 1; i < path.size(); i++) {
            int nextX = path.get(i)[0];
            int nextY = path.get(i)[1];

            String moveDirection = "";
            if (nextX == indexX + 1 && nextY == indexY) {
                moveDirection = "Down";
            } else if (nextX == indexX - 1 && nextY == indexY) {
                moveDirection = "Up";
            } else if (nextX == indexX && nextY == indexY + 1) {
                moveDirection = "Right";
            } else if (nextX == indexX && nextY == indexY - 1) {
                moveDirection = "Left";
            }

            if (!moveDirection.isEmpty()) {
                move(moveDirection);
            }
        }
    }

    public void setThemeType(String themeType) {
        this.themeType = themeType;
    }

    public int[][] getArrayIndexes() {
        return arrayIndexes;
    }

    public void changeTheme() {
        if (themeType.equals("Boy")) {
            updateCurrentTheme("Girl");
            setThemeType("Girl");
        } else if (themeType.equals("Girl")) {
            updateCurrentTheme("Erkin");
            setThemeType("Erkin");
        } else {
            updateCurrentTheme("Boy");
            setThemeType("Boy");
        }
    }

    public void doMouseWheelAction(int rotation) {
        if (direction.equals("Down") || direction.equals("Up")) {
            if (rotation > 0) {
                move("Up");
            } else {
                move("Down");
            }
        } else {
            if (rotation > 0) {
                move("Left");
            } else {
                move("Right");
            }
        }

    }
}
