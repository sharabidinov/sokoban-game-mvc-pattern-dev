import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class Timer extends JLabel implements Runnable {

    private Thread thread;
    private int time;
    private boolean isSuspended;

    public Timer() {
        thread = new Thread(this);
        setText("00:00");
        setForeground(Color.white);
        setSize(100, 50);
        setFont(new Font("Arial", Font.BOLD, 20));
    }

    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    if (!isSuspended) {
                        Thread.sleep(1000);
                        time++;
                        setTime();
                    }
                }
            } catch (InterruptedException ie) {
                System.out.println("Error: " + ie);
            }
        }
    }

    private void setTime() {
        int seconds = time % 60;
        int minutes = time / 60;
        if (seconds >= 10 && minutes >= 10) {
            setText(minutes + ":" + seconds);
        } else if (seconds < 10 && minutes >= 10) {
            setText(minutes + ":0" + seconds);
        } else if (seconds >= 10) {
            setText("0" + minutes + ":" + seconds);
        } else {
            setText("0" + minutes + ":0" + seconds);
        }
    }

    public void startTimer() {
        if (!thread.isAlive()) {
            thread.start();
        } else {
            resumeTimer();
        }
    }

    public void resetTimer() {
        time = 0;
    }

    public void resumeTimer() {
        isSuspended = false;
    }

    public void stopTimer() {
        isSuspended = true;
    }
}
