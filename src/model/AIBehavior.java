package model;

public class AIBehavior {

	private static final double[] weight = { -3.8, 3.7, 2.5, -8.8, -0.59, 8.2 };
	public static final int COLS = 10;
	public static final int ROWS = 22;

	private GameBoardSolo gameBoard;
	private int[] nextHeight;
	private int[] beforeHeight;

	private double AIScore;

	private double varHeight;
	private int blockContactSurface;
	private int wallContactSurface;
	private int emptySpace;
	private int emptyBlock;
	private int clearLineNum;

	Point[] coord = new Point[4];

	public AIBehavior(GameBoardSolo soloGameBoard) {
		gameBoard = soloGameBoard;
		init();
	}

	public void init() {
		nextHeight = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		beforeHeight = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < 4; i++)
			coord[i] = new Point(0, 0);
	}

	public void setNextHeight() {
		int blockHeight = 0;
		for (int i = 0; i < COLS; i++) {
			for (int j = ROWS - 1; j >= 3; j--)
				if (gameBoard.Board[j][i] != -1)
					blockHeight = ROWS - j;
			nextHeight[i] = blockHeight;
			// System.out.print(blockHeight + " ");
			blockHeight = 0;
		}
		// System.out.println();
	}

	public void setBeforeHeight() {
		int blockHeight = 0;
		for (int i = 0; i < COLS; i++) {
			for (int j = ROWS - 1; j >= 3; j--)
				if (gameBoard.tempBoard[j][i] != -1)
					blockHeight = ROWS - j;
			beforeHeight[i] = blockHeight;
			// System.out.print(blockHeight + " ");
			blockHeight = 0;
		}
		// System.out.println();
	}

	public void setVarHeight() {
		double avrHeight;
		varHeight = 0;
		for (int i = 0; i < COLS; i++) {
			varHeight += nextHeight[i];
		}
		avrHeight = varHeight / 10;
		varHeight = 0;
		for (int i = 0; i < COLS; i++) {
			varHeight += (nextHeight[i] - avrHeight) * (nextHeight[i] - avrHeight);
		}
		varHeight /= 10;
		// System.out.println("varHeight -> "+varHeight);
	}

	public void setClearLineNum() {
		clearLineNum = 0;
		for (int i = 0; i < ROWS; i++)
			if (isFullRow(i))
				clearLineNum++;
		System.out.println("clearLine -> " + clearLineNum);
	}

	public boolean isFullRow(int line) {
		for (int i = 0; i < gameBoard.Board[line].length; i++)
			if (gameBoard.Board[line][i] == -1)
				return false;
		return true;
	}

	public void setBlockAndWallCS() {
		blockContactSurface = 0;
		wallContactSurface = 0;
		emptySpace = 0;
		Block block = gameBoard.currentBlock;
		Point[] point = new Point[4];
		for (int i = 0; i < 4; i++) {
			point[i] = block.topLeftPoint.setCurrentPoint(block.coord[i]);
		}
		for (int i = 0; i < 4; i++) {
			if (point[i].getY() == 0 || point[i].getY() == COLS - 1)
				wallContactSurface++;
			getEmpty(point[i].getX(), point[i].getY(), i);
			if (!isEqualBlock(point, point[i].getX(), point[i].getY() + 1))
				blockContactSurface += getSurfaceNum(point[i].getX(), point[i].getY() + 1);
			if (!isEqualBlock(point, point[i].getX(), point[i].getY() - 1))
				blockContactSurface += getSurfaceNum(point[i].getX(), point[i].getY() - 1);
			if (!isEqualBlock(point, point[i].getX() + 1, point[i].getY()))
				blockContactSurface += getSurfaceNum(point[i].getX() + 1, point[i].getY());
		}

	}

	public void getEmpty(int x, int y, int i) {
		if (x + 1 < ROWS && gameBoard.Board[x + 1][y] == -1) {
			emptySpace++;
			getEmpty(x + 1, y, i);
		}
	}

	public boolean isEqualBlock(Point[] p, int x, int y) {
		for (int i = 0; i < 4; i++) {
			if (p[i].getX() == x && p[i].getY() == y)
				return true;
		}
		return false;
	}

	public int getSurfaceNum(int x, int y) {
		int index = 0;

		try {
			index = gameBoard.Board[x][y];
			if (index != -1)
				return 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
		return 0;
	}

	public void setEmptyBlock() { // 옆면 말고 밑면만 계산
		emptyBlock = 0;
		int emptySpaceLine = 0;
		int floorBlock = 22;
		for (int i = 0; i < COLS; i++) {
			for (int j = (ROWS - nextHeight[i]); j < ROWS; j++) {
				if (gameBoard.Board[j][i] == -1) {
					floorBlock = j;
					emptySpaceLine++;
				}
			}
			if (floorBlock != 22)
				emptyBlock += nextHeight[i] - emptySpaceLine - (21 - floorBlock);
			emptySpaceLine = 0;
			floorBlock = 22;
		}

	}

	public void circul() {
		setNextHeight();
		setBeforeHeight();
		setVarHeight();
		setClearLineNum();
		setBlockAndWallCS();
		setEmptyBlock();
	}

	public double setAIScore() {
		AIScore = 0;
		setNextHeight();
		setBeforeHeight();
		setVarHeight();
		setClearLineNum();
		setBlockAndWallCS();
		setEmptyBlock();
		AIScore = weight[0] * varHeight + weight[1] * blockContactSurface + weight[2] * wallContactSurface
				+ weight[3] * emptySpace + weight[4] * emptyBlock + weight[5] * clearLineNum;
		return AIScore;
	}

	public void setBestPoint() {
		double maxAIScore = -999999999;
		int left = 0;
		Block block = gameBoard.currentBlock;
		Point point = new Point(0, 0);
		Point bestPoint = new Point(0, 0);
		for (int i = 0; i < 4; i++) { // 회전모양 4개
			block.moveDown();
			block.AIPerformSpin();

			for (int j = 0; j < 4; j++)
				left += block.AIMoveLeft();
			point.setX(block.topLeftPoint.getX());
			point.setY(block.topLeftPoint.getY());
			for (int j = 0; j < 10; j++) { // colomn 10개
				block.AIFastDown();
				if (maxAIScore < setAIScore()) {
					maxAIScore = AIScore;
					bestPoint.setX(block.topLeftPoint.getX());
					bestPoint.setY(block.topLeftPoint.getY());

					for (int k = 0; k < 4; k++) {
						coord[k].setX(block.coord[k].getX());
						coord[k].setY(block.coord[k].getY());
					}

					// System.out.println("varHeight -> "+varHeight);
					// System.out.println("empty contact wall -> "
					// +emptySpace+","+blockContactSurface+","+wallContactSurface);
					// System.out.println("emptyblock -> " + emptyBlock);

				}
				block.setTopLeftPoint(point);
				block.AIMoveRight();
				point.setX(block.topLeftPoint.getX());
				point.setY(block.topLeftPoint.getY());

			}
			point.setX(1);
			point.setY(4);
			block.setTopLeftPoint(point);
			left = 0;
		}

		for (int i = 0; i < 4; i++) {
			block.coord[i].setX(coord[i].getX());
			block.coord[i].setY(coord[i].getY());
		}

		block.setTopLeftPoint(bestPoint);
		block.topLeftPoint.setX(5);
		System.out.println(bestPoint.getX() + " " + bestPoint.getY() + " " + block.blockIndex);

		gameBoard.drop();
		try {
			gameBoard.t.sleep(500);
		} catch (InterruptedException e) {
		}

		block.fastDown();

	}
}
