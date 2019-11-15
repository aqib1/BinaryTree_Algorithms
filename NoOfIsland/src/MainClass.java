
public class MainClass {

	public static void main(String[] args) {
		int[][] land = {{1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}};
		System.out.println(findNumberOfIsland(land));
	}

	private static int findNumberOfIsland(int[][] land) {
		int count_land = 0;
		boolean visited[][] = new boolean[land.length][];
		for (int i = 0; i < land.length; i++)
			visited[i] = new boolean[land[i].length];
		for (int x = 0; x < land.length; x++) {
			for (int y = 0; y < land[x].length; y++) {
				if (isLand(land, visited, x, y)) {
					count_land++;
					findAdjustentChild(land, visited, x, y);
				}
			}
		}
		return count_land;
	}

	private static boolean isLand(int[][] land, boolean[][] visited, int x, int y) {
		return land[x][y] == 1 && visited[x][y] == false;
	}

	private static void findAdjustentChild(int[][] land, boolean visited[][], int x, int y) {
		visited[x][y] = true;
		int[][] rules = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 1, 1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
				{ 1, 1 } };
		for (int i = 0; i < rules.length; i++) {
			if (isValidMove(land, visited, x + rules[i][0], y + rules[i][1])) {
				visited[x + rules[i][0]][y + rules[i][1]] = true;
				findAdjustentChild(land, visited, x + rules[i][0], y + rules[i][1]);
			}
		}
	}

	private static boolean isValidMove(int[][] land, boolean visited[][], int x, int y) {
		return !(x < 0 || x >= land.length || y < 0 || y >= land.length) && land[x][y] == 1 && !visited[x][y];
	}
}
