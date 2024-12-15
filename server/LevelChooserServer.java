package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelChooserServer {
    public int[][] loadLevelFromFile(String filePath) throws IOException {
        List<int[]> levelData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim()
                        .replace("{", "")
                        .replace("}", "")
                        .replace(",", "");
                String[] elements = line.split("\\s+");
                int[] row = new int[elements.length];
                for (int i = 0; i < elements.length; i++) {
                    row[i] = Integer.parseInt(elements[i]);
                }
                levelData.add(row);
            }
        }
        return levelData.toArray(new int[0][]);
    }
}