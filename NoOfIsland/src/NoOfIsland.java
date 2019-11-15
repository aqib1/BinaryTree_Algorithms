
public class NoOfIsland {

	/*
	 * 00 01 02 10 11 12 20 21 22
	 */

	private int[][] rules = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	public int noOfIsland(int[][] land) {
		int count = 0;
		boolean isVisit[][] = new boolean[land.length][];
		for (int row = 0; row < land.length; row++)
			isVisit[row] = new boolean[land[row].length];

		// count of island
		for (int row = 0; row < land.length; row++) {
			for (int col = 0; col < land[row].length; col++) {
				if (isValidLand(land, isVisit, row, col)) {
					// calculate island
					count++;
					isVisit[row][col] = true;
					markIslandBounderies(land, isVisit, row, col);
				}
			}
		}

		return count;
	}

	private void markIslandBounderies(int[][] land, boolean[][] isVisit, int row, int col) {
		for (int x = 0; x < rules.length; x++) {
			if (isValidMove(land, isVisit, row + rules[x][0], col + rules[x][1])) {
				isVisit[row + rules[x][0]][col + rules[x][1]] = true;
				markIslandBounderies(land, isVisit, row + rules[x][0], col + rules[x][1]);
			}
		}

	}

	private boolean isValidMove(int[][] land, boolean[][] isVisit, int i, int j) {
		return !(i < 0 || i >= land.length || j < 0 || j >= land.length) && land[i][j] == 1 && !isVisit[i][j];
	}

	private boolean isValidLand(int[][] land, boolean[][] isVisit, int row, int col) {

		return land[row][col] == 1 && !isVisit[row][col];
	}
}
