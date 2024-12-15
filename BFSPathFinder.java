import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSPathFinder {

    public static List<int[]> findPath(int[][] map, int startX, int startY, int targetX, int targetY) {
        int rows = map.length;
        int cols = map[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int[][] parent = new int[rows][cols];

        for (int i = 0; i < parent.length; i++) {
            for (int j = 0; j < parent[i].length; j++) {
                parent[i][j] = -1;
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == targetX && y == targetY) {
                return buildPath(parent, startX, startY, targetX, targetY, cols);
            }

            for (int i = 0; i < 4; i++) {
                // new coordinate
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && !visited[nx][ny]) {
                    if (map[nx][ny] == 0 || map[nx][ny] == 4) {
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;

                        parent[nx][ny] = x * cols + y;
                    }
                }
            }
        }

        return null;
    }

    private static List<int[]> buildPath(int[][] parent, int startX, int startY, int targetX, int targetY, int cols) {
        // list to store the path
        List<int[]> path = new ArrayList<>();
        // starting position
        int x = targetX;
        int y = targetY;

        while (!(x == startX && y == startY)) {
            path.add(0, new int[]{x, y});

            int parentIndex = parent[x][y];
            x = parentIndex / cols;
            y = parentIndex % cols;
        }

        path.add(0, new int[]{startX, startY});
        return path;
    }
}
